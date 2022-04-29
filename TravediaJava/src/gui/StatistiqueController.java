/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entities.Evenement;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import services.CategorieService;
import services.EvenementService;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatistiqueController implements Initializable {

    @FXML
    private PieChart graph;
    EvenementService e = new EvenementService();
    CategorieService c = new CategorieService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Evenement> list = e.getNumberOfEventsByCat();
        list.stream().map((prod) -> {
            int number = prod.getCount();
            String name = c.find(prod.getCategorie()).getNom() + " (" + number + ") ";
            PieChart.Data slice = new PieChart.Data(name, number);
            return slice;
        }).forEachOrdered((slice) -> {
            graph.getData().add(slice);
        });
    }    

    @FXML
    private void GoToEvents(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowEvent.fxml"));
        Parent root = loader.load(); 
        graph.getScene().setRoot(root);
    }
    }    
    

