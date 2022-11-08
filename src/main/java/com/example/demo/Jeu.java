package com.example.demo;

import java.util.ArrayList;
import java.util.Random;

public class Jeu extends SujetObserve{
    private int taille;
    private int objectif;
    private int nbJouees;
    private int nbGagnees;
    private int[] cases;
    private ArrayList<Integer> nextTransition;


    public Jeu(int taille, int objectif) {
        this.taille = taille;
        this.objectif = objectif;
        this.nbJouees = -1;
        this.nbGagnees = 0;
        cases = new int[taille*taille];
        nextTransition = new ArrayList<>();
    }

    public int getTaille() {
        return taille;
    }

    public int getObjectif() {
        return objectif;
    }

    public int getNbGagnees() {
        return nbGagnees;
    }

    public int getNbJouees() {
        return nbJouees;
    }

    public int getCase(int l, int c) {
        return cases[l*taille+c];
    }

    public void nouveau() {
        resetTransition();
        nbJouees += 1;
        boolean won = false;
        for (int e: cases) {
            if (e>=objectif) {
                won = true;
            }
        }
        if (won) {nbGagnees+=1;}
        Random random = new Random();
        cases = new int[taille*taille];
        for (int i=0; i<taille*taille; i++) {
            cases[i] = (int) Math.pow(2, random.nextInt(1,4)) ;
        }
        this.notifierObservateurs() ;
    }

    public void jouer(int l, int c) {
        Random random = new Random();
        int count = 0;
        nextTransition.add(l*taille+c);
        int val = getCase(l, c);
        for (int i=0; i<taille; i++) {
            if (getCase(i, c)==val && i!=l)  {
                count++;
                nextTransition.add(i*taille+c);
                cases[i*taille+c] = (int) Math.pow(2,random.nextInt(1,4));
            }
        }
        for (int j=0; j<taille; j++) {
            if (getCase(l, j)==val && j!=c)  {
                count++;
                nextTransition.add(l*taille+j);
                cases[l*taille+j] = (int) Math.pow(2,random.nextInt(1,4));
            }
        }
        cases[l*taille+c] = val*(count+1);
        notifierObservateurs();
    }

    public ArrayList<Integer> getNextTransition() {
        return nextTransition;
    }

    public void resetTransition() {
        nextTransition = new ArrayList<>();
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public void setObjectif(int objectif) {
        this.objectif = objectif;
    }
}
