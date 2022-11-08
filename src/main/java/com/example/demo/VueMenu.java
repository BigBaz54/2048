package com.example.demo;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;

import static javafx.scene.input.KeyCombination.keyCombination;

public class VueMenu extends MenuBar implements Observateur {
    private Jeu jeu ;
    public VueMenu(Jeu jeu) {
        super();
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);
        Menu menu = new Menu("Jeu");
        TextInputDialog dialTaille = new TextInputDialog("");
        dialTaille.setHeaderText("Entrer la taille choisie");
        TextInputDialog dialObj = new TextInputDialog("");
        dialObj.setHeaderText("Entrer l'objectif choisi");
        MenuItem quitter = new MenuItem("Quitter ");
        quitter.setOnAction(event -> Platform.exit());
        quitter.setAccelerator(keyCombination("Ctrl+Q"));
        MenuItem nouveau = new MenuItem("Nouveau ");
        nouveau.setOnAction(event -> jeu.nouveau());
        nouveau.setAccelerator(keyCombination("Ctrl+N"));
        MenuItem taille = new MenuItem("Taille : "+jeu.getTaille());
        taille.setOnAction(event -> {dialTaille.showAndWait(); try {jeu.setTaille(Integer.parseInt(dialTaille.getEditor().getText())); jeu.nouveau();} catch (NumberFormatException n) {}});
        MenuItem objectif = new MenuItem("Objectif : "+jeu.getObjectif());
        objectif.setOnAction(event -> {dialObj.showAndWait(); try {jeu.setObjectif(Integer.parseInt(dialObj.getEditor().getText())); jeu.nouveau();} catch (NumberFormatException n) {}});
        menu.getItems().addAll(nouveau, taille, objectif, quitter);
        this.getMenus().add(menu);
    }
    public void reagir() {
        this.getMenus().get(0).getItems().get(1).setText("Taille : "+jeu.getTaille());
        this.getMenus().get(0).getItems().get(2).setText("Objectif : "+jeu.getObjectif());
    };
}
