/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ameni
 */
public class Start extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
          //  Parent root = FXMLLoader.load(getClass().getResource("MapTest.fxml"));
           // Parent root = FXMLLoader.load(getClass().getResource("ListeRegionFront.fxml"));
            
            Scene scene = new Scene(root, 1020, 600);
            
            primaryStage.setTitle("Travedia");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage() + " "+ ex.getCause());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
