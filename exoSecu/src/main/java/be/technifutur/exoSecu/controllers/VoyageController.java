package be.technifutur.exoSecu.controllers;


import be.technifutur.exoSecu.dtos.VoyageDTO;
import be.technifutur.exoSecu.entities.Voyage;
import be.technifutur.exoSecu.services.VoyageService;
import be.technifutur.exoSecu.utils.VoyageNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(path = "/voyages")
public class VoyageController {

private final VoyageService service;

    public VoyageController(VoyageService service) {
        this.service = service;
    }

    @GetMapping
    @CrossOrigin
    public Set<VoyageDTO> getAllVoyages() throws VoyageNotFoundException {
        Set<VoyageDTO> voyages = service.getAllVoyages();

        if (voyages.isEmpty()) {
            throw new VoyageNotFoundException("Y'a pas de voyage");
        }
        return voyages;
    }

    @GetMapping(path = "/{id}")
    @CrossOrigin
    public VoyageDTO getVoyageById(@PathVariable int id) throws VoyageNotFoundException {
        VoyageDTO voyage = service.getVoyageById(id);
        if (voyage == null) {
            throw new VoyageNotFoundException("Le voyage avec l'id " + id + " n'existe pas");
        }
        return voyage;
    }

    @PostMapping
    @CrossOrigin
    public void insertVoyage(Voyage voyage) {
        service.insertVoyage(voyage);
    }

    @PutMapping
    @CrossOrigin
    public void updateVoyage(Voyage voyage) {
        service.updateVoyage(voyage);
    }
}
