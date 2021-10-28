package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.Donation;
import com.blooddonorapp.app.persistance.entities.DonationDTO;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DonationMapperInterface {

    @Mappings({
            @Mapping(source = "donorId", target = "donorId"),
            @Mapping(source = "bloodBankId", target = "bloodBankId")
    })
    DonationDTO toDTO(Donation donation);

    @Named("DTOToMap")
    @InheritInverseConfiguration
    Donation toMap(DonationDTO donationDTO);

    @IterableMapping(qualifiedByName = "DTOToMap")
    List<DonationDTO> toList(List<Donation> list);
}
