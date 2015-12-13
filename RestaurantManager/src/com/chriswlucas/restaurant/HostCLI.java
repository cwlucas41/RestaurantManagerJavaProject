package com.chriswlucas.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

class HostCLI extends UserCLI implements HostUI {
		
	public HostCLI(Restaurant restaurant){
		super(restaurant);
	}
	
	public void controlHost() {
		boolean isFinished = false;
		while (!isFinished){
			printLine("Choose from the following choices:");
			printLine("-1) exit");
			printLine("0) Display unoccupied tables");
			printLine("1) Display unoccupied bar seats");
			printLine("2) Display table waitlist");
			printLine("3) Display bar seat waitlist");
			printLine("4) Add party to table waitlist");
			printLine("5) Add party to bar seat waitlist");
			printLine("6) Seat party from table waitlist");
			printLine("7) Seat party from bar seat waitlist");
			int choice = getIntegerFromUser();
			switch (choice) {
			case -1: isFinished = true; break;
			case 0: this.displayFreeTables(false); break;
			case 1: this.displayFreeTables(true); break;
			case 2: this.displayWaitlist(false); break;
			case 3: this.displayWaitlist(true); break;
			case 4: this.addNewPartyToWaitlist(false); break;
			case 5: this.addNewPartyToWaitlist(true); break;
			case 6: this.seatCustomers(false); break;
			case 7: this.seatCustomers(true); break;
			default: printLine("Invalid choice, try again"); break;
			}
		}
	}
	
	public void addNewPartyToWaitlist(boolean isAtBar) {
		printLine("Enter party size or -1 to exit");
		int partySize = getIntegerFromUser();
		if (partySize == -1) {
			return;
		} else if (partySize > 0) {
			printLine("Enter party name");
			String partyID = getLineFromUser();
			this.getRestaurant().addToWaitlist(partyID, partySize, isAtBar);
		} else {
			addNewPartyToWaitlist(isAtBar);
		}
		
	}
	
	public Hashtable<String, Integer> displayWaitlist(boolean isBarWaitlist) {
		Hashtable<String, Integer> waitlist;
		if (isBarWaitlist) {
			waitlist = this.getRestaurant().getBarWaitlist();
			printLine("Waitlist for bar:");
		} else {
			waitlist = this.getRestaurant().getTableWaitlist();
			printLine("Waitlist for tables:");
		}
		printLine("\tName\tSize");
		Set<String> keys = waitlist.keySet();
		for (String key : keys) {
			printLine("\t" + key + "\t" + waitlist.get(key).toString());
		}
		return waitlist;
	}
	
	public void seatCustomers(boolean isAtBar) {
		Hashtable<String, Integer> waitlist = this.displayWaitlist(isAtBar);
		printLine("Choose party from waitlist by entering the party name");
		boolean isFinished = false;
		String key = getLineFromUser();
		while (!isFinished) {
			if (waitlist.containsKey(key)) {
				isFinished = true;
			} else {
				printLine("Invald index, try again");
				key = getLineFromUser();
			}
		}
		Integer partyNumber = this.getRestaurant().createParty(isAtBar, key);
		if (partyNumber != null) {
			printLine("Customers have party number of " + partyNumber);
		} else {
			printLine("The party cannot be seated at this time");
		}
	}
	
	public List<Integer> assignTableNumbers(boolean isAtBar, int partySize) {
		List<Integer> assignedTables = new ArrayList<Integer>();
		int remainingToSeat = partySize;
		List<Integer> freeTables = this.displayFreeTables(isAtBar);
		while (remainingToSeat > 0) {
			printLine("Choose table number, remaining customers to seat is " + remainingToSeat);
			printLine("Enter -1 to cancel");
			int tableNumber = getIntegerFromUser();
			if (tableNumber == -1) {
				return null;
			} else if (freeTables.contains(tableNumber) && !assignedTables.contains(tableNumber)) {
				assignedTables.add(tableNumber);
				remainingToSeat -= this.getRestaurant().getTable(tableNumber).getsize();
			} else {
				printLine("Invalid choice, please try again");
			}
		}
		return assignedTables;
	}
	
	public List<Integer> displayFreeTables(boolean isAtBar) {
		if (isAtBar) {
			printLine("The available bar seats are:");
		} else {
			printLine("The available tables are:");
		}
		printLine("\tNumber\tCapacity");
		List<Integer> tableNumbers = this.getRestaurant().getUnoccupiedTables(isAtBar);
		Collections.sort(tableNumbers);
		for (int tableNumber : tableNumbers) {
			printLine("\t" + tableNumber + "\t" + this.getRestaurant().getTable(tableNumber));
		}
		printLine("");
		return tableNumbers;
	}
}
