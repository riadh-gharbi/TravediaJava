/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import services.UtilisateurService;

/**
 * (121,"flen1", "benflen1", "flen1@flen.com", "1234flen", "Voyageur", 0, 0);
 *
 * @author ibtihel Test package contains main methods
 */
public class MainClass {

    public static void main(String[] args) {
        //Test Stuff Here
        UtilisateurService us = new UtilisateurService();
        us.findByEmail("flenffn@123.com");

    }
}
