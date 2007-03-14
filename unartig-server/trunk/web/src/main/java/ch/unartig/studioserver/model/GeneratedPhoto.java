package ch.unartig.studioserver.model;

import java.util.*;




/**
 * GeneratedPhoto generated by hbm2java
 */
public class GeneratedPhoto  implements java.io.Serializable {

    // Fields    

     private Long photoId;
     private String filename;
     private String displayTitle;
     private Integer widthPixels;
     private Integer heightPixels;
     private Date pictureTakenDate;
     private Date uploadDate;
     private Set orderItems;
     private Set photoSubjects;
     private StudioAlbum album;


    // Constructors

    /** default constructor */
    public GeneratedPhoto() {
    }
    
    /** constructor with id */
    public GeneratedPhoto(Long photoId) {
        this.photoId = photoId;
    }
   
    
    

    // Property accessors

    /**
     * 
     */
    public Long getPhotoId() {
        return this.photoId;
    }
    
    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    /**
     * 
     */
    public String getFilename() {
        return this.filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * 
     */
    public String getDisplayTitle() {
        return this.displayTitle;
    }
    
    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    /**
     * 
     */
    public Integer getWidthPixels() {
        return this.widthPixels;
    }
    
    public void setWidthPixels(Integer widthPixels) {
        this.widthPixels = widthPixels;
    }

    /**
     * 
     */
    public Integer getHeightPixels() {
        return this.heightPixels;
    }
    
    public void setHeightPixels(Integer heightPixels) {
        this.heightPixels = heightPixels;
    }

    /**
     * 
     */
    public Date getPictureTakenDate() {
        return this.pictureTakenDate;
    }
    
    public void setPictureTakenDate(Date pictureTakenDate) {
        this.pictureTakenDate = pictureTakenDate;
    }

    /**
     * 
     */
    public Date getUploadDate() {
        return this.uploadDate;
    }
    
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * 
     */
    public Set getOrderItems() {
        return this.orderItems;
    }
    
    public void setOrderItems(Set orderItems) {
        this.orderItems = orderItems;
    }

    /**
     * 
     */
    public Set getPhotoSubjects() {
        return this.photoSubjects;
    }
    
    public void setPhotoSubjects(Set photoSubjects) {
        this.photoSubjects = photoSubjects;
    }

    /**
     * 
     */
    public StudioAlbum getAlbum() {
        return this.album;
    }
    
    public void setAlbum(StudioAlbum album) {
        this.album = album;
    }

  /**
	 * toString
	 * @return String
	 */
  public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("photoId").append("='").append(getPhotoId()).append("' ");			
      buffer.append("filename").append("='").append(getFilename()).append("' ");			
      buffer.append("displayTitle").append("='").append(getDisplayTitle()).append("' ");			
      buffer.append("widthPixels").append("='").append(getWidthPixels()).append("' ");			
      buffer.append("heightPixels").append("='").append(getHeightPixels()).append("' ");			
      buffer.append("pictureTakenDate").append("='").append(getPictureTakenDate()).append("' ");			
      buffer.append("uploadDate").append("='").append(getUploadDate()).append("' ");			
      buffer.append("album").append("='").append(getAlbum()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
	}



}