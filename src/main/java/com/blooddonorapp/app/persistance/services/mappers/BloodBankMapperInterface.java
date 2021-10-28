package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.BloodBank;
import com.blooddonorapp.app.persistance.entities.BloodBankDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface BloodBankMapperInterface {

    BloodBankDTO toDTO(BloodBank bloodBank);

    @Named("DTOToMap")
    @InheritInverseConfiguration
    BloodBank toMap(BloodBankDTO bloodBankDTO);

    @IterableMapping(qualifiedByName = "DTOToMap")
    List<BloodBankDTO> toList(List<BloodBank> list);
}
