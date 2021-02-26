package one.digital.inovation.diopersonapi.service;

import one.digital.inovation.diopersonapi.dto.request.PersonDTO;
import one.digital.inovation.diopersonapi.dto.response.MessageResponseDTO;
import one.digital.inovation.diopersonapi.entity.Person;
import one.digital.inovation.diopersonapi.exception.PersonNotFoundException;
import one.digital.inovation.diopersonapi.mapper.PersonMapper;
import one.digital.inovation.diopersonapi.provider.PersonProvider;
import one.digital.inovation.diopersonapi.repository.PersonRepository;
import one.digital.inovation.diopersonapi.service.impl.PersonServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


@ExtendWith({MockitoExtension.class})
public class PersonServiceTest {
    @Mock
    private PersonRepository repository;
    @Mock
    private PersonMapper mapper;

    @InjectMocks
    private PersonServiceImpl service;

    @Test
    @DisplayName("Given a PersonDTO Then Return Saved Message")
    void createPersonOkTest() {
        PersonDTO personToCreate = PersonProvider.dto();
        Person savedPerson = PersonProvider.entity();

        Mockito.when(mapper.dto2Entity(ArgumentMatchers.any(PersonDTO.class))).thenReturn(savedPerson);
        Mockito.when(repository.save(ArgumentMatchers.any(Person.class))).thenReturn(savedPerson);

        MessageResponseDTO expectedMessage = MessageResponseDTO.builder()
                .message("Create person with ID: " + savedPerson.getId())
                .build();
        MessageResponseDTO actualMessage = service.createPerson(personToCreate);

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Given an id and a PersonDTO Then Return UpdatedMessage")
    void updateByIdOkTest() throws PersonNotFoundException {
        PersonDTO personToUpdate = PersonProvider.dto();
        Person updatedPerson = PersonProvider.entity();

        Mockito.when(repository.findById(ArgumentMatchers.eq(personToUpdate.getId()))).thenReturn(Optional.of(updatedPerson));
        Mockito.when(mapper.dto2Entity(ArgumentMatchers.any(PersonDTO.class))).thenReturn(updatedPerson);
        Mockito.when(repository.save(ArgumentMatchers.any(Person.class))).thenReturn(updatedPerson);

        MessageResponseDTO expectedMessage = MessageResponseDTO.builder()
                .message("Update person with ID: " + updatedPerson.getId())
                .build();
        MessageResponseDTO actualMessage = service.updateById(personToUpdate.getId(), personToUpdate);

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Given an id that not exists and a PersonDTO Then Return throw PersonNotFoundException")
    void updateByIdPersonNotFoundExceptionTest() {
        PersonDTO personToUpdate = PersonProvider.dto();

        Mockito.when(repository.findById(ArgumentMatchers.eq(personToUpdate.getId()))).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(PersonNotFoundException.class,
                () -> service.updateById(personToUpdate.getId(), personToUpdate));

        String expectedMessage = "Person not found with ID [" + personToUpdate.getId() + "]";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Return a list with all person in database")
    void findAllTest() {
        Person personInDatabase = PersonProvider.entity();
        PersonDTO personInResult = PersonProvider.dto();

        Mockito.when(repository.findAll()).thenReturn(Lists.list(personInDatabase));
        Mockito.when(mapper.entity2Dto(ArgumentMatchers.any(Person.class))).thenReturn(personInResult);

        List<PersonDTO> expectedResult = Lists.list(personInResult);
        List<PersonDTO> actualResult = service.listAll();

        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Given an id Then Return a Person with that id")
    void findByIdOkTest() throws PersonNotFoundException {
        Person personToFind = PersonProvider.entity();

        Mockito.when(repository.findById(ArgumentMatchers.eq(personToFind.getId()))).thenReturn(Optional.of(personToFind));
        Mockito.when(mapper.entity2Dto(ArgumentMatchers.any(Person.class))).thenReturn(PersonProvider.dto());

        PersonDTO expectedResult = PersonProvider.dto();
        PersonDTO actualResult = service.findById(personToFind.getId());

        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Given an id that not exists Then Return throw PersonNotFoundException")
    void findByIdPersonNotFoundExceptionTest() {
        PersonDTO personToFind = PersonProvider.dto();

        Mockito.when(repository.findById(ArgumentMatchers.eq(personToFind.getId()))).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(PersonNotFoundException.class,
                () -> service.findById(personToFind.getId()));

        String expectedMessage = "Person not found with ID [" + personToFind.getId() + "]";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Given an id Then Delete the person in database that have the id")
    void deleteByIdOkTest() throws PersonNotFoundException {
        Person personToDelete = PersonProvider.entity();

        Mockito.when(repository.findById(ArgumentMatchers.eq(personToDelete.getId()))).thenReturn(Optional.of(personToDelete));

        service.deleteById(personToDelete.getId());
    }

    @Test
    @DisplayName("Given an id that not exists Then Return throw PersonNotFoundException")
    void deleteByIdPersonNotFoundExceptionTest() {
        PersonDTO personToDelete = PersonProvider.dto();

        Mockito.when(repository.findById(ArgumentMatchers.eq(personToDelete.getId()))).thenReturn(Optional.empty());

        Exception exception = Assertions.assertThrows(PersonNotFoundException.class,
                () -> service.findById(personToDelete.getId()));

        String expectedMessage = "Person not found with ID [" + personToDelete.getId() + "]";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
