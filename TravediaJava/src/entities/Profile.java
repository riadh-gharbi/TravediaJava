/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


/**
 *
 * @author Ibtihel
 */
public class Profile {
    
    private int profileId,evaluation;
    private String image,description;

    public Profile() {
    }

    
    
    public Profile(int profileId, int evaluation, String image, String description) {
        this.profileId = profileId;
        this.evaluation = evaluation;
        this.image = image;
        this.description = description;
    }
    
    

    public Profile(int evaluation, String image, String description) {
        this.evaluation = evaluation;
        this.image = image;
        this.description = description;
    }

    

    public int getProfileId() {
        return profileId;
    }

    public void setprofileId(int profileId) {
        this.profileId = profileId;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    
    
    
    
    
    
    
}
