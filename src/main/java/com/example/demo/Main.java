package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start (Stage primaryStage) {
        Jeu jeu = new Jeu(5, 20) ;
        BorderPane root = new BorderPane() ;
        root.setTop(new VueMenu(jeu)) ;
        GridPane vp = new VuePlateau(jeu);
        vp.setAlignment(Pos.CENTER);
        root.setCenter(vp) ;
        VBox vs = new VueStats(jeu);
        vs.setAlignment(Pos.CENTER);
        root.setBottom(vs) ;
        primaryStage.setScene(new Scene(root, 450, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}