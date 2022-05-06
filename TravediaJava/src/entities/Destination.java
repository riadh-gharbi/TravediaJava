/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ameni
 */
public class Destination {
     int id;
     String nom;
     String description ;
     String image ;
     private int id_region;
     String region ;
     String latitude;
     String longitude;

    public Destination(int id, String nom, String description, String image,int id_region,String latitude,String longitude) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id_region=id_region;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Destination(String nom, String description, String image,String latitude,String longitude) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Destination(String nom, String description, String image, int id_region,String latitude,String longitude) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.id_region = id_region;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public Destination(String nom, String description, String image, String region,String latitude,String longitude) {
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.region = region;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

  


    public Destination() {
    }

//    public Destination(String toString, String toString0) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
  /**
     * @return the id_region
     */
    public int getId_region() {
        return id_region;
    }

    /**
     * @param id_region the id_region to set
     */
    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

//    @Override
//    public String toString() {
//        return "Destination{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", id_region=" + id_region + '}';
//    }

    @Override
    public String toString() {
        return "Destination{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", id_region=" + id_region + ", region=" + region + ", latitude=" + latitude + ", longitude=" + longitude + '}';
    }

   
    
  
     
    
}
