package com.dareen.microservices.product;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Column(name = "productName")
	private String productName;

	@Column(name = "productCode")
	@Id
	private String productCode;

	@Column(name = "productLine")
	private String productLine;

	@Column(name = "productScale")
	private String productScale;

	@Column(name = "productVendor")
	private String productVendor;

	@Column(name = "productDescription")
	private String productDescription;

	@Column(name = "quantityInStock")
	private int quantityInStock;

	@Column(name = "buyPrice")
	private long buyPrice;

	@Column(name = "MSRP")
	private long MSRP;

	public Products() {
		super();
	}

	public Products(String productName, String productCode, String productLine, String productScale,
			String productVendor, String productDescription, int quantityInStock, long buyPrice, long mSRP) {
		super();
		this.productName = productName;
		this.productCode = productCode;
		this.productLine = productLine;
		this.productScale = productScale;
		this.productVendor = productVendor;
		this.productDescription = productDescription;
		this.quantityInStock = quantityInStock;
		this.buyPrice = buyPrice;
		this.MSRP = mSRP;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public void setBuyPrice(long buyPrice) {
		this.buyPrice = buyPrice;
	}

	public void setMSRP(long mSRP) {
		MSRP = mSRP;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductLine() {
		return productLine;
	}

	public String getProductScale() {
		return productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public long getBuyPrice() {
		return buyPrice;
	}

	public long getMSRP() {
		return MSRP;
	}

}
