/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import GUI.PostController;
import com.jfoenix.controls.JFXTextArea;
import entities.Commentaire;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import services.ServiceCommentaire;

/**
 * FXML Controller class
 *
 * @author riadh
 */
public class AddCommentController implements Initializable {

    @FXML
    private JFXTextArea commentText;
    private int postId;
    private ServiceCommentaire cs;
    private GUI.PostController pc;

    public void setPc(PostController pc) {
        this.pc = pc;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cs = new ServiceCommentaire();
    }    
    public void setPostId(int id)
    {
        postId=id;
    }
    @FXML
    private void envoyerCommentaire(ActionEvent event) {
        Commentaire c = new Commentaire(postId,commentText.getText(), new java.sql.Date(System.currentTimeMillis()));
        cs.ajouter(c);
        pc.addComment(c, postId);
        
    }
    
}
