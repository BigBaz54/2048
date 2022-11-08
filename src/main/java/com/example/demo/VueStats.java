package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class VueStats extends Pane implements Observateur {
    private Label stats ;
    private Jeu jeu ;
    public VueStats(Jeu jeu) {
        super();
        this.jeu = jeu ;
        this.jeu.ajouterObservateur(this) ;
        stats = new Label("Parties gagnées/jouées : "+jeu.getNbGagnees()+"/"+jeu.getNbJouees());
        this.getChildren().add(stats);
    }
    public void reagir() {
        String st = "Parties gagnées/jouées : ";
        st += jeu.getNbGagnees() + "/" + jeu.getNbJouees();
        this.stats.setText(st);
    }
}
