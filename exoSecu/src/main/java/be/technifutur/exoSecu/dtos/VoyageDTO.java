package be.technifutur.exoSecu.dtos;

import be.technifutur.exoSecu.entities.Hebergement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoyageDTO {

    Integer id;


    String lieu;


    LocalDate dateDebut;


    LocalDate dateFin;


    Integer prix;


    Hebergement hebergement;


    Integer nbrePersonnes;

}
