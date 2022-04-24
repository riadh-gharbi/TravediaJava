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
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.EvenementService;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import services.CategorieService;


/**
 * FXML Controller class
 *
 * @author user
 */
public class EditEventController implements Initializable {

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
    private ComboBox<String> category;
    @FXML
    private ImageView imageaff;
    File selectedFile;
    CategorieService pexp = new CategorieService();
    private ObservableList<String> stationsList = FXCollections.observableArrayList();
    List<Categorie> listcat;
    @FXML
    private Label errorNom;
    @FXML
    private Label errorDesc;
    @FXML
    private ImageView checkNom;
    private boolean verificationName=true;
    @FXML
    private ImageView checkDesc;
    private boolean verificationDesc=true;
    @FXML
    private Label errorDatedeb;
    @FXML
    private Label errorDatefin;
    @FXML
    private Label errorCateg;
    private Label errorImage;
    @FXML
    private ImageView checkDatedeb;
    private boolean verificationDatedeb=true;
    @FXML
    private ImageView checkDatefin;
    private boolean verificationDatefin=true;
    @FXML
    private ImageView checkCateg;
    private boolean verificationCateg=true;
    private boolean verificationImage=true;

    Evenement currentEvent;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TO DO 
        checkNom.setVisible(false);
        errorNom.setVisible(false);
        checkDesc.setVisible(false);
        errorDesc.setVisible(false);
        checkDatedeb.setVisible(false);
        errorDatedeb.setVisible(false);
        checkDatefin.setVisible(false);
        errorDatefin.setVisible(false);
        checkCateg.setVisible(false);
        errorCateg.setVisible(false);
        listcat = new CategorieService().recuperer();
        for (Categorie categorie : listcat) {
            category.getItems().add(categorie.getNom());
        }
        //category.setAccessibleText(pexp.getNom());
    }    

    @FXML
    private void ajouterEv(ActionEvent event) throws IOException {
        if(datedeb.getValue() == null)
        {
            System.out.println("please selected date deb");
            errorDatedeb.setText("please select date");
            errorDatedeb.setVisible(true);
            return;
        }
        else
        {
            errorDatedeb.setVisible(false);
            checkDatedeb.setVisible(true);
        }
        if(datefin.getValue() == null)
        {
            System.out.println("please selected date fin");
            errorDatefin.setText("please select date");
            errorDatefin.setVisible(true);
            return;
        }
        if(Date.valueOf(datedeb.getValue()).before(Date.from(LocalDate.now().atStartOfDay().toInstant(ZoneOffset.UTC))))
        {
            System.out.println("should be more than today");
            errorDatedeb.setVisible(true);
            return;
        }
        else
        {
            errorDatedeb.setVisible(false);
            checkDatedeb.setVisible(true);
        }
       if(Date.valueOf(datedeb.getValue()).after(Date.valueOf(datefin.getValue())))
       {
           System.out.println("end date need to be > datedeb");
           errorDatefin.setVisible(true);
           //erreur
           return;
       }
       else
        {
            errorDatefin.setVisible(false);
            checkDatefin.setVisible(true);
        }
       if(category.getSelectionModel().getSelectedIndex() == -1)
       {
           System.out.println("please select a category");
           errorCateg.setVisible(true);
           return;
       }
       else
        {
            errorCateg.setVisible(false);
            checkCateg.setVisible(true);
        }
        System.out.println("selected category is " + listcat.get(category.getSelectionModel().getSelectedIndex()));
         Evenement evn = currentEvent;
        evn.setNom(name.getText());
        if(name.getText().isEmpty()){
            errorNom.setVisible(true);
            return;
        }
        else
        {
            errorNom.setVisible(false);
            checkNom.setVisible(true);
        }
        evn.setDescription(description.getText());
        if(description.getText().isEmpty()){
            errorDesc.setVisible(true);
            return;
        }
        else
        {
            errorDesc.setVisible(false);
            checkDesc.setVisible(true);
        }
        if(false && selectedFile == null)
        {
            errorImage.setVisible(true);
            return;
        }
        String fileName = currentEvent.getImage();
        if(selectedFile != null)
        {
            try {
                fileName = System.currentTimeMillis() + "." + FilenameUtils.getExtension(selectedFile.getName());
                File destFile = new File(AddEventController.imageDir + "/" + fileName);
                FileUtils.copyFile(selectedFile, destFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       evn.setImage(fileName);
       //evn.setCategorie(category.get);
       evn.setDatedeb(Date.valueOf(datedeb.getValue()));
       evn.setDatefin(Date.valueOf(datefin.getValue()));
       evn.setCategorie(listcat.get(category.getSelectionModel().getSelectedIndex()).getId());
        EvenementService evns = new EvenementService();
        evns.modifier(evn);
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
    public void setEvent(Evenement ev)
    {
        currentEvent = ev;
        name.setText(ev.getNom());
        description.setText(ev.getDescription());
        datefin.setValue(ev.getDatefin().toLocalDate());
        datedeb.setValue(ev.getDatedeb().toLocalDate());
        image.setText(ev.getImage());
        int comboBoxIndex = -1;
        for (Categorie l : listcat) {
            comboBoxIndex++;
            if(ev.getCategorie()== (l).getId())break;
        }
        category.getSelectionModel().select(comboBoxIndex);

        
        File imageFile = new File(AddEventController.imageDir + "/" + ev.getImage());
        Image imagea = new Image(imageFile.toURI().toString());
        imageaff.setImage(imagea) ;

    }
    
}
    

