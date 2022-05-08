/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import services.ReclamationService;
import util.Session;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class ListRecTestController implements Initializable {

    @FXML
    private BorderPane Table;
    @FXML
    private HBox FieldNames;
    @FXML
    private VBox ObjectVBox;
    @FXML
    private GridPane listGrid;
    
    //private Dashboard1Controller dashboard1Controller;
    private FrontController frontController;
    private List<Reclamation> reclist=  new ArrayList<>();
    @FXML
    private VBox vList;
    @FXML
    private BorderPane rootParent;
    
    private FXMain fxm;
    @FXML
    private JFXTextField rechercheTextField;
    
    

    public FXMain getFxm() {
        return fxm;
    }

    public void setFxm(FXMain fxm) {
        this.fxm = fxm;
    }

//    public void setDashboard1Controller(Dashboard1Controller dashboard1Controller) {
//        this.dashboard1Controller = dashboard1Controller;
//    }
    public void setFrontController(FrontController frontController){
        this.frontController = frontController;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService rs = new ReclamationService();
        reclist = rs.recupererParUser(Session.getUser().getId());
        int row = 0;
        
        for(Reclamation r: reclist)
        {
            
//            Label sujet= new Label(r.getSujet());
//            Label contenu = new Label(r.getContenu());
//            Label etat=new Label(r.getEtat_reclamation());
//            Label user = new Label("Utilisateur "+r.getUser_id().toString());
//            Label response = new Label(rs.recupererReponse(r.getId()).getContenu());
//            RowConstraints rc = new RowConstraints();
//            rc.setMinHeight(50.0);
//            listGrid.getRowConstraints().add(rc);
//
//            listGrid.add(sujet, 0, row);
//            listGrid.add(contenu, 1, row);
//            listGrid.add(etat, 2, row);
//            listGrid.add(user, 3, row);
//            listGrid.add(response, 4, row);
//            JFXButton buttonModif = new JFXButton("Modifier");
//            JFXButton buttonSuppr = new JFXButton("Supprimer");
//            listGrid.add(buttonModif,5,row);
//            listGrid.add(buttonSuppr,6,row);
//            row++;
            try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("recItem.fxml"));
            Parent root = loader.load();
            vList.getChildren().add(root);
            RecItemController recItemController;
            recItemController = loader.getController();
            //recItemController.setDashboard1Controller(dashboard1Controller);
            //recItemController.setFxm(fxm);
            recItemController.setReclamation(r, rs.recupererReponse(r.getId()));
            } catch (IOException ex)
            {
                System.err.println("List Rec Error : " + ex.getMessage() + " " + ex.getCause());
            }
            
        }
        

    }    
    
   public void sb()
   {
       frontController.test();
   }

    @FXML
    private void goAjoutRec(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AddReclamation.fxml"));
            Parent root = loader.load();
//            if (dashboard1Controller != null)
//            {
//                dashboard1Controller.getRecCont().getChildren().clear();
//                dashboard1Controller.getRecCont().getChildren().add(root);
//                
//            }
            frontController.getAnchorPane().getChildren().clear();
            frontController.getAnchorPane().getChildren().add(root);
            AddReclamationController addReclamationController = loader.getController();
            //addReclamationController.setDashboard1Controller(dashboard1Controller);
            addReclamationController.setFrontController(frontController);
            addReclamationController.setPrevious("listRecTest.fxml");
           
        } catch (IOException ex) {
            System.err.println("Error Ajout Rec button "+ ex.getMessage() + " "+ ex.getCause());
        }
    }


    public boolean CompareRecItems(String searchTerm, String sujet,String contenu,String etat, String user,String reponse)
    {
        String regex = "^"+searchTerm;
        System.err.println(regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null ;
        Matcher matcher1 = null;
        Matcher matcher2 = null;
        Matcher matcher3 = null;
        if(searchTerm ==null) return true;
        if(sujet!=null){
        matcher = pattern.matcher(sujet);}
        if(contenu!=null){
        matcher1 = pattern.matcher(contenu);}
        if(etat!=null){
        matcher2 = pattern.matcher(etat);}
        if(user!=null){
        matcher3 = pattern.matcher(user);}
       // Matcher matcher4 = pattern.matcher(reponse);
        //System.out.println(matcher.matches() || matcher1.matches() || matcher2.matches() || matcher3.matches());
        return matcher.find() || matcher1.find() || matcher2.find() || matcher3.find();
    }

    @FXML
    private void textAction(KeyEvent event) {
         //Get recherche text string
        ReclamationService rs = new ReclamationService();
        vList.getChildren().clear();
          reclist =rs.recuperer();
        System.err.println("TEXT ACTION");
       String searchTerm;

        searchTerm = rechercheTextField.getText();

      System.out.println("Test Search Term "+ searchTerm);
      
        for (Reclamation r: reclist)
       {
          // System.out.println(r.toString());
           
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("recItem.fxml"));
            Parent root = loader.load();
       
//            //compare strings here
            if( CompareRecItems(searchTerm.toLowerCase(), r.getSujet().toLowerCase(),
                   r.getContenu().toLowerCase()
                    , r.getEtat_reclamation().toLowerCase()
                    , String.valueOf(r.getUser_id()).toLowerCase()
                    , rs.recupererReponse(r.getId()).getContenu())){
                
                
            
                
                System.out.println("Testing search Term "+ searchTerm);
            vList.getChildren().add(root);
             RecItemController recItemController;
            recItemController = loader.getController();
       
            recItemController.setReclamation(r, rs.recupererReponse(r.getId()));
           
            }
            } catch (IOException ex)
            {
                System.err.println("List Rec Error : " + ex.getMessage() + " " + ex.getCause());
            }
     
       }
        
    }
    
    
    
}
