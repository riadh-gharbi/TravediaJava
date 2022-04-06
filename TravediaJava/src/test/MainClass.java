/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Categorie;
import entities.Evenement;
import services.CategorieService;
import services.EvenementService;
import util.MyDB;

/**
 *
 * @author riadh
 * Test package contains main methods
 */
public class MainClass {
    public static void main(String[] args) {
        //Test Stuff Here
        MyDB db = MyDB.getInstance();
        System.out.println(db);
        Evenement evt = new Evenement(48,"java modifier","java image modifier","crud modifier", "2033-12-12","2033-12-12",14);
        EvenementService evts = new EvenementService();
        System.out.println(evts.recuperer());
    }
}
