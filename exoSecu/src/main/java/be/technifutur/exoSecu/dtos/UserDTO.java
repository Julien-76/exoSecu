package be.technifutur.exoSecu.dtos;

import be.technifutur.exoSecu.entities.Voyage;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {


    Integer id;


    String username;


    String password;


    Instant creationDate;


    List<VoyageDTO> voyagesDTO;

    boolean accountNonExpired = true;

    boolean accountNonLocked = true;

    boolean credentialsNonExpired = true;

    boolean enabled = true;
}
