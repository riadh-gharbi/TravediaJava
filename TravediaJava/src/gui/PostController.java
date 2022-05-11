/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import entities.Post;
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import objects.Account;
import services.ServicePost;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import services.ServiceCommentaire;
import util.Session;

/**
 * FXML Controller class
 *
 * @author snoussi amine
 */
public class PostController implements Initializable {

    @FXML
    private HBox likeContainer;
    @FXML
    private ImageView imgProfile;
    @FXML
    private Label username;
    @FXML
    private ImageView imgVerified;
    @FXML
    private Label date;
    @FXML
    private ImageView audience;
    @FXML
    private Label caption;
    @FXML
    private ImageView imgPost;
    @FXML
    private Label nbReactions;
    @FXML
    private Label nbComments;
    @FXML
    private Label nbShares;
    @FXML
    private HBox reactionsContainer;
    @FXML
    private ImageView imgLike;
    @FXML
    private ImageView imgLove;
    @FXML
    private ImageView imgCare;
    @FXML
    private ImageView imgHaha;
    @FXML
    private ImageView imgWow;
    @FXML
    private ImageView imgSad;
    @FXML
    private ImageView imgAngry;

    /**
     * Initializes the controller class.
     */
    private long startTime = 0;
    private Reactions currentReaction;
    private Post post;
    ServicePost sp = new ServicePost();

    @FXML
    private ImageView imgReaction;
    @FXML
    private Label reactionName;
    @FXML
    private VBox commentField;

    @FXML
    public void onLikeContainerPressed(MouseEvent me) {
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased(MouseEvent me) {
        if (System.currentTimeMillis() - startTime > 500) {
            reactionsContainer.setVisible(true);
        } else {
            if (reactionsContainer.isVisible()) {
                reactionsContainer.setVisible(false);
            }
            if (currentReaction == Reactions.NON) {
                setReaction(Reactions.LIKE);
            } else {
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    public void onReactionImgPressed(MouseEvent me) {
        switch (((ImageView) me.getSource()).getId()) {
            case "imgLove":
                setReaction(Reactions.LOVE);
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        reactionsContainer.setVisible(false);
    }

    public void setReaction(Reactions reaction) {
        Image image = new Image(getClass().getResourceAsStream(reaction.getImgSrc()));
        imgReaction.setImage(image);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));

        if (currentReaction == Reactions.NON) {
            post.setTotalReactions(post.getTotalReactions() + 1);
        }

        currentReaction = reaction;

        if (currentReaction == Reactions.NON) {
            post.setTotalReactions(post.getTotalReactions() - 1);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
    }

    public void setData(Post post) {
        this.post = post;
        Image img;
        //img = new Image(getClass().getResourceAsStream(post.getAccount().getProfileImg()));
        //imgProfile.setImage(img);
        username.setText(Session.getUser().getPrenom() + " " + Session.getUser().getNom());
//        if (post.getAccount().isVerified()) {
    //       imgVerified.setVisible(true);
//        } else {
//            imgVerified.setVisible(false);
//        }

         date.setText(post.getDate().toString());
        if (post.getAudience() == PostAudience.PUBLIC) {
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        } else {
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);

        if (post.getCaption() != null && !post.getCaption().isEmpty()) {
            caption.setText(post.getCaption());
        } else {
            caption.setManaged(false);
        }

        if (post.getImage() != null && !post.getImage().isEmpty()) {
            String path = "images/" + post.getImage();
            System.out.println(path);
        Image image = new Image(getClass().getResourceAsStream(path));
        imgPost.setImage(image);
                        

          
        } else {
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }
      
        nbReactions.setText(String.valueOf(post.getTotalReactions()));
        nbComments.setText(post.getNbComments() + " comments");
        nbShares.setText(post.getNbShares() + " shares");

        currentReaction = Reactions.NON;
        setComments(post.getId());
    }
    
    private void setComments(int postID)
    {
        ServiceCommentaire sc = new ServiceCommentaire();
        List<Commentaire> listCom;
        listCom = sc.getById(postID);
        
        for(Commentaire c : listCom)
        {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Commentaire.fxml"));
                Parent comment = loader.load();
                CommentaireController cont = loader.getController();
                cont.setCommentaire(c);
                commentField.getChildren().add(comment);
            } catch (IOException ex) {
                    System.err.println("Comment affichage error : "+ ex.getMessage()+ " "+ ex.getCause());
            }
        
        }
        
        //Add comment add field
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddComment.fxml"));
        Parent addCommentField= loader.load();
            gui.AddCommentController c = loader.getController();
            c.setPc(this);
            c.setPostId(postID);
        commentField.getChildren().add(addCommentField);}
        catch (IOException ex){}
    }
    public void addComment(Commentaire c,int postID)
    {
         try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("Commentaire.fxml"));
                Parent comment = loader.load();
                CommentaireController cont = loader.getController();
                cont.setCommentaire(c);
                commentField.getChildren().remove(commentField.getChildren().size()-1);
                commentField.getChildren().add(comment);
            } catch (IOException ex) {
                    System.err.println("Comment affichage error : "+ ex.getMessage()+ " "+ ex.getCause());
            }
         
          //Add comment add field
        try{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddComment.fxml"));
        Parent addCommentField= loader.load();
            gui.AddCommentController commentAdd = loader.getController();
            commentAdd.setPc(this);
            commentAdd.setPostId(postID);
        commentField.getChildren().add(addCommentField);}
        catch (IOException ex){}
    
    }

    private Post getPost() {
        Post post = new Post();
        Account account = new Account();
        account.setName("Ahmed i");
        account.setProfileImg("/img/user.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setDate(new Date());

        post.setAudience(PostAudience.PUBLIC);
        post.setCaption(sp.getById(2).getCaption());
       // if(post.getImage() !=null){
       // String path = "/img/" + post.getImage();
        //Image image = new Image(getClass().getResourceAsStream(path));
       // imgPost.setImage(image);}
        post.setTotalReactions(sp.getById(2).getTotalReactions());
        post.setNbComments(sp.getById(2).getNbComments());
        post.setNbShares(sp.getById(2).getNbShares());
        System.out.println(post);

        return post;
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //setData(getPost());
        
    }

    @FXML
    private void commenter(ActionEvent event) {
        //ajouter Commentaire
        
    }

}
