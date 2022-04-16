/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.DestinationService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AjouterDestinationnController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField nom;
    @FXML
    private Button btnup;
    @FXML
    private ImageView viewimage;
    @FXML
    private TextField imagepath;
    @FXML
    private TextArea desc;
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

    @FXML
    private void AjouterDestination(ActionEvent event) {
         Destination r = new Destination();
        r.setNom(nom.getText());
        r.setDescription(desc.getText());
        r.setImage(path);
       DestinationService ps= new DestinationService();
        ps.ajouter(r);
    }

    @FXML
    private void handleuploadimage(ActionEvent event) {
          fc.setTitle("my uploaded image");
        fc.setInitialDirectory(new File (System.getProperty("user.home")));
        fc.getExtensionFilters().clear();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files","*.png","*.jpg","*.gif","*.jpeg"));
//        File file =fc.showOpenDialog(null);
//        if (file != null){
//            path = selectedFile.getName();
//   
//            viewimage.setImage(new Image(file.toURI().toString()));
//        }else {
//            System.out.println("image invalide");
//        }
   selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            path = selectedFile.getName();
//    
            imagepath.setText(path);
            Image imagea = new Image(selectedFile.toURI().toString());
           viewimage.setImage(imagea) ;

        }
    }
    
}
