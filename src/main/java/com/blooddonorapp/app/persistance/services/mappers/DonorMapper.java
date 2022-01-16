//package com.blooddonorapp.app.persistance.services.mappers;
//
//import com.blooddonorapp.app.models.BloodBank;
//import com.blooddonorapp.app.models.BloodType;
//import com.blooddonorapp.app.models.Donor;
//import com.blooddonorapp.app.models.Gender;
//import com.blooddonorapp.app.persistance.entities.DonorDTO;
//import org.mapstruct.Mapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Mapper
//public interface DonorMapper {
//
//    DonorDTO toDto(Donor donor);
//    Donor toMap(DonorDTO donorDTO);
//    List<DonorDTO> toListDto(List<Donor> list);
//    List<Donor> toList(List<DonorDTO> listDto);
//
////    private DonationMapper donationMapper = new DonationMapper();
////
////    public Donor toMap(final DonorDTO donorDTO, final BloodBank bloodBank){
////        return Donor.builder()
////                .donorId(donorDTO.getDonorId())
////                .bloodBank(bloodBank)
////                .birthdate(donorDTO.getBirthdate())
////                .username(donorDTO.getUsername())
////                .email(donorDTO.getEmail())
////                .password(donorDTO.getPassword())
////                .name(donorDTO.getName())
////                .surname(donorDTO.getSurname())
////                .gender(Gender.valueOf(donorDTO.getGender()))
////                .bloodType(BloodType.valueOf(donorDTO.getBloodType()))
////                .city(donorDTO.getCity())
////                .points(donorDTO.getPoints())
//////                .donations(donorDTO.getDonations())
////                .build();
////    }
////
////    public DonorDTO toDTO(final Donor donor){
////        return DonorDTO.builder()
////                .donorId(donor.getDonorId())
////                .bloodBankId(donor.getBloodBank().getBloodBankId())
////                .birthdate(donor.getBirthdate())
////                .username(donor.getUsername())
////                .email(donor.getEmail())
////                .password(donor.getPassword())
////                .name(donor.getName())
////                .surname(donor.getSurname())
////                .gender(donor.getGender().toString())
////                .bloodType(donor.getBloodType().toString())
////                .city(donor.getCity())
////                .points(donor.getPoints())
////                .donations(donationMapper.toList(donor.getDonations()))
////                .build();
////    }
////
////    public List<DonorDTO> toList(final List<Donor> list){
////        return list.stream()
////                .map(this::toDTO)
////                .collect(Collectors.toList());
////    }
//}
