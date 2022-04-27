/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.UtilisateurService;

/**
 * FXML Controller class
 *
 * @author Ibtihel
 */
public class DashboardController implements Initializable {

    @FXML
    TableView<Utilisateur> tableView;
    @FXML
    private TableColumn<Utilisateur, String> nomIdCol;
    @FXML
    private TableColumn<Utilisateur, String> prenomIdCol;
    @FXML
    private TableColumn<Utilisateur, String> emailIdCol;
    @FXML
    private TableColumn<Utilisateur, String> roleIdCol;
    @FXML
    private TableColumn<Utilisateur, String> langueIdCol;
    @FXML
    private TableColumn<Utilisateur, String> actionIdCol;

    ObservableList<Utilisateur> usersList = FXCollections.observableArrayList();
    UtilisateurService us = new UtilisateurService();
    Utilisateur selectedUser = null;

    @FXML
    private Button logoutbut;

    /* public void goToProfile() throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
            loginbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        /* Parent roottocompte = FXMLLoader.load(getClass().getResource("UserProfile.fxml"));
        Scene scene = new Scene(roottocompte);
        primaryStage.setTitle("Profile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    @FXML
    private void SignOut() throws IOException {
        us.logout();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("user log.fxml"));
            logoutbut.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void loadUserData() {
        List<Utilisateur> user = us.recuperer();
        System.out.println("list1" + user);
        usersList = FXCollections.observableArrayList(user);
        System.out.println("list2" + user);
        //tableView.setItems(usersList);
        System.out.println("listuser3" + usersList);

        nomIdCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomIdCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        emailIdCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleIdCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
        langueIdCol.setCellValueFactory(new PropertyValueFactory<>("langue"));
        System.out.println(user.get(0).getLangue());
        System.out.println("list4" + nomIdCol);

        Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {

                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button blockButton = new Button();
                        blockButton.setText("Bloquer");
                        blockButton.setStyle("-fx-font-size:15;FontWeight.BOLD;-fx-text-fill:white;-fx-background-color:#f5631d; -fx-background-radius:20px;-fx-border-radius:20px");
                        blockButton.setPrefSize(80, 20);

                        blockButton.setOnMouseClicked((MouseEvent event) -> {

                            selectedUser = tableView.getSelectionModel().getSelectedItem();
                            if (selectedUser == null) {
                                System.out.println("selectionni haja");
                            } else {
                                if (selectedUser.getIs_blocked() == false) {
                                    us.blockUser(selectedUser.getId());
                                } else {
                                    us.unblockUser(selectedUser.getId());
                                }
                            }

                        });

                        HBox managebtn = new HBox(blockButton);
                        managebtn.setStyle("-fx-alignment:center");
                        //HBox.setMargin(blockButton, new Insets(2, 2, 0, 3));

                        setGraphic(managebtn);
                        setText(null);

                    }
                }
            };

            return cell;
        };
        actionIdCol.setCellFactory(cellFoctory);
        tableView.setItems(usersList);
        System.out.println("list57" + tableView);
    }

    /* private void loadUserData(int sizeUser, int startUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.loadUserData();
        //List<Utilisateur> usersList = us.recuperer();
        //ObservableList list = FXCollections.observableArrayList(user);
        //  tableView.setItems(list);
    }
}
