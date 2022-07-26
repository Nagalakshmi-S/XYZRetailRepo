package com.xyzretail.bean;

import java.sql.Date;
import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Transaction {
	
	private int transactionId;
	private String userName;
	private Date date;
	private Time time;
	
}
