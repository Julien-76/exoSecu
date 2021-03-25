package be.technifutur.exoSecu.mapper;

import be.technifutur.exoSecu.dtos.UserDTO;
import be.technifutur.exoSecu.dtos.VoyageDTO;
import be.technifutur.exoSecu.entities.User;
import be.technifutur.exoSecu.entities.Voyage;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class Mapper {

    public VoyageDTO toVoyageDto (Voyage voyage /* #Desireless */) {
        return voyage != null ? VoyageDTO.builder()
                .id(voyage.getId())
                .lieu(voyage.getLieu())
                .dateDebut(voyage.getDateDebut())
                .dateFin(voyage.getDateFin())
                .prix(voyage.getPrix())
                .hebergement(voyage.getHebergement())
                .nbrePersonnes(voyage.getNbrePersonnes())
                .build() : null;
    }

    public Voyage toVoyage (VoyageDTO voyageDto) {
        return voyageDto != null ? Voyage.builder()
                .id(voyageDto.getId())
                .lieu(voyageDto.getLieu())
                .dateDebut(voyageDto.getDateDebut())
                .dateFin(voyageDto.getDateFin())
                .prix(voyageDto.getPrix())
                .hebergement(voyageDto.getHebergement())
                .nbrePersonnes(voyageDto.getNbrePersonnes())
                .build() : null ;
    }

    public UserDTO toUserDto(User entity) {
        return entity != null ? UserDTO.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .password(entity.getPassword())
                .creationDate(entity.getCreationDate())
                .voyagesDTO(entity.getVoyages().stream().map(this::toVoyageDto).collect(Collectors.toList()))
                .build() : null;
    }
}
