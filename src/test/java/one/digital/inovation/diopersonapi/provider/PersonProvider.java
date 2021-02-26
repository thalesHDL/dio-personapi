package one.digital.inovation.diopersonapi.provider;

import one.digital.inovation.diopersonapi.dto.request.PersonDTO;
import one.digital.inovation.diopersonapi.entity.Person;
import org.assertj.core.util.Lists;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PersonProvider {
    public static final Long ID = 1L;
    public static final String CPF = "16649409790";
    public static final String FIST_NAME = "Jose";
    public static final String LAST_NAME = "Silva";
    public static final String BIRTH_DATE = "18-04-1992";

    public static PersonDTO dto() {
        return PersonDTO.builder()
                .id(ID)
                .cpf(CPF)
                .firstName(FIST_NAME)
                .lastName(LAST_NAME)
                .birthDate(BIRTH_DATE)
                .phones(Lists.list(PhoneProvider.dto()))
                .build();
    }

    public static PersonDTO dtoWithLastName(String lastName) {
        return PersonDTO.builder()
                .id(ID)
                .cpf(CPF)
                .firstName(FIST_NAME)
                .lastName(lastName)
                .birthDate(BIRTH_DATE)
                .phones(Lists.list(PhoneProvider.dto()))
                .build();
    }

    public static Person entity() {
        return Person.builder()
                .id(ID)
                .cpf(CPF)
                .firstName(FIST_NAME)
                .lastName(LAST_NAME)
                .birthDate(LocalDate.parse(BIRTH_DATE, DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .phones(Lists.list(PhoneProvider.entity()))
                .build();
    }
}
