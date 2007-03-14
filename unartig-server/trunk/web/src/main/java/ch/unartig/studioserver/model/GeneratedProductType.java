package ch.unartig.studioserver.model;

import java.util.*;




/**
 * GeneratedProductType generated by hbm2java
 */
public class GeneratedProductType  implements java.io.Serializable {

    // Fields    

     private Long productTypeId;
     private String name;
     private String description;
     private Boolean digitalProduct;
     private Set prices;


    // Constructors

    /** default constructor */
    public GeneratedProductType() {
    }
    
    /** constructor with id */
    public GeneratedProductType(Long productTypeId) {
        this.productTypeId = productTypeId;
    }
   
    
    

    // Property accessors

    /**
     * 
     */
    public Long getProductTypeId() {
        return this.productTypeId;
    }
    
    public void setProductTypeId(Long productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * 
     */
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     */
    public Boolean getDigitalProduct() {
        return this.digitalProduct;
    }
    
    public void setDigitalProduct(Boolean digitalProduct) {
        this.digitalProduct = digitalProduct;
    }

    /**
     * 
     */
    public Set getPrices() {
        return this.prices;
    }
    
    public void setPrices(Set prices) {
        this.prices = prices;
    }

  /**
	 * toString
	 * @return String
	 */
  public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("productTypeId").append("='").append(getProductTypeId()).append("' ");			
      buffer.append("name").append("='").append(getName()).append("' ");			
      buffer.append("description").append("='").append(getDescription()).append("' ");			
      buffer.append("digitalProduct").append("='").append(getDigitalProduct()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
	}



}