package com.chriswlucas.restaurant;

class MenuItem {

	MenuItem(String name, int price, Boolean isFood){
		this.Name = name;
		this.price = price;
		//set isFood here when you create the item.
	}
	
	Boolean isFood(){
		return isFood;
	}
	
	String getItemName(){
		return Name;
	}
	
	int getPrice(){
		return price;
	}
	public int price;
	public String Name;
	public boolean isFood = false; //set to true if a food item, leave false if drink.
}
