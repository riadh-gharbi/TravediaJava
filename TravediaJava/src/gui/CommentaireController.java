/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXTextArea;
import entities.Commentaire;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author snoussi amine
 */
public class CommentaireController implements Initializable {

    @FXML
    private Label user;
    @FXML
    private JFXTextArea commentaire;
    
    Commentaire c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        commentaire.setText(c.getContenu());
        
    }    
    
    public void setCommentaire(Commentaire commentaire)
    {
        c= commentaire;
    }
    
}
