package be.technifutur.exoSecu.repositories;

import be.technifutur.exoSecu.entities.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoyageRepository extends JpaRepository<Voyage, Integer> {
}
