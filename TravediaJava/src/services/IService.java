/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author riadh
 * add generic type to apply to each 
 */
public interface IService<T> {
   /* void ajouter(T t);
    void modifier(T t);
    void supprimer(int id);
    List<T> recuperer();
    T recuperer(int id);*/
    public void ajouter(T t);
    public T getById(int id);
    public List<T> getAll();
    public boolean modifier(T t);
    public boolean supprimer(T t);
    public T getOne(T t);
}
