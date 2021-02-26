package one.digital.inovation.diopersonapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import one.digital.inovation.diopersonapi.dto.request.PersonDTO;
import one.digital.inovation.diopersonapi.dto.response.MessageResponseDTO;
import one.digital.inovation.diopersonapi.exception.PersonNotFoundException;
import one.digital.inovation.diopersonapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO dto) {
        log.info("Request to create a person");
        return this.service.createPerson(dto);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") Long id, @RequestBody @Valid PersonDTO dto) throws PersonNotFoundException {
        log.info("Request to update a person with id: " + id);
        return this.service.updateById(id, dto);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDTO> listAll() {
        log.info("Request to list all person");
        return this.service.listAll();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonDTO findById(@PathVariable("id") Long id) throws PersonNotFoundException {
        log.info("Request to find person with id: " + id);
        return this.service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws PersonNotFoundException {
        log.info("Request to delete person with id: " + id);
        this.service.deleteById(id);
    }
}
