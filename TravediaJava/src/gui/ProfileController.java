/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class ProfileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void goToProfilePage() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        /* Main.primaryStage.setScene(new Scene(root));
        Main.primaryStage.show();
        Main.primaryStage.setFullScreen(true);*/
    }
}
