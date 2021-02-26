package one.digital.inovation.diopersonapi.provider;

import one.digital.inovation.diopersonapi.dto.request.PhoneDTO;
import one.digital.inovation.diopersonapi.entity.Phone;
import one.digital.inovation.diopersonapi.enums.PhoneType;
import org.springframework.stereotype.Component;

@Component
public class PhoneProvider {
    public static final Long ID = 1L;
    public static final String MOBILE_NUMBER = "5537998651425";

    public static PhoneDTO dto() {
        return PhoneDTO.builder()
                .id(ID)
                .number(MOBILE_NUMBER)
                .type(PhoneType.MOBILE)
                .build();
    }

    public static Phone entity() {
        return Phone.builder()
                .id(ID)
                .number(MOBILE_NUMBER)
                .type(PhoneType.MOBILE)
                .build();
    }
}
