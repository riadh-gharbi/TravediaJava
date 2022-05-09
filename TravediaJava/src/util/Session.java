/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import entities.Utilisateur;
import services.UtilisateurService;

/**
 *
 * @author Ibtihel
 */
public class Session {

    private static final UtilisateurService fs = new UtilisateurService();

    private static Session instance = null;
    private static Utilisateur user = null;

    private Session(Utilisateur userConnected) {
        super();
        Session.user = userConnected;

    }

    private Session(Utilisateur userConnected, Boolean b) {
        super();
        Session.user = userConnected;
    }

    public final static Session getInstance(Utilisateur userConnected) {

        if (Session.instance == null) {
            Session.instance = new Session(userConnected);
        }
        return Session.instance;
    }

    public final static Session getFirstInstance(Utilisateur userConnected) {

        if (Session.instance == null) {

            Session.instance = new Session(userConnected, false);

        }
        return Session.instance;
    }

    public static UtilisateurService getFs() {
        return fs;
    }

    public static Session getInstance() {
        return instance;
    }

    public static Utilisateur getUser() {
        return user;
    }

    public static void setUser(Utilisateur user) {
        Session.user = user;
    }

}
