package be.technifutur.exoSecu.services;

import be.technifutur.exoSecu.dtos.VoyageDTO;
import be.technifutur.exoSecu.entities.Voyage;
import be.technifutur.exoSecu.mapper.Mapper;
import be.technifutur.exoSecu.repositories.VoyageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VoyageService {

    private final Mapper mapper;

    private final VoyageRepository repository;

    public VoyageService(Mapper mapper, VoyageRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }


    public Set<VoyageDTO> getAllVoyages() {
        return repository.findAll()
                .stream()
                .map(mapper::toVoyageDto)
                .collect(Collectors.toSet());
    }

    public VoyageDTO getVoyageById(int id) {

        Optional<Voyage> voyage = repository.findById(id);

        return voyage.map(mapper::toVoyageDto).orElse(null);
    }

    public void insertVoyage(Voyage voyage) {
        if (voyage != null) {
            repository.save(voyage);
        }
    }

    public void updateVoyage(Voyage voyage) {
        if (voyage != null) {
            Optional<Voyage> voyageToUpdate = repository.findById(voyage.getId());
            if (voyageToUpdate.isPresent()) {
                repository.save(voyage);
            }
        }
    }
}
