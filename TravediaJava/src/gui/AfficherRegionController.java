/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Region;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.RegionService;

/**
 * FXML Controller class
 *
 * @author Ameni
 */
public class AfficherRegionController implements Initializable {

//    private TableView<Region> tablev;
//    private TableColumn<Region, String> Image;
//    private TableColumn<Region, String> Region;
    @FXML
    private Button addnew;
   // public static Region regioncon;
    @FXML
    private VBox vbox;
    @FXML
    private Button btndest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
            RegionService ps = new RegionService();
            List<Region> regions =ps.recuperer();
            ObservableList list = FXCollections.observableArrayList(regions);
          List<Node> nodes = new ArrayList<>();
//        
        for(Region c : regions){
            try{
              
                FXMLLoader loader = new FXMLLoader();
                
                loader.setLocation(getClass().getResource("ItemRegion.fxml"));
                Parent root =loader.load();
                nodes.add( root);
                vbox.getChildren().add(root);
                ItemRegionController itemController = new ItemRegionController();
                itemController = loader.getController();
                itemController.setRegion(c);
            }catch (IOException e){
                    e.printStackTrace();
                    e.getMessage();
            } }
//*****************
//                InputStream input = rset.getBinaryStream("image");
//                InputStreamReader inputReader = new InputStreamReader(input);                        
//                if(inputReader.ready())
//                {
//                    File tempFile = new File("tempFile.jpg");
//
//                    FileOutputStream fos = new FileOutputStream(tempFile);
//                    byte[] buffer = new byte[1024];
//                    while(input.read(buffer) > 0){
//                        fos.write(buffer);                        
//                    }
//                    Image image = new Image(tempFile.toURI().toURL().toString());
//                    c1.setImage(image);//right here is where you want to set your imageView with the image.
//                }     
//                companyData.add(c1);
//                        ***************************  
//        
//           Image.setCellValueFactory(new PropertyValueFactory<>("image"));
//           Region.setCellValueFactory(new PropertyValueFactory<>("nom"));
//            RegionService catser = new RegionService();

        }
        
       
        
    
      @FXML
    private void BtnAjouterRegion(ActionEvent event) throws IOException {
        
        
          Parent root = FXMLLoader.load(getClass().getResource("AjouterRegion.fxml"));
               addnew.getScene().setRoot(root);
    }
//    private void modifier(ActionEvent event) throws IOException {
////        Region p= tablev.getSelectionModel().getSelectedItem();
////        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRegion.fxml"));
////        Parent root =loader.load();
////        ModifierRegionController dc = loader.getController();
////       // dc.EditRegion(p);
////       
////        tablev.getScene().setRoot(root);
////   Region p= vbox.getSelectionModel().getSelectedItem();
////   Region p= vbox.get
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRegion.fxml"));
//        Parent root =loader.load();
//        ModifierRegionController dc = loader.getController();
//      //  dc.initDon(p);
//        vbox.getScene().setRoot(root);
//      
//    }

    @FXML
    private void opendest(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherDestination.fxml"));
               btndest.getScene().setRoot(root);
    }
   
    }    
    

