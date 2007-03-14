package ch.unartig.studioserver.model;

import java.util.*;




/**
 * GeneratedStudioAlbum generated by hbm2java
 */
public abstract class GeneratedStudioAlbum extends ch.unartig.studioserver.model.GenericLevel implements java.io.Serializable {

    // Fields    

     private String albumTypeString;
     private Event event;
     private PriceSegment priceSegment;
     private Set photos;
     private Set products;


    // Constructors

    /** default constructor */
    public GeneratedStudioAlbum() {
    }
    
   
    
    

    // Property accessors

    /**
     * 
     */
    public String getAlbumTypeString() {
        return this.albumTypeString;
    }
    
    public void setAlbumTypeString(String albumTypeString) {
        this.albumTypeString = albumTypeString;
    }

    /**
     * 
     */
    public Event getEvent() {
        return this.event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * 
     */
    public PriceSegment getPriceSegment() {
        return this.priceSegment;
    }
    
    public void setPriceSegment(PriceSegment priceSegment) {
        this.priceSegment = priceSegment;
    }

    /**
     * 
     */
    public Set getPhotos() {
        return this.photos;
    }
    
    public void setPhotos(Set photos) {
        this.photos = photos;
    }

    /**
     * 
     */
    public Set getProducts() {
        return this.products;
    }
    
    public void setProducts(Set products) {
        this.products = products;
    }

  /**
	 * toString
	 * @return String
	 */
  public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("event").append("='").append(getEvent()).append("' ");			
      buffer.append("priceSegment").append("='").append(getPriceSegment()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
	}



}