/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.util.List;

/**
 *
 * @author riadh add generic type to apply to each
 */
public interface IService<T> {

    void ajouter(T t);

    void modifier(T t);

    void supprimer(int id);

    List<T> recuperer();

    T recuperer(int id);

    public void blockUser(int id);

    public void unblockUser(int id);

    public boolean isEmailValid(String email);

    public int generateCode();

    public boolean isCodeValid(int userCode);

    public String login(Utilisateur user);

    public void logout();

    public boolean isConnected();

    public boolean isUserBlocked(Utilisateur user);

    public boolean verifyEmailEx(String email);

    public Utilisateur findByEmail(String email);

    public String createAccount(Utilisateur user);

    public void Accdelete();

    public boolean ModifierPassword(String oldPassword, String newPassword);

    public boolean resetPassword(String email, String newPassword);

    public String checkRole();
}
