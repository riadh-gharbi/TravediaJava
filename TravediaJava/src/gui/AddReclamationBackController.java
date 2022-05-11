/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import services.ReclamationService;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class AddReclamationBackController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;

   private String previous;
    private DashboardController dashboardController;
    @FXML
    private ChoiceBox<Utilisateur> user;
    @FXML
    private ChoiceBox<String> etat;

    public DashboardController getDashboard1Controller() {
        return dashboardController;
    }

    public void setDashboard1Controller(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
private List<Utilisateur> userList = new ArrayList<>();
    private ObservableList<Utilisateur> userObsList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        etat.setValue("En Cours");
        etat.setValue("Résolue");
        //Recupérer les utilisateurs qui ne sont pas admins
        UtilisateurService us = new UtilisateurService();
        userList = us.recuperer();
        
        for (Utilisateur u:userList)
        {
            if(!us.checkRole(u).equals("admin"))
            userObsList.add(u);
        }
        user.setItems(userObsList);
        user.setConverter(new StringConverter<Utilisateur>(){
            @Override
            public String toString(Utilisateur u)
            {
                return u.getNom() + " " + u.getPrenom();
            }
            
            @Override
            public Utilisateur fromString(String string)
            {
                return user.getItems().stream().filter(u->(u.getId()==Integer.parseInt(string))).findFirst().orElse(null);
            }
        
        });
        user.valueProperty().addListener((obs,oldval,newval)->{
            if(newval !=null){
                System.out.println("Selected User is: " + newval.getNom() + " ID: "+ newval.getId());
            }
        });
    }    

    @FXML
    private void addReclamation(ActionEvent event) {
        Reclamation r = new Reclamation();
        r.setContenu(contenu.getText());
        r.setSujet(sujet.getText());
        r.setEtat_reclamation(etat.getValue());
        //PLACEHOLDER
        r.setUser_id(user.getValue().getId());
        ReclamationService rs = new ReclamationService();
        rs.ajouter(r);
      
       FXMain.instance.Recuperer();
            //afficherRec = (AnchorPane) loader.load();
                   // FXmain.getRootLayout().setCenter(afficherRec);
                  //  ReclamationController reclamationController = loader.getController();
                 
        back();
                   // reclamationController.setFXMain(FXmain);
                 
                    

        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.show();
          
    }
    
    private FXMain FXmain;
    public void setFXMain(FXMain fxm)
    {
        this.FXmain =fxm;
        
        
    }
    
    private void back()
    {
    try {
            FXMLLoader loader= new FXMLLoader();
            
            loader.setLocation(getClass().getResource(previous));
            Parent root = loader.load();
            //dashboardController.getRecCont().getChildren().clear();
            dashboardController.getAnchor().getChildren().clear();
            dashboardController.getAnchor().getChildren().add(root);
            
            AnchorPane.setTopAnchor(root,0.0);
                AnchorPane.setBottomAnchor(root,0.0);
                AnchorPane.setRightAnchor(root, 0.0);
                AnchorPane.setLeftAnchor(root, 0.0);
        } catch (IOException ex) {
            System.err.println("Return to list error "+ ex.getMessage() + " " + ex.getCause());
        }
    
    }
    
    @FXML
    private void goBack(ActionEvent event) {
        back();
    }

    
}
