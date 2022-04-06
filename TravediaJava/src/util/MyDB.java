/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author riadh
 * Responsable d'établir la connection avec la base de données
 * Faire la liaison entre le projet JAVA et la DB MySQL
 */
public class MyDB {
    private final String url ="jdbc:mysql://localhost:3306/travedia";
    private final String username ="root";
    private final String pwd ="";
    private Connection connection ; 
    private static MyDB instance;

    public MyDB()  {
        try { 
            connection= DriverManager.getConnection(url, username, pwd);
        } catch (SQLException ex) {
                        System.out.println(ex.getMessage());

        }
        System.out.println("cnx travedia  mrigla");
    }

   
    public static MyDB getInstance() {
        if (
                instance==null
                )
            instance= new MyDB();
        return instance ; 
    }
    public Connection getConnection(){
        return connection; 
    }
    
    
}