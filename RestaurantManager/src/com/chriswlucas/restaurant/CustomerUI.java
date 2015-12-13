package com.chriswlucas.restaurant;

import java.util.List;

/**
 * Interface for the customer uses.
 * Can be implemented as a command line or as a GUI.
 * @author Nick Anderson 
 * @author Jonathon Bundy
 */
interface CustomerUI {
	
	public void controlCustomer(int partyID);
	
	/**
	 * Gets the customer names from the customers and stores them in a list.
	 */
	public List<String> setCustomerNames(int partySize);
	
	/**
	 * Starts the whole ordering procedure for a specific party.
	 * @param partyID
	 */
	public void initiateOrdering(int partyID);
	
	/**
	 * Display the options a user has to choose from.
	 */
	public void displayOrderingChoices();
	
	/**
	 * Display the menu to the user.
	 * @param isFood - true for food, false for drinks.
	 */
	public void displayMenuItems(boolean isFood);
	
	/**
	 * Displays the customer to be chosen.
	 * @param custNames - list of customer names.
	 */
	public void displayCustomers(List<String>custNames);
	
	/**
	 * Gets an integer choice from the customer.
	 * @return data - the user's choice.
	 */
	public int getIntegerFromUser();
	
	/**
	 * Displays the remove instruction to the user.
	 */
	public void displayRemoveInstruction();
	
	/**
	 * Display choices for items (0 for food, 1 for drink).
	 */
	public void displayFoodOrDrinkChoice();
	
	/**
	 * Displays the items that are in the ticket currently being made.
	 * @param items - items in the ticket
	 * @param custNames - list of customer names.
	 */
	public void displayItemsInList(List<MenuItem> itemList);
	
	/**
	 * Display invalid option.
	 */
	public void displayInvalidOption();
	
	/**
	 * Displays the option for patrons to checkout
	 * @param partyID
	 */
	public void displayCheckout();
	
	public void displayThanks();
	
	public void displayPayWhenReady();
	
	public void initiateCheckout(int partyID);
	
	public void display(String string);
	
	public List<Integer> setCheckNames(List<String>custNames, int split, int num);
	
	/**
	 * Displays the total for a receipt
	 */
	public void displayReceipt(Receipt receipt);
	
	/**
	 * Displays the total receipts for a table
	 */
	public void displayReceipts(List<Receipt>receipts);	
	
	public int getSplit();

}
