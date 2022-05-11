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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
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
        
         //notif
        Notifications notificationBuilder = Notifications.create()
                .title("Confirmation")
                .text("Modified successfully !!")
                .graphic(null)
                .hideAfter(Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("notiff");
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        notificationBuilder.showConfirm();
        
        CategorieService catser = new CategorieService();
        catser.modifier(currentCategorie);
                FXMain.instance.getBackController().render("ShowCategorieSimple.fxml");

    }

     public void setCategorie(Categorie cat){
        File imageFile = new File(AddCategorieController.imageDir + "/" + cat.getImage());
        Image imagea = new Image(imageFile.toURI().toString());
        imageaff.setImage(imagea) ;
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

    @FXML
    private void ShowCategList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCategorieSimple.fxml"));
        Parent root = loader.load(); 
        nom.getScene().setRoot(root);
    }

    @FXML
    private void Exit(ActionEvent event) throws IOException {
               FXMain.instance.getBackController().render("ShowCategorieSimple.fxml");

    }
    
}
