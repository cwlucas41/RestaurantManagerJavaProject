package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

class CustomerCLI extends UserInterface implements CustomerUI {

	
	public CustomerCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	/**
	 * Gets the customer names from the customers.
	 */
	public List<String> setCustomerNames(){
		List<String>people = new ArrayList<String>();
		Scanner scanner = new Scanner(System.in);
		System.out.print("How many people in your party: ");
		int numPeople = scanner.nextInt();
		
		for (int i = 0; i<numPeople; i++){
			int temp = i + 1;
			String output = "Enter customer "+temp+"(FirstName and first letter of last name without spaces): ";
			System.out.print(output);
			people.add(scanner.next());
		}
		scanner.close();
		return people;
	}
	
	private int getIntegerFromUser(){
		Scanner scanner = new Scanner(System.in);
		int data = scanner.nextInt();
		scanner.close();
		return data;
	}
	
	public int displayCustomerChoices(){
		System.out.println("Choose 1, 2, 3, or 4 for the following choices.");
		System.out.println("1) Add item to ticket.");
		System.out.println("2) Remove item from ticket.");
		System.out.println("3) Submit order");
		System.out.println("4) Cancel order");
		return getIntegerFromUser();
	}
	
	public void displayAddItem(){
		System.out.println("Choose the number of the item you want:");
		System.out.println(super.getRestaurant().getMenu().toString());
	}
	
	public void displayCustomers(List<String>custNames){
		System.out.println("Choose the number of the customer you want:");
		ListIterator<String> it = custNames.listIterator() ;
		int j = 0;
		while(it.hasNext()){
			String name = j + it.next();
			System.out.println(name);
			j++;
		}
		System.out.println();
	}
	public int getChoice(){
		return getIntegerFromUser();
	}
	
	public void displayItemsInList(List<Order>items, List<String>custNames){
		ListIterator<Order> iterator = items.listIterator();
		int i = 0;
		System.out.println("Choose the number of the item you want to remove:");
		while (iterator.hasNext()){
			Order temp = iterator.next();
			String out = i+") "+custNames.get(temp.getCust()) + ": " + temp.toString();
			System.out.println(out);
			i++;
		}
	}
	
	public void displayRemoveItemMenu(){
		System.out.println("Choose 1 or 2 for the following choices.");
		System.out.println("1) Remove a drink item");
		System.out.println("2) Remove a food item");
	}
		
	public void displayInvalidOption(){
		System.out.println("Invalid option.");
	}
}
