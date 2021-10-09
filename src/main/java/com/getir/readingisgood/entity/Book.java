package com.getir.readingisgood.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	@Id
	private String serial;

	private String title;

	private String author;

	private double price;

	private int stock;
	
	private Date dateAdded = new Date();

	@Override
	public String toString() {
		return title +",";
	}

}
