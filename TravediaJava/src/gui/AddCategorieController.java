/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Categorie;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import org.controlsfx.control.Notifications;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import services.CategorieService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddCategorieController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
   private TextField image;
    private String path;
     @FXML
    private ImageView imageaff;
     File selectedFile;
    @FXML
    private ImageView nomCheck;
    private boolean verificationName=true;
    @FXML
    private Label error;

    
    public static String imageDir = "C:\\xampp\\htdocs\\Travedia\\public\\front\\images\\uploads\\event_picture";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       nomCheck.setVisible(false);
        error.setVisible(false);
    }    

    @FXML
    private void ajoutCat(ActionEvent event) throws IOException {
        Categorie cat = new Categorie();
        cat.setNom(nom.getText());
        //cat.setImage(image.getText());
        CategorieService catser = new CategorieService();
        if(nom.getText().isEmpty())
        {
            error.setText("Nom catgorie invalide");
            error.setVisible(true);
            return;
        }
        String masque = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(nom.getText());
        if(!controler.matches())
        {
            error.setText("Nom catgorie invalide");
            error.setVisible(true);
            return;
        }
        else
        {
            error.setVisible(false);
            nomCheck.setVisible(true);
        }
        if(selectedFile == null)
        {
            return;
        }
        String fileName = "";
        try {
            fileName = System.currentTimeMillis() + "." + FilenameUtils.getExtension(selectedFile.getName());
            File destFile = new File(imageDir + "/" + fileName);
            FileUtils.copyFile(selectedFile, destFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        cat.setImage(fileName);
        
        // notif
        //Image img = new Image("/images/check-circle.png");
        Notifications notificationBuilder = Notifications.create()
                .title("Confirmation")
                .text("Added successfully !!")
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

        
        catser.ajouter(cat);
        Parent root = FXMLLoader.load(getClass().getResource("ShowCategorie.fxml"));
        nom.getScene().setRoot(root);}
    

    @FXML
    private void upload(ActionEvent event) throws MalformedURLException {
      //  System.out.println(categorie.getValue());
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
    private void verifImage(ActionEvent event) {
    }
    @FXML
    private void verifNom(KeyEvent event) {
      /* int nbNonChar = 0;
            for (int i = 1; i < nom.getText().trim().length(); i++) {
                char ch = nom.getText().charAt(i);
                if (!Character.isLetter(ch)) {
                    nbNonChar++;
                }
            }

            if (nbNonChar == 0 && nom.getText().trim().length() >=3) {
            nomCheck.setImage(new Image("/images/yesMark.png"));
            
            verificationName = true;
            } else {
              nomCheck.setImage(new Image("/images/xMark.png"));
                
                 verificationName = false;

            }*/
    }

    @FXML
    private void ShowCategList(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowCategorie.fxml"));
        Parent root = loader.load(); 
        nom.getScene().setRoot(root);
    }

    @FXML
    private void front(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
        Parent root = loader.load(); 
        nom.getScene().setRoot(root);
    }
    
    
}
