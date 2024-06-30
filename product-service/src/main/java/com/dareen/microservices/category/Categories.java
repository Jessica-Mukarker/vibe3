package com.dareen.microservices.category;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dareen.microservices.product.Products;

import javax.persistence.Id;

@Entity
@Table(name = "productlines")
public class Categories {
	
	@Column(name = "productLine")
	@Id
private String productLine;
private String textDescription;
private String htmlDescription;
private Blob image;

@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name ="productLine")
private List<Products> products;


public Categories() {
	super();
	this.products =new ArrayList<>(); 

}


public Categories(String productLine, String textDescription, String htmlDescription, Blob image) {
	super();
	this.productLine = productLine;
	this.textDescription = textDescription;
	this.htmlDescription = htmlDescription;
	this.image = image;
}


public String getProductLine() {
	return productLine;
}


public void setProductLine(String productLine) {
	this.productLine = productLine;
}


public String getTextDescription() {
	return textDescription;
}


public void setTextDescription(String textDescription) {
	this.textDescription = textDescription;
}


public String getHtmlDescription() {
	return htmlDescription;
}


public void setHtmlDescription(String htmlDescription) {
	this.htmlDescription = htmlDescription;
}


public Blob getImage() {
	return image;
}


public void setImage(Blob image) {
	this.image = image;
}


public List<Products> getProducts() {
	return products;
}


public void setProducts(List<Products> products) {
	this.products = products;
}



}