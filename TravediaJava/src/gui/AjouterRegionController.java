/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AjouterRegionController implements Initializable {

    @FXML
    private Button btnup;
    @FXML
    private ImageView viewimage;
    @FXML
    private Button btn;
    @FXML
    private TextField nom;
final FileChooser fc = new FileChooser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleuploadimage(ActionEvent event) {
        fc.setTitle("my uploaded image");
        fc.setInitialDirectory(new File (System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));
        File file =fc.showOpenDialog(null);
        if (file != null){
            viewimage.setImage(new Image(file.toURI().toString()));
        }else {
            System.out.println("image invalide");
        }
    }

    @FXML
    private void AjouterRegion(ActionEvent event) {
         Region r = new Region();
       // File file =fc.showOpenDialog(null);
        r.setNom(nom.getText());
        r.setImage(viewimage.get);
       // r.setImage(viewimage.setImage(new Image(file.toURI().toString())));
           RegionService ps= new RegionService();
        ps.ajouter(r);
    }
    
}
