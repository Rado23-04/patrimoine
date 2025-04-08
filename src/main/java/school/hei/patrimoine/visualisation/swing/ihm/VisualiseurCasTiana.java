package school.hei.patrimoine.visualisation.swing.ihm;

import school.hei.patrimoine.cas.example.PatrimoineBakoAu31Decembre2025;
import school.hei.patrimoine.cas.example.PatrimoineTianaAu8Avril2025;

import java.util.List;

import static java.awt.EventQueue.invokeLater;

public class VisualiseurCasTiana {
    public static void main(String[] args) {
        invokeLater(
                () ->
                        new MainIHM(
                                List.of(
                                        new PatrimoineTianaAu8Avril2025().get()
                                )
                        )
        );
    }
}
