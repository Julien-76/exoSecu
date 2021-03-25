package be.technifutur.exoSecu.utils;

import be.technifutur.exoSecu.entities.Hebergement;
import be.technifutur.exoSecu.entities.TypeH;
import be.technifutur.exoSecu.entities.Voyage;
import be.technifutur.exoSecu.services.VoyageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class VoyageInit implements InitializingBean {

    private final VoyageService service;

    public VoyageInit(VoyageService service) {
        this.service = service;
    }

    private final List<Voyage> init = Arrays.asList(
            Voyage.builder()
                    .lieu("Londres")
                    .dateDebut(LocalDate.of(2021, 07, 14))
                    .dateFin(LocalDate.of(2021,07,28))
                    .prix(240)
                    .hebergement(Hebergement.builder()
                            .nom("Sir Jules")
                            .typeHebergement(TypeH.APPARTEMENT)
                            .adresse("Fish and Chips Street")
                            .build())
                    .nbrePersonnes(3)
                    .build(),
            Voyage.builder()
                    .lieu("Montreal")
                    .dateDebut(LocalDate.of(2021, 5, 5))
                    .dateFin(LocalDate.of(2021,6,4))
                    .prix(345)
                    .hebergement(Hebergement.builder()
                            .nom("Tabarnak")
                            .typeHebergement(TypeH.VILLA)
                            .adresse("Rue de la poutine")
                            .build())
                    .nbrePersonnes(4)
                    .build(),
            Voyage.builder()
                    .lieu("Paris")
                    .dateDebut(LocalDate.of(2021, 8, 2))
                    .dateFin(LocalDate.of(2021,8,16))
                    .prix(544)
                    .hebergement(Hebergement.builder()
                            .nom("Chez tonton Jacques")
                            .typeHebergement(TypeH.APPARTEMENT)
                            .adresse("Champs Elysee")
                            .build())
                    .nbrePersonnes(2)
                    .build(),
            Voyage.builder()
                    .lieu("Sibérie")
                    .dateDebut(LocalDate.of(2021, 11, 13))
                    .dateFin(LocalDate.of(2021,11,27))
                    .prix(520)
                    .hebergement(Hebergement.builder()
                            .nom("Spoutnik Land")
                            .typeHebergement(TypeH.CHALET)
                            .adresse("Republique des Komis")
                            .build())
                    .nbrePersonnes(5)
                    .build()
    );


    @Override
    public void afterPropertiesSet() throws Exception {
        this.init.forEach(service::insertVoyage);
    }
}
