package springapp.domain.NSS;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bgc_products")
public class NssProductBean implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The value for the bgcProductId field */
	@Id
	@GeneratedValue
	@Column(name="bgc_product_id")
    private int bgcProductId;
      
    /** The value for the bgcProductName field */
	@Column(name="bgc_product_name")
    private String bgcProductName;
      
    /** The value for the priceBasic field */
	@Column(name="price_basic")
    private BigDecimal priceBasic;
      
    /** The value for the priceDpass field */
	@Column(name="price_dpass")
    private BigDecimal priceDpass;
      
    /** The value for the bgcProductDesc field */
	@Column(name="bgc_product_desc")
    private String bgcProductDesc;

	public int getBgcProductId() {
		return bgcProductId;
	}

	public void setBgcProductId(int bgcProductId) {
		this.bgcProductId = bgcProductId;
	}

	public String getBgcProductName() {
		return bgcProductName;
	}

	public void setBgcProductName(String bgcProductName) {
		this.bgcProductName = bgcProductName;
	}

	public BigDecimal getPriceBasic() {
		return priceBasic;
	}

	public void setPriceBasic(BigDecimal priceBasic) {
		this.priceBasic = priceBasic;
	}

	public BigDecimal getPriceDpass() {
		return priceDpass;
	}

	public void setPriceDpass(BigDecimal priceDpass) {
		this.priceDpass = priceDpass;
	}

	public String getBgcProductDesc() {
		return bgcProductDesc;
	}

	public void setBgcProductDesc(String bgcProductDesc) {
		this.bgcProductDesc = bgcProductDesc;
	}
  

}
