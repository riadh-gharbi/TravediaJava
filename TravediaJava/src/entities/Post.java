/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import GUI.*;
import java.util.Date;
import javafx.scene.control.ChoiceBox;
import objects.Account;

/**
 *
 * @author snoussi amine
 */
public class Post {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private Account account;
    private PostAudience audience;
    private Date date;
    private String caption;
    private String image;
    private int totalReactions;
    private int nbComments;
    private int nbShares;
    public String getImage;

    public Post() {
    }

    public Post(String caption) {
        this.caption = caption;
    }

    public Post(Object account, Object audience, Object date, Object caption, Object image, Object totalReactions, Object nbComments, Object nbShares) {
        
        this.account = (Account) account;
        this.audience = (PostAudience) audience;
        this.date = (Date) date;
        this.caption = (String) caption;
        this.image = (String) image;
        this.totalReactions = (int) totalReactions;
        this.nbComments = (int) nbComments;
        this.nbShares = (int) nbShares;
    }

    public Post(int id, Account account, PostAudience audience, Date date, String caption, String image, int totalReactions, int nbComments, int nbShares, String getImage) {
        this.id = id;
        this.account = account;
        this.audience = audience;
        this.date = date;
        this.caption = caption;
        this.image = image;
        this.totalReactions = totalReactions;
        this.nbComments = nbComments;
        this.nbShares = nbShares;
        this.getImage = getImage;
    }

    public Post(Object date, Object caption, Object image, Object totalReactions, Object nbComments, Object nbShares) {
        this.date = (Date) date;
        this.caption = (String) caption;
        this.image = (String) image;
        if (totalReactions !=null)
        this.totalReactions = (int) totalReactions;
        if(nbComments !=null)
        this.nbComments = (int) nbComments;
        if(nbShares!=null)
        this.nbShares = (int) nbShares;
    }

 
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PostAudience getAudience() {
        return audience;
    }

    public void setAudience(PostAudience audience) {
        this.audience = audience;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotalReactions() {
        return totalReactions;
    }

    public void setTotalReactions(int totalReactions) {
        this.totalReactions = totalReactions;
    }

    public int getNbComments() {
        return nbComments;
    }

    public void setNbComments(int nbComments) {
        this.nbComments = nbComments;
    }

    public int getNbShares() {
        return nbShares;
    }

    public void setNbShares(int nbShares) {
        this.nbShares = nbShares;
    }

    public Object getById(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Post{" + "account=" + account + ", audience=" + audience + ", date=" + date + ", caption=" + caption + ", image=" + image + ", totalReactions=" + totalReactions + ", nbComments=" + nbComments + ", nbShares=" + nbShares + '}';
    }

    
    

}
