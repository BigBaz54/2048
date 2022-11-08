package com.example.demo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EcouteurCase implements EventHandler<ActionEvent> {
    private int lig;
    private int col;
    private Jeu jeu;

    public EcouteurCase(Jeu jeu, int lig, int col) {
        this.lig = lig;
        this.col = col;
        this.jeu = jeu;
    }
    public void handle(ActionEvent event) {
        jeu.jouer(lig, col);
    }
}
