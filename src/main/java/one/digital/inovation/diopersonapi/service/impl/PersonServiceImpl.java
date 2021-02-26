package one.digital.inovation.diopersonapi.service.impl;

import lombok.RequiredArgsConstructor;
import one.digital.inovation.diopersonapi.dto.request.PersonDTO;
import one.digital.inovation.diopersonapi.dto.response.MessageResponseDTO;
import one.digital.inovation.diopersonapi.entity.Person;
import one.digital.inovation.diopersonapi.exception.PersonNotFoundException;
import one.digital.inovation.diopersonapi.mapper.PersonMapper;
import one.digital.inovation.diopersonapi.repository.PersonRepository;
import one.digital.inovation.diopersonapi.service.PersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;
    private final PersonMapper mapper;

    public MessageResponseDTO createPerson(PersonDTO dto) {
        Person entity = this.mapper.dto2Entity(dto);
        entity = this.repository.save(entity);
        return this.createMessageResponse("Create person with ID: ", entity.getId());
    }

    public MessageResponseDTO updateById(Long id, PersonDTO dto) throws PersonNotFoundException {
        this.findByIdIfExists(id);
        Person entity = this.mapper.dto2Entity(dto);
        this.repository.save(entity);
        return this.createMessageResponse("Update person with ID: ", entity.getId());
    }

    public List<PersonDTO> listAll() {
        return this.repository.findAll().stream()
                .map(this.mapper::entity2Dto)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person entity = this.findByIdIfExists(id);
        return this.mapper.entity2Dto(entity);
    }

    public void deleteById(Long id) throws PersonNotFoundException {
        Person entity = this.findByIdIfExists(id);
        this.repository.delete(entity);
    }

    private Person findByIdIfExists(Long id) throws PersonNotFoundException {
        return this.repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    private MessageResponseDTO createMessageResponse(String message, Long id) {
        return MessageResponseDTO.builder()
                .message(message + id)
                .build();
    }
}
