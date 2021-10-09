package com.getir.readingisgood.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

	private String line1;

	private String line2;

	private String state;

	private String country;

	private String zipCode;

}
