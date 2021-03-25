package be.technifutur.exoSecu.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Embeddable
public class Hebergement {

    String nom;

    TypeH typeHebergement;

    String adresse;
}
