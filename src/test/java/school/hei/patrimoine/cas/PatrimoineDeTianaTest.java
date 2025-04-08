package school.hei.patrimoine.cas;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import school.hei.patrimoine.cas.example.PatrimoineTianaAu8Avril2025;
import school.hei.patrimoine.modele.Argent;
import school.hei.patrimoine.modele.Patrimoine;

import java.time.LocalDate;

public class PatrimoineDeTianaTest {

    @Test
    void patrimoineDeTianaFinMars(){
        var cas = new PatrimoineTianaAu8Avril2025();
        LocalDate finAnnee = LocalDate.of(2026, 03, 31);

        Patrimoine patrimoineFinAnnee = cas.get().projectionFuture(finAnnee);

        Argent result = patrimoineFinAnnee.getValeurComptable();
        System.out.println(result);
    }
}
