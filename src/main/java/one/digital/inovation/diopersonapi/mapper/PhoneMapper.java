package one.digital.inovation.diopersonapi.mapper;

import one.digital.inovation.diopersonapi.dto.request.PhoneDTO;
import one.digital.inovation.diopersonapi.entity.Phone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {
    Phone dto2Entity(PhoneDTO dto);

    PhoneDTO entity2Dto(Phone entity);
}
