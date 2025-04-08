package school.hei.patrimoine.cas.example;

import school.hei.patrimoine.modele.Patrimoine;
import school.hei.patrimoine.modele.Personne;
import school.hei.patrimoine.modele.possession.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.Calendar.APRIL;
import static java.util.Calendar.JANUARY;
import static school.hei.patrimoine.modele.Argent.ariary;
import static school.hei.patrimoine.modele.Devise.MGA;

public class PatrimoineBakoAu31Decembre2025 implements Supplier<Patrimoine> {
    public static final LocalDate AU_8_AVRIL_2025 = LocalDate.of(2025, APRIL, 8);

    private Compte compteBNI() {
        return new Compte("compte courant BNI", AU_8_AVRIL_2025, ariary(2_000_000));
    }

    private Compte compteBMOI() {
        return new Compte("compte épargne BMOI", AU_8_AVRIL_2025, ariary(625_000));
    }

    private Compte coffre() {
        return new Compte("coffre à la maison", AU_8_AVRIL_2025, ariary(1_750_000));
    }

    private Materiel ordinateur() {
        return new Materiel("ordinateur portable", AU_8_AVRIL_2025, AU_8_AVRIL_2025, ariary(3_000_000), -0.12);
    }

    private static Set<Possession> possessionsAvecFluxMensuels(Compte bni, Compte bmoi) {
        new FluxArgent("salaire mensuel", bni, AU_8_AVRIL_2025, LocalDate.MAX, 2, ariary(2_125_000));

        new FluxArgent("épargne mensuelle", bni, AU_8_AVRIL_2025, LocalDate.MAX, 3, ariary(-200_000));
        new FluxArgent("réception épargne", bmoi, AU_8_AVRIL_2025, LocalDate.MAX, 3, ariary(200_000));

        new FluxArgent("dépenses de vie", bni, AU_8_AVRIL_2025, LocalDate.MAX, 1, ariary(-700_000));

        new FluxArgent("loyer colocation", bni, AU_8_AVRIL_2025, LocalDate.MAX, 26, ariary(-600_000));

        return Set.of(bni, bmoi);
    }

    @Override
    public Patrimoine get() {
        var bako = new Personne("Bako");
        var ordinateur = ordinateur();
        var bni = compteBNI();
        var bmoi = compteBMOI();
        var coffre = coffre();

        var possessions = new GroupePossession(
                "patrimoine initial avec flux mensuels",
                MGA,
                AU_8_AVRIL_2025,
                Set.of(ordinateur, coffre)
        );

        var groupeFlux = new GroupePossession(
                "comptes avec flux mensuels",
                MGA,
                AU_8_AVRIL_2025,
                possessionsAvecFluxMensuels(bni, bmoi)
        );

        return Patrimoine.of("Bako au 8 avril 2025", MGA, AU_8_AVRIL_2025, bako, Set.of(possessions, groupeFlux));
    }

    public Patrimoine projectionFinAnnee() {
        return get().projectionFuture(LocalDate.of(2025, JANUARY, 1));
    }
}
