/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Evenement;
import entities.Categorie;
import java.io.File;
import java.sql.Date;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.EvenementService;
import javafx.stage.FileChooser;
import services.CategorieService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AddEventController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField description;
    @FXML
    private DatePicker datefin;
    @FXML
    private DatePicker datedeb;
    @FXML
    private TextField image;
    private String path;
    @FXML
    private ComboBox<?> category;
    @FXML
    private ImageView imageaff;
    File selectedFile;
    CategorieService pexp = new CategorieService();
    private ObservableList<String> stationsList = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TO DO 
       
        //category.setAccessibleText(pexp.getNom());
    }    

    @FXML
    private void ajouterEv(ActionEvent event) throws IOException {
         Evenement evn = new Evenement();
        evn.setNom(name.getText());
        evn.setDescription(description.getText());
       // evn.setDatedeb(datedeb.getD);
       evn.setImage(path);
       //evn.setCategorie(category.get);
       evn.setDatedeb(Date.valueOf(datedeb.getValue()));
       evn.setDatefin(Date.valueOf(datefin.getValue()));
       evn.setCategorie(2);
        EvenementService evns = new EvenementService();
        evns.ajouter(evn);
        Parent root = FXMLLoader.load(getClass().getResource("showEvent.fxml"));
        name.getScene().setRoot(root);
    }

    @FXML
    private void upload(ActionEvent event) throws MalformedURLException{
         FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.home") ));
        fc.setTitle("Veuillez choisir l'image");
        fc.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Image", ".jpg", ".png"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg")
        );
        selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {

            path = selectedFile.getName();
//    
            image.setText(path);
            Image imagea = new Image(selectedFile.toURI().toString());
           imageaff.setImage(imagea) ;
    }
    }

    @FXML
    private void ShowEventList(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("showEvent.fxml"));
        name.getScene().setRoot(root);
    }
    
}
    

