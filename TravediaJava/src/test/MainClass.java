/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import util.MyDB;

import entities.Utilisateur;
import services.UtilisateurService;

/**
 * (121,"flen1", "benflen1", "flen1@flen.com", "1234flen", "Voyageur", 0, 0);
 * @author ibtihel
 * Test package contains main methods
 */
public class MainClass {
    public static void main(String[] args) {
        //Test Stuff Here
        
        Utilisateur u = new Utilisateur( 122,"flenn@fleeen.com","foulen123","omniaaa", "ghaadaaa", "Guide", 0, 0);
        UtilisateurService us = new UtilisateurService();
         us.modifier(u);
         
         
    }
}
