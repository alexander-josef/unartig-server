package ch.unartig.studioserver.model;

import java.util.*;




/**
 * GeneratedPrice generated by hbm2java
 */
public class GeneratedPrice  implements java.io.Serializable {

    // Fields    

     private Long priceId;
     private java.math.BigDecimal priceCHF;
     private java.math.BigDecimal priceEUR;
     private java.math.BigDecimal priceGBP;
     private java.math.BigDecimal priceSEK;
     private String comment;


    // Constructors

    /** default constructor */
    public GeneratedPrice() {
    }
    
    /** constructor with id */
    public GeneratedPrice(Long priceId) {
        this.priceId = priceId;
    }
   
    
    

    // Property accessors

    /**
     * 
     */
    public Long getPriceId() {
        return this.priceId;
    }
    
    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    /**
     * 
     */
    public java.math.BigDecimal getPriceCHF() {
        return this.priceCHF;
    }
    
    public void setPriceCHF(java.math.BigDecimal priceCHF) {
        this.priceCHF = priceCHF;
    }

    /**
     * 
     */
    public java.math.BigDecimal getPriceEUR() {
        return this.priceEUR;
    }
    
    public void setPriceEUR(java.math.BigDecimal priceEUR) {
        this.priceEUR = priceEUR;
    }

    /**
     * 
     */
    public java.math.BigDecimal getPriceGBP() {
        return this.priceGBP;
    }
    
    public void setPriceGBP(java.math.BigDecimal priceGBP) {
        this.priceGBP = priceGBP;
    }

    /**
     * 
     */
    public java.math.BigDecimal getPriceSEK() {
        return this.priceSEK;
    }
    
    public void setPriceSEK(java.math.BigDecimal priceSEK) {
        this.priceSEK = priceSEK;
    }

    /**
     * 
     */
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

  /**
	 * toString
	 * @return String
	 */
  public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("priceId").append("='").append(getPriceId()).append("' ");			
      buffer.append("priceCHF").append("='").append(getPriceCHF()).append("' ");			
      buffer.append("priceEUR").append("='").append(getPriceEUR()).append("' ");			
      buffer.append("priceGBP").append("='").append(getPriceGBP()).append("' ");			
      buffer.append("priceSEK").append("='").append(getPriceSEK()).append("' ");			
      buffer.append("comment").append("='").append(getComment()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
	}



}