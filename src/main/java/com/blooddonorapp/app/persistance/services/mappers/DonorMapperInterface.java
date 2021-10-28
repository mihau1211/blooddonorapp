package com.blooddonorapp.app.persistance.services.mappers;

import com.blooddonorapp.app.models.Donor;
import com.blooddonorapp.app.persistance.entities.DonorDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DonorMapperInterface {

    DonorDTO toDTO(Donor donor);

    @Named("DTOToMap")
    @InheritInverseConfiguration
    Donor toMap(DonorDTO donorDTO);

    @IterableMapping(qualifiedByName = "DTOToMap")
    List<DonorDTO> toList(List<Donor> list);
}
