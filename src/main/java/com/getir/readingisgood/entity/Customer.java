package com.getir.readingisgood.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.UniqueConstraint;
import java.util.Date;

@Document(collection = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

	@Id
	private String email;

	private String password;

	private String name;

	private String mobile;

	private Address address;

	private Date createdDate = new Date();
}
