/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Categorie;
import services.CategorieService;
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
        Categorie cat = new Categorie(94,"java modifier","java image modifier");
        CategorieService cats = new CategorieService();
        System.out.println(cats.recuperer());
    }
}
