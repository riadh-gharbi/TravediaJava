/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class UserProfileController implements Initializable {

    @FXML
    private AnchorPane ProfileAnchorPane;
    @FXML
    private ImageView userProfileImage;
    @FXML
    private Label FirstNameFieldProfile;
    @FXML
    private Label LastNameFieldProfile;
    @FXML
    private Label EmailFieldProfile;
    @FXML
    private Label RoleFieldProfile;
    @FXML
    private Button changeRoleButton;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> isDoneCol;
    @FXML
    private TableColumn<?, ?> setAlarmCol;
    @FXML
    private TableColumn<?, ?> messageCol;
    @FXML
    private TableColumn<?, ?> noteCol;
    @FXML
    private TableColumn<?, ?> imageCol;
    @FXML
    private TableColumn<?, ?> startDateCol;
    @FXML
    private TableColumn<?, ?> endDateCol;
    @FXML
    private TableColumn<?, ?> creationDateCol;
    @FXML
    private TableColumn<?, ?> deleteCol;
    @FXML
    private Button nextPage;
    @FXML
    private Button previousPage;
    @FXML
    private Button newTask;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
