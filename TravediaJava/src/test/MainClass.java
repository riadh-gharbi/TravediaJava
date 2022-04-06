/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Region;
import services.RegionService;
import util.MyDB;

/**
 *
 * @author riadh
 * Test package contains main methods
 */
public class MainClass {
    public static void main(String[] args) {
      //  Test Stuff Here
        Region r = new Region("blasabehya");
        RegionService ps = new RegionService();
         ps.ajouter(r);
        
//MyDB db= MyDB.getInstance() ;
// System.out.println(db);
    }
}
