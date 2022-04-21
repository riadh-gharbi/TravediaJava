/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Destination;
import entities.Region;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
Connection cnx ;
    
    @FXML
    private ComboBox<String> combox;
    @FXML
    private Button btndest;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         try {
//            String req = "select * from Region";
//            Statement stm = cnx.createStatement();
//            ResultSet rst = stm.executeQuery(req);
//            
//            while (rst.next()) {
//             //   Users a = new Users(rst.getInt("id"));
//                
//                String xx = rst.getString(req);
//                combox.getItems().add(xx);
//            }
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    }    

    @FXML
    private void AjouterDestination(ActionEvent event) throws IOException {
         int error = 0;

        if (nom.getText().isEmpty()) {
            nom.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un nom ! ");
            al.showAndWait();
       
            error++;
            Parent root = FXMLLoader.load(getClass().getResource("AjouterDestinationn.fxml"));
               nom.getScene().setRoot(root);
        } else {
            nom.setStyle(null);
        }
        
          if (desc.getText().isEmpty()) {
            desc.setStyle("-fx-border-color: red; -fx-border-width: 2px");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez ajouter un description! ");
            al.showAndWait();
            error++;
             Parent root = FXMLLoader.load(getClass().getResource("AjouterDestinationn.fxml"));
               nom.getScene().setRoot(root);
        } else {
            desc.setStyle(null);
        }
         Destination r = new Destination();
        r.setNom(nom.getText());
        r.setDescription(desc.getText());
        r.setImage(path);
     //  r.setRegion(combox.getValue());
      //  r.setRegion(combox.getSelectionModel().getSelectedItem());
        DestinationService ps= new DestinationService();
        ps.ajouter(r);
         Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
               nom.getScene().setRoot(root);
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

    @FXML
      private void opendest(ActionEvent event) throws IOException {
        
          Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
               btndest.getScene().setRoot(root);
    }
    
}
