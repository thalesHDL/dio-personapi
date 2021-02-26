package one.digital.inovation.diopersonapi.service;

import one.digital.inovation.diopersonapi.dto.request.PersonDTO;
import one.digital.inovation.diopersonapi.dto.response.MessageResponseDTO;
import one.digital.inovation.diopersonapi.exception.PersonNotFoundException;

import java.util.List;

public interface PersonService {
    MessageResponseDTO createPerson(PersonDTO dto);

    MessageResponseDTO updateById(Long id, PersonDTO dto) throws PersonNotFoundException;

    List<PersonDTO> listAll();

    PersonDTO findById(Long id) throws PersonNotFoundException;

    void deleteById(Long id) throws PersonNotFoundException;
}
