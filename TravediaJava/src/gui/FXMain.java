package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.stripe.Stripe;
import entities.Paiement;
import entities.Reclamation;
import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.PaiementService;
import services.ReclamationService;
/**
 *
 * @author riadh
 */
public class FXMain extends Application {
    
    
    public static Stage primaryStage;
    private AnchorPane
            dashboard;
    private ObservableList<Reclamation> reclamationData = FXCollections.observableArrayList();
    private ObservableList<Paiement> paiementData = FXCollections.observableArrayList();
    private ReclamationService serv;
    private PaiementService pserv;
    public Dashboard1Controller dashboardController;
    private DashboardController backController;
    private FrontController frontController;
    
    public FrontController getFrontController()
    {
        return frontController;
    }

    public DashboardController getBackController() {
        return backController;
    }

    public void setBackController(DashboardController backController) {
        this.backController = backController;
    }

    public Dashboard1Controller getDashboard1Controller() {
        return dashboardController;
    }

    public void setDashboard1Controller(Dashboard1Controller dashboard1Controller) {
        this.dashboardController = dashboard1Controller;
    }
    
    public AnchorPane getRootLayout()
    {
        return dashboard;
    }
    public static FXMain instance;
    public FXMain()
    {
        Stripe.apiKey = "sk_test_51KT8ejAISKORykYshnnbQcDPyMdeStYUi7Xtp05Lh1or86C6AHB8K3NsvA6CmiFXv4obHCq1p3gxp8oV8YHNizZ000pllSDFVs";

        serv = new ReclamationService();
        pserv= new PaiementService();
        UpdatePaiement();
        Recuperer();
     synchronized(FXMain.class){
        if(instance != null) throw new UnsupportedOperationException(
                getClass()+" is singleton but constructor called more than once");
        instance = this;
    }
    }
    
    public void Recuperer()
    {
        List<Reclamation> list = serv.recuperer();
        reclamationData = FXCollections.observableArrayList();
        for (Reclamation r : list)
        {
            
            reclamationData.add(r);
        }
    }
    
    public void UpdatePaiement()
    {
        List<Paiement> listPay = pserv.recuperer();
        //System.out.println(listPay.toString());
        paiementData = FXCollections.observableArrayList();
        for (Paiement p: listPay)
        {
            paiementData.add(p);
        }
        
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        FXMain.primaryStage = primaryStage;
        this.primaryStage.setTitle("Travedia");
        primaryStage.setResizable(false);
        InitRoot();
        //InitRootBack();
        
        
        
        //InitTest();
        //AfficherReclamations();

    }
    
    public void InitRoot()
    {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("Front.fxml"));
            
            dashboard = loader.load();
            frontController = loader.getController();
           // Dashboard1Controller dashboard1Controller = loader.getController();
           // dashboardController = dashboard1Controller;
           
           // dashboard1Controller.setFxm(this);
            //Afficher la scene 
            Scene scene = new Scene(dashboard);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (IOException e)
        {
            System.out.println("InitRootError : "+e.getMessage());
        }
    }
     public void InitTest()
    {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("Front.fxml"));
            
            dashboard = loader.load();
           
            //Afficher la scene 
            Scene scene = new Scene(dashboard);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (IOException e)
        {
            System.out.println("InitRootError : "+e.getMessage());
        }
    }
    
    
        public void InitRootBack()
    {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(FXMain.class.getResource("dashboard.fxml"));
            
            dashboard = loader.load();
            DashboardController backCon = loader.getController();
            this.backController = backCon;
            //backController.setFxm(this);
            //Afficher la scene 
            Scene scene = new Scene(dashboard);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        } catch (IOException e)
        {
            System.out.println("InitRootError : "+e.getMessage());
        }
    }
    
    public void AfficherReclamations()
    {
        try {
            FXMLLoader loader=  new FXMLLoader();
            loader.setLocation(getClass().getResource("AddReclamation.fxml"));
            loader.load();
            //AnchorPane afficherRec =(AnchorPane) loader.load();
            
            //dashboard.setCenter(afficherRec);
           // ReclamationController controller = loader.getController();
           // controller.setFXMain(this);
        } catch (IOException ex) {
            System.err.println("Error Afficher Reclamation " + ex.getMessage() + " " + ex.getCause()+" "+ex.getLocalizedMessage());
        }
        
    }
    
    public Stage getPrimaryStage()
    {
        return primaryStage;
    }
    
    public void setPrimaryStage(Stage pStage)
    {
        this.primaryStage = pStage;
    }
    
    
    public static void main(String[] args) {
        launch();
    }
    
    public ObservableList<Reclamation> getReclamationData() {
		return reclamationData;
	}
    
    
    public boolean showRecEditDialog(Reclamation reclamation,RecItemController recItemController) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditReclamation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        EditReclamationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setReclamation(reclamation);
        controller.setRecItemController(recItemController);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        System.err.println("error dialog "+e.getMessage()+" "+ e.getCause());
        return false;
    }
   
    }
    public boolean showRecEditBackDialog(Reclamation reclamation, RecItemBackController recItemBackController) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("EditReclamation.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        EditReclamationController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setReclamation(reclamation);
        controller.setRecItemBackController(recItemBackController);

        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        System.err.println("error dialog "+e.getMessage()+" "+ e.getCause());
        return false;
    }
    
    }
    
    public boolean showPayEditBackDialog(Paiement paiement,PayItemBackController payItemBackController) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editPaiementBack.fxml"));
        VBox page = (VBox) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier Paiement");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        EditPaiementBackController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPaiement(paiement);
        controller.setPaiementBackController(payItemBackController);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        System.err.println("error dialog "+e.getMessage()+" "+ e.getCause());
        return false;
    }
    
    }
 public boolean showPayDialog(ListPayController payController) {
    try {
        // Load the fxml file and create a new stage for the popup dialog.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Pay.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Create the dialog Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Modifier Paiement");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Set the person into the controller.
        PayController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        //controller.setPaiement(paiement);
        controller.setListPayController(payController);
        // Show the dialog and wait until the user closes it
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        System.err.println("error dialog "+e.getMessage()+" "+ e.getCause());
        return false;
    }
    
    }

    ObservableList<Paiement> getPaiementData() {
        return paiementData;
    }
}
