package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
    @Override
    public void start (Stage primaryStage) {
        Jeu jeu = new Jeu(5, 20) ;
        BorderPane root = new BorderPane() ;
        root.setTop(new VueMenu(jeu)) ;
        root.setCenter(new VuePlateau(jeu)) ;
        root.setBottom(new VueStats(jeu)) ;
        primaryStage.setScene(new Scene(root, 450, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}