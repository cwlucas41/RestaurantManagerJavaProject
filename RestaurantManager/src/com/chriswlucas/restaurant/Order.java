package com.chriswlucas.restaurant;

class Order{
	Order(MenuItem item, int customer){
		this.it = item;
		this.cust=customer;
	}
	
	MenuItem getItem(){
		return it;
	}
	int getCust(){
		return cust;
	}
	private MenuItem it;
	private int cust;
}