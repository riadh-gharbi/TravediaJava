/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Razer
 */
public class Planning {
    private int Id;
    private Date dateDepart;
    private Date dateFin;
    private int VoyageurId;
    private int ActualiteId;
    private String Description;
    private int HotelId;
    private String TypePlan;
    private int DestinationId;
    private int EvenementId;
    private int prix;

    public Planning(int Id, Date dateDepart, Date dateFin, int Voyageur, String Description, int Hotel, String TypePlan, int Destination, int Evenement, int prix) {
        this.Id = Id;
        this.dateDepart = dateDepart;
        this.dateFin = dateFin;
        this.VoyageurId = Voyageur;
        this.Description = Description;
        this.HotelId = Hotel;
        this.TypePlan = TypePlan;
        this.DestinationId = Destination;
        this.EvenementId = Evenement;
        this.prix = prix;
    }

    public Planning(Date dateDepart, Date dateFin, int Voyageur, String Description, int Hotel, String TypePlan, int Destination, int Evenement, int prix) {
        this.dateDepart = dateDepart;
        this.dateFin = dateFin;
        this.VoyageurId = Voyageur;
        this.Description = Description;
        this.HotelId = Hotel;
        this.TypePlan = TypePlan;
        this.DestinationId = Destination;
        this.EvenementId = Evenement;
        this.prix = prix;
    }

    public Planning(Date dateDepart, Date dateFin, int VoyageurId, int ActualiteId, String Description, String TypePlan, int prix) {
        this.dateDepart = dateDepart;
        this.dateFin = dateFin;
        this.VoyageurId = VoyageurId;
        this.ActualiteId = ActualiteId;
        this.Description = Description;
        this.TypePlan = TypePlan;
        this.prix = prix;
    }
    
    

    public Planning() {
    }

     
    
    

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    
    

    
    
    public String getTypePlan() {
        return TypePlan;
    }

    public void setTypePlan(String TypePlan) {
        this.TypePlan = TypePlan;
    }

    
    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }   
  
    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getVoyageurId() {
        return VoyageurId;
    }

    public void setVoyageurId(int VoyageurId) {
        this.VoyageurId = VoyageurId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getHotelId() {
        return HotelId;
    }

    public void setHotelId(int Hotel) {
        this.HotelId = Hotel;
    }

    public int getDestinationId() {
        return DestinationId;
    }

    public void setDestinationId(int Destination) {
        this.DestinationId = Destination;
    }

    public int getEvenementId() {
        return EvenementId;
    }

    public void setEvenementId(int Evenement) {
        this.EvenementId = Evenement;
    }

    public int getActualiteId() {
        return ActualiteId;
    }

    public void setActualiteId(int ActualiteId) {
        this.ActualiteId = ActualiteId;
    }

    @Override
    public String toString() {
        return "Planning{" + "Id=" + Id + ", dateDepart=" + dateDepart + ", dateFin=" + dateFin + ", VoyageurId=" + VoyageurId + ", ActualiteId=" + ActualiteId + ", Description=" + Description + ", HotelId=" + HotelId + ", TypePlan=" + TypePlan + ", DestinationId=" + DestinationId + ", EvenementId=" + EvenementId + ", prix=" + prix + '}';
    }

   

     

   
    

}