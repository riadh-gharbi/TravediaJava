/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author user
 */
public class Start extends Application {
    private double x,y;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ShowCategorie.fxml"));
        Scene scene = new Scene(root);
       //-- primaryStage.setScene(new Scene(root));
        //set stage borderless
       // --primaryStage.initStyle(StageStyle.UNDECORATED);
        //drag it here
       //-- root.setOnMousePressed(event ->{
           //-- x = event.getSceneX();
          //--  y = event.getSceneY();
            
       //-- });
        //--root.setOnMouseDragged(event ->{
         //--  primaryStage.setX(event.getSceneX() - x);
         //--  primaryStage.setY(event.getSceneY() - y);
       //-- });
        
        primaryStage.setTitle("Travedia");
       primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
