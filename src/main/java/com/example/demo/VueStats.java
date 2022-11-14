package com.example.demo;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VueStats extends VBox implements Observateur {
    private Label stats ;
    private Jeu jeu ;
    public VueStats(Jeu jeu) {
        super();
        this.jeu = jeu ;
        this.jeu.ajouterObservateur(this) ;
        stats = new Label("Parties gagnées/jouées : "+jeu.getNbGagnees()+"/"+jeu.getNbJouees());
        stats.setStyle("-fx-font: normal bold 15px ''; -fx-background-color: lightgray; -fx-padding: 25px; -fx-background-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 5, 0, 0, 0); -fx-background-insets: 20px;");
        this.getChildren().add(stats);
    }
    public void reagir() {
        String st = "Parties gagnées/jouées : ";
        st += jeu.getNbGagnees() + "/" + jeu.getNbJouees();
        this.stats.setText(st);
    }
}
