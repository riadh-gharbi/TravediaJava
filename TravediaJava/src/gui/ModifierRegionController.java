/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ModifierRegionController implements Initializable {

    @FXML
    private Button btnedit;
    @FXML
    private TextField nom;
    @FXML
    private Button btnupedit;
    @FXML
    private ImageView viewimageedit;
    @FXML
    private TextField imagepathedit;
final FileChooser fc = new FileChooser();
private String path ; 
File selectedFile;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
//      public void EditRegion(Region p) throws IOException{
//       nom.setText(p.getNom());
//     
//        RegionService ps= new RegionService();
//        ps.modifier(p);
//          Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
//               nom.getScene().setRoot(root);
//    }


    @FXML
    private void handleuploadimage(ActionEvent event) {
         fc.setTitle("my uploaded image");
        fc.setInitialDirectory(new File (System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));

   selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            path = selectedFile.getName();
            imagepathedit.setText(path);
            Image imagea = new Image(selectedFile.toURI().toString());
           viewimageedit.setImage(imagea) ;
        }
    }
  public void initDon(Region r) {
    	
    	nom.setText(r.getNom());
    	
    }
    @FXML
    private void EditRegion(ActionEvent event , Region r) throws IOException {
       // nom.setText(p.getNom());
                
        RegionService ps= new RegionService();
        r.setNom(nom.getText());
        ps.modifier(r);
          Parent root = FXMLLoader.load(getClass().getResource("AfficherRegion.fxml"));
               nom.getScene().setRoot(root);
    }

  
}
