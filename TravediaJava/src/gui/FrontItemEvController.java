/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import entities.Evenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import services.EvenementService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class FrontItemEvController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label datefin;
    @FXML
    private Label datedeb;
    @FXML
    private Label description;
    FrontController controller;
    Evenement currentEvent;
    @FXML
    private Label Timer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public void setEvenement(Evenement ev){
         currentEvent = ev;
         nom.setText(ev.getNom());
        description.setText(ev.getDescription());
        datedeb.setText(ev.getDatedeb().toString());
        datefin.setText(ev.getDatefin().toString());
        
        //image.setText(ev.getImage());
        startTimer(findDifferenceFromNow(ev));
       

        
        File imageFile = new File(AddEventController.imageDir + "/" + ev.getImage());
        Image imagea = new Image(imageFile.toURI().toString());
        image.setImage(imagea) ;
        
    }
    public int findDifferenceFromNow(Evenement promotion)
    {
        Date d1 = Date.valueOf(LocalDate.now());
        Date d2 = promotion.getDatedeb();
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
            = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
  
  
            // parse method is used to parse
            // the text from a string to
            // produce the date
  
            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                = d2.getTime() - d1.getTime();
  
            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                = (difference_In_Time
                   / 1000)
                  % 60;
  
            long difference_In_Minutes
                = (difference_In_Time
                   / (1000 * 60))
                  % 60;
  
            long difference_In_Hours
                = (difference_In_Time
                   / (1000 * 60 * 60))
                  % 24;
  
            long difference_In_Years
                = (difference_In_Time
                   / (1000l * 60 * 60 * 24 * 365));
  
            long difference_In_Days
                = (difference_In_Time
                   / (1000 * 60 * 60 * 24))
                  % 365;
  
            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds
  
           return (int)(difference_In_Time/1000);
        
    }
    public int findDifference(Evenement promotion)
    {
        Date d1 = promotion.getDatedeb();
        Date d2 = promotion.getDatefin();
        // SimpleDateFormat converts the
        // string format to date object
        SimpleDateFormat sdf
            = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss");
  
  
            // parse method is used to parse
            // the text from a string to
            // produce the date
  
            // Calucalte time difference
            // in milliseconds
            long difference_In_Time
                = d2.getTime() - d1.getTime();
  
            // Calucalte time difference in
            // seconds, minutes, hours, years,
            // and days
            long difference_In_Seconds
                = (difference_In_Time
                   / 1000)
                  % 60;
  
            long difference_In_Minutes
                = (difference_In_Time
                   / (1000 * 60))
                  % 60;
  
            long difference_In_Hours
                = (difference_In_Time
                   / (1000 * 60 * 60))
                  % 24;
  
            long difference_In_Years
                = (difference_In_Time
                   / (1000l * 60 * 60 * 24 * 365));
  
            long difference_In_Days
                = (difference_In_Time
                   / (1000 * 60 * 60 * 24))
                  % 365;
  
            // Print the date difference in
            // years, in days, in hours, in
            // minutes, and in seconds
  
           return (int)(difference_In_Time/1000);
        
    }
    public void startTimer(int seconds)
    {
        int days = seconds/86400;
        int hours = (seconds%86400)/3600;
        int minutes = (seconds%3600)/60;
        int secondsTimer = seconds%60;
        
       // System.out.println(seconds);
        setTimeout(() -> {
            //System.out.println(this.getScene());
            Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if(seconds<0){Timer.setText("Finished !!");}
                            else
                            Timer.setText("Still "+String.valueOf(days)+"Days and"+String.valueOf(hours)+":"+String.valueOf(minutes)+":"+String.valueOf(secondsTimer)+" for the event !");
                        }
                    });

            
            startTimer(seconds-1);}, 1000);
        
    }
    public static void setTimeout(Runnable runnable, int delay){
    new Thread(() -> {
        try {
            Thread.sleep(delay);
            runnable.run();
        }
        catch (Exception e){
            System.err.println(e);
        }
    }).start();
}

    @FXML
    private void GoToWeather(ActionEvent event) throws IOException {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("Weather.fxml"));
//        Parent root = loader.load(); 
//        Timer.getScene().setRoot(root);
          FXMain.instance.getFrontController().render("WeatherSimple.fxml");
    }
   
}
