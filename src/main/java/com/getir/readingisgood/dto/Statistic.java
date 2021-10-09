package com.getir.readingisgood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class Statistic {

    private String month;

    private int totalOrder;

    private int totalBook;

    private Long totalAmount;

}
