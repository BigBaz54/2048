package com.example.demo;

import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import static javafx.scene.input.KeyCombination.keyCombination;

public class VueMenu extends MenuBar implements Observateur {
    private Jeu jeu ;
    public VueMenu(Jeu jeu) {
        super();
        this.jeu = jeu;
        this.jeu.ajouterObservateur(this);
        Menu menu = new Menu("Jeu");
        MenuItem quitter = new MenuItem("Quitter ");
        quitter.setOnAction(event -> Platform.exit());
        quitter.setAccelerator(keyCombination("Ctrl+Q"));
        MenuItem nouveau = new MenuItem("Nouveau ");
        nouveau.setOnAction(event -> jeu.nouveau());
        nouveau.setAccelerator(keyCombination("Ctrl+N"));
        MenuItem taille = new MenuItem("Taille : "+jeu.getTaille());
        MenuItem objectif = new MenuItem("Objectif : "+jeu.getObjectif());
        menu.getItems().addAll(nouveau, taille, objectif, quitter);
        this.getMenus().add(menu);
    }
    public void reagir() {
        this.getMenus().get(0).getItems().get(1).setText("Taille : "+jeu.getTaille());
        this.getMenus().get(0).getItems().get(2).setText("Objectif : "+jeu.getObjectif());
    };
}
