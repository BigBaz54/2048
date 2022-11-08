package com.example.demo;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

import java.util.ArrayList;

public class VuePlateau extends GridPane implements Observateur {
    private Jeu jeu ;

    public VuePlateau(Jeu jeu) {
        super();
        this.jeu = jeu ;
        this.jeu.ajouterObservateur(this) ;
        int taille = jeu.getTaille();
        this.setPadding(new Insets(20));
        this.setHgap(1);
        this.setVgap(1);
        for (int i=0; i<taille; i++) {
            for (int j=0; j<taille; j++) {
                Button temp = new Button(jeu.getCase(i, j)+"");
                temp.setMinWidth(40);
                temp.setMinHeight(40);
                temp.setMaxWidth(40);
                temp.setMaxHeight(40);
                temp.setOnAction(new EcouteurCase(jeu, i, j));
                this.add(temp, j, i);
            }
        }
        jeu.nouveau();
    }

    public void reagir() {
        int taille = jeu.getTaille();
        ArrayList<Integer> nextTransition = jeu.getNextTransition();
        if (!nextTransition.isEmpty()) {
            Button button = (Button) getChildren().get(nextTransition.get(0));
            Bounds target = button.localToScene(button.getBoundsInLocal());
            double targetX = target.getMaxX() - target.getWidth() / 2;
            double targetY = target.getMaxY() - target.getHeight() / 2;
            for (Integer e : nextTransition) {
                Button tbutton = (Button) getChildren().get(e);
                Bounds translated = tbutton.localToScene(tbutton.getBoundsInLocal());
                double translatedX = translated.getMaxX() - translated.getWidth() / 2;
                double translatedY = translated.getMaxY() - translated.getHeight() / 2;
                TranslateTransition trans = new TranslateTransition(Duration.millis(400), tbutton);
                trans.setByX(targetX - translatedX);
                trans.setByY(targetY - translatedY);
                TranslateTransition revert = new TranslateTransition(Duration.millis(1), tbutton);
                revert.setByX(-(targetX - translatedX));
                revert.setByY(-(targetY - translatedY));
                SequentialTransition seqTransition = new SequentialTransition (trans,new PauseTransition(Duration.millis(300)),revert);
                seqTransition.play();
            }

        }
        jeu.resetTransition();
        for (int i=0; i<taille; i++) {
            for (int j=0; j<taille; j++) {
                int val = jeu.getCase(i, j);
                if (val<=2) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #EEE4DA");
                } else if (val<=4) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #ECE0C8");
                } else if (val<=8) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #F2B179");
                } else if (val<=16) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #EC8D53");
                } else if (val<=32) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #F57C5F");
                } else if (val<=64) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #E95937");
                } else if (val<=128) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #F3D96B");
                } else if (val<=256) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #EDCC61");
                } else if (val<=512) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #F2C05D");
                } else if (val<=1024) {
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #EEC744");
                } else{
                    this.getChildren().get(i * taille + j).setStyle("-fx-background-color: #ECC230");
                }

                ((Button) this.getChildren().get(i*taille+j)).setText(val + "");

            }
        }
    };
}
