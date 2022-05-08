/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Commentaire;
import entities.Poste;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import services.ServiceCommentaire;
import services.ServicePoste;
import util.MyDB;

/**
 *
 * @author riadh Test package contains main methods
 */
public class MainClass {

    public static void main(String[] args) {
        //Test Stuff Here
        MyDB ds = MyDB.getInstance();
        ServicePoste sp = new ServicePoste();
        LocalDate locald1;
        locald1 = LocalDate.of(2022, 04, 04);
        Date date1 = Date.valueOf(locald1);
        Poste p = new Poste(1, "taswira", "ahmed", 99,date1);
        sp.supprimerbyId(66);
        /*ServiceCommentaire sc = new ServiceCommentaire();
        Commentaire c = new Commentaire(1, "Contenu", null);
        sc.ajouter(c);*/
        ServiceCommentaire sc = new ServiceCommentaire();
        // LocalDate date = LocalDate.now();
        //Instant instant = date.atStartOfDay(ZoneId.systemDefault()).toInstant();
        LocalDate locald;
        locald = LocalDate.of(2022, 04, 04);
        Date date = Date.valueOf(locald); // Magic happens here!
        //sc.setDate(date);
       /* Commentaire c = new Commentaire(1,66, "Contenu", date);
        sc.modifier(c);*/
    }
}
