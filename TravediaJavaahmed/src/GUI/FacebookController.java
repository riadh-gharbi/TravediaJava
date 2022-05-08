/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.Post;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.ServicePost;

/**
 * FXML Controller class
 *
 * @author Cooler Master
 */
public class FacebookController implements Initializable {

    @FXML
    private TextField Caption;
    @FXML
    private Button Post;
    Connection con;
    ServicePost sp = new ServicePost();
    PreparedStatement pst;
    @FXML
    private ChoiceBox<String> account;
    private String[] acc = {"Public","Friends"};
    @FXML
    private VBox vBox;
    
    
    
    File file;
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        account.getItems().addAll(acc);
        List<Post> listPost =sp.getAll();
        for (Post p: listPost){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Post.fxml"));
                Parent root = loader.load();
                vBox.getChildren().add(root);
                PostController postController = loader.getController();
                postController.setData(p);
            } catch (IOException ex) {
                Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    public void addPost(Post p)
    {
        try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Post.fxml"));
                Parent root = loader.load();
                vBox.getChildren().add(1,root);
                PostController postController = loader.getController();
                postController.setData(p);
            } catch (IOException ex) {
                Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
            }
    
    }
    
    @FXML
    void Post (ActionEvent event) throws FileNotFoundException, IOException {
        String caption = Caption.getText();
        String audience = account.getValue();
        Date date = new Date(System.currentTimeMillis());
        Post t = new Post(caption);
        t.setDate(new Date(System.currentTimeMillis()));
        //t.setAudience();
       // t.setAudience(PostAudience.valueOf(audience));
        t.setDate(date);
        t.setImage(file.getName());
        sp.ajouter(t);
       
        
        
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/travedia","root","");
//            pst = con.prepareStatement("insert into post(audience,caption,date)values(?,?,?)");
//            pst.setString(1, audience);
//            pst.setString(2, caption);
//            pst.setDate(3,new java.sql.Date( date.getTime()));
            
//             FileInputStream fl = new FileInputStream(file);
//
//                byte[] data = new byte[(int) file.length()];
//                String fileName = file.getName();
//                String path = fileName;
//                fl.read(data);
//                fl.close();
//            int status = pst.executeUpdate();
            addPost(t);
            
           //if(status==1)
           {JOptionPane.showMessageDialog(null, "Post added");
           Caption.setText("");
           Caption.requestFocus();
           } 
//           else 
//           {   
//               {JOptionPane.showMessageDialog(null, "Post failed");
//           
//           }
          
        
        
    }
    /*@FXML
    void Upload(ActionEvent event) {
        String dialogTitle = "Image Loader";
      
       try{
          FXMLLoader fxmlloader = new FXMLLoader();
           fxmlloader.setLocation(getClass().getResource("imageloader.fxml"));
           //Parent root = fxmlloader.load();
            Pane imageloaderDialogPane = fxmlloader.load();
           
       } catch (IOException ex) {
            Logger.getLogger(FacebookController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

        @FXML
    private File chooseImage(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\TravediaJava-ahmed\\TravediaJavaahmed\\src\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    java.nio.file.Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        System.out.println(file.getPath());
        
        return file;
    }
    
    
    

    
    
}
