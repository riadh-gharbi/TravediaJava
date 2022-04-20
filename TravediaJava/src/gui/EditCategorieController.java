/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class EditCategorieController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private ImageView imageaff;
    @FXML
    private TextField image;
    File selectedFile;
    private String path;
    Categorie currentCategorie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void edit(ActionEvent event) throws IOException {
        //Categorie cat = new Categorie();
        currentCategorie.setNom(nom.getText());
        currentCategorie.setImage(path);
        CategorieService catser = new CategorieService();
        catser.modifier(currentCategorie);
        Parent root = FXMLLoader.load(getClass().getResource("ShowCategorie.fxml"));
        nom.getScene().setRoot(root);
    }

     public void setCategorie(Categorie cat){
        currentCategorie = cat;
        nom.setText(cat.getNom());
        image.setText(cat.getImage());
        
    }
    
    @FXML
    private void upload(ActionEvent event) {
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
    
}
