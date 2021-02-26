package one.digital.inovation.diopersonapi.mapper;

import one.digital.inovation.diopersonapi.dto.request.PersonDTO;
import one.digital.inovation.diopersonapi.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PhoneMapper.class})
public interface PersonMapper {
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Person dto2Entity(PersonDTO dto);

    PersonDTO entity2Dto(Person entity);
}
