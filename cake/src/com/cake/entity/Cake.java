package com.cake.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cake")
public class Cake {
	private int id;
	private double weight;
	private int price;
	private String pic;

	@Id
	@GenericGenerator(name = "cakeAssigned", strategy="assigned")
	@GeneratedValue(generator = "cakeAssigned")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "Cake [id=" + id + ", weight=" + weight + ", price=" + price + ", pic=" + pic + "]";
	}

}
