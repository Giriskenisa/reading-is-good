package com.getir.readingisgood.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.getir.readingisgood.util.Constants;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Document(collection = "order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

	@Id
	private String orderId;

	private String customerId;

	private List<NewBooksOrder> books;

	private Long totalAmount;
	
	private int totalBooks;

	private String status;

	@JsonFormat(pattern = Constants.DATE_FORMAT)
	private Date orderedDate = new Date();

}