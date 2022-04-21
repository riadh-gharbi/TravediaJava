/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;


import entities.Paiement;
import entities.Reclamation;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import services.PaiementService;
import services.ReclamationService;

/**
 *
 * @author riadh
 * Test package contains main methods
 */
public class MainClass{
    public static void main(String[] args) {
        //Test Stuff Here
//        Reclamation r = new Reclamation(1, "Java Test", "Ceci est un test java"
//                , "En Cours", null);
//        
//        ReclamationService serv = new ReclamationService();
        //serv.ajouter(r);
        //System.out.println( serv.recuperer().toString());
        
        //Delete Test, 7 ,worked, but only the ones without rec Response foreign key
        //serv.supprimer(7);
        
        //Delete Test, 3 , worked after fixing cascade 
        //serv.supprimer(3);
        
        //Add Reponse Test : WORKED
//        serv.ajouter(r);
//        int id = serv.recuperer().get(serv.recuperer().size()-1).getId();
//        ReclamationReponse rep = new ReclamationReponse("Ceci est une reponse Java", id);
//        serv.ajouterReponse(rep, id);
        

           PaiementService servP = new PaiementService();
           
       //    Test AJOUT Paiement
//           Paiement p;
//        p = new Paiement(1, 7, 2, 200, "Payé", new Date(), null, "Cash", null);
//        servP.ajouter(p);

            //Test Modification Paiement
                      // Paiement p;
//       p = new Paiement(1, 7, 2, 200, "Payé", new Date(), null, "En Ligne", null);
//            servP.modifier(p);
System.out.println(servP.recuperer().toString());

    }
   
}
