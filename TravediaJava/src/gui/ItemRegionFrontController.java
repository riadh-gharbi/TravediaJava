/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class ItemRegionFrontController implements Initializable {

 private Region region = null;
 Region currentRegion;
    FrontController controller;
 @FXML
    private Label nom;
    @FXML
    private VBox vboxx;
    @FXML
    private HBox hboxx;
    @FXML
    private Hyperlink link;
    @FXML
    private ImageView imgregion;
    @FXML
    private Label image;
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Rectangle clip = new Rectangle(
                imgregion.getFitWidth(), imgregion.getFitHeight()
            );
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            imgregion.setClip(clip);
    } 
    public void setParentController(FrontController contr)
    {
        controller = contr;
    }
     void setData(Region r) {
        currentRegion = r;
        nom.setText(r.getNom());
     //    String url = getClass().getResource("src/images/images" + r.getImage()).toString();
     //   imgregion.setImage(new Image(url, true));
       // String url = getClass().getResource("images/images/face27.jpg").toString();
    //   String url = getClass().getResource("images/images/face27.jpg" + region.getImage()).toString();
     //  imgregion.setImage(new Image(url, true));
     
    }

//    private void AfficherDestination(MouseEvent event) throws IOException {
//        try{
//          Parent root = FXMLLoader.load(getClass().getResource("DestinationFront.fxml"));
//               link.getScene().setRoot(root);
//         //  FXMLLoader loader = new FXMLLoader(getClass().getResource("DestinationFront.fxml"));
//       //     Parent root = loader.load();
////            DestinationFrontController cnt = loader.getController();
////            cnt.
//          // nom.getScene().setRoot(root);
//      }catch (IOException ex) {
//           System.out.println(ex.getMessage());
//       }
//    }

    @FXML
    private void AfficherDestination(ActionEvent event) {
          try{
          Parent root = FXMLLoader.load(getClass().getResource("DestinationFront.fxml"));
               link.getScene().setRoot(root);
         //  FXMLLoader loader = new FXMLLoader(getClass().getResource("DestinationFront.fxml"));
       //     Parent root = loader.load();
//            DestinationFrontController cnt = loader.getController();
//            cnt.
          // nom.getScene().setRoot(root);
      }catch (IOException ex) {
           System.out.println(ex.getMessage());
       }
    }
    
}
