package question3;

import java.util.ArrayList;
import java.util.List;


/**
 * Defines the properties and behaviour of an event.
 * 
 * @author ei00052
 * 
 */

public abstract class Event {

	// The event's name.
	private String name;
	// The type of the event (free or paid).
	private EventType eventType;
	// The total number of seats (or tickets) for the event.
	private int totalNumberOfSeats;
	// List of all purchased member tickets.
	private List<Ticket> memberTickets = new ArrayList<>();
	// List of all purchased student tickets.
	private List<Ticket> studentTickets = new ArrayList<>();
	// The current member ticket number holder.
	private int currentMemberTicketNumber;
	// The current student ticket number holder.
	private int currentStudentTicketNumber;
	
	/**
	 * Constructor an Event to be created with a name, type
	 * of the event and the total number of the tickets for that event
	 *
	 * @param name
	 * 			The event's name.
	 * @param eventType
	 * 			The type of the event (free or paid).
	 * @param total
	 * 			The total number of seats (or tickets) for the event.
	 */
	public Event(String name, EventType eventType, int total) {
		super();
		this.name = name;
		this.eventType = eventType;
		this.totalNumberOfSeats = total;
        this.currentMemberTicketNumber = 1;
        this.currentStudentTicketNumber = 1;
	}


	public String getEventName() {
		return this.name;
	}

	public EventType getEventType() {
		return this.eventType;
	}

	public int getTotalNumberOfSeats() {
		return this.totalNumberOfSeats;
	}

	
	/**
	 * Purchases the number and type of tickets and
	 * uses the updates the information in the tickets list.
	 * 
	 * @param numberOfTickets
	 * 			The number of ticket to be purchased.
	 * @param type
	 * 			The ticket type which you want to buy.
	 */			
	private void createTickets(int numberOfTickets, TicketType type) {
		
		if (type == TicketType.MEMBER) {
			for (int i = 1; i <= numberOfTickets; i++) {
				Ticket ticketM = new Ticket(this.currentMemberTicketNumber++,
						TicketType.MEMBER);
				//Add the ticket to the list of purchased member tickets.
				memberTickets.add(ticketM);
			}
		} else if (type == TicketType.STUDENT) {
			for (int i = 1; i <= numberOfTickets; i++) {
				Ticket ticketS = new Ticket(this.currentStudentTicketNumber++,
						TicketType.STUDENT);
				//Add the ticket to the list of purchased student tickets.
				this.studentTickets.add(ticketS);
			}
		}
		else {
			System.out.println("Could not purchase tickets");
		}
	}

	
	/**
	 * Purchase the desired tickets.
	 * 
	 * @param numberOfMemberTickets
	 * 			The number of member tickets we want to purchase.
	 * @param numberOfStudentTickets
	 * 			The number of student tickets we want to purchase.
	 */
	public void purchaseTickets(int numberOfMemberTickets, int numberOfStudentTickets) {
		// Checks if there are still tickets available for purchase.
		if ((this.memberTickets.size() + numberOfMemberTickets)
				+ (this.studentTickets.size() + numberOfStudentTickets) <= this.totalNumberOfSeats) {
            // Purchase and create the tickets
            this.createTickets(numberOfMemberTickets, TicketType.MEMBER);
            this.createTickets(numberOfStudentTickets, TicketType.STUDENT);
        }
	}

	
	/**
	 * How many tickets have been purchased so far.
	 * 
	 * @param type the type of ticket.
	 * @return the number of purchased tickets for the specified type of tickets.
	 */
	public int howManyTicketsPurchased(TicketType type) {

		int number = 0;
		if (type == TicketType.MEMBER) {
			//Get the number of sold out member tickets.
			number = this.memberTickets.size();
		} else if (type == TicketType.STUDENT) {
			//Get the number of sold out student tickets.
			number = this.studentTickets.size();
		} else {
			System.out.println("Could not find tickets purchased.");
		}
		return number;
	}

	
	/**
	 * Print out Information about the purchased tickets.
	 */
	public void printPurchasedTicketInformation() {
		int available = 0;
		// Gives us the number of available tickets.
		available = this.totalNumberOfSeats - (this.howManyTicketsPurchased(TicketType.MEMBER)
                + this.howManyTicketsPurchased(TicketType.STUDENT));

		System.out.println("Event name: " + this.getEventName() + "\n"
				+ "Number of Tickets Still Available: " + "\n" + available);

		System.out.println("Member Ticket Info:");
		//Iterates through all the member tickets' numbers and prints them out.
		for (int i = 0; i < this.howManyTicketsPurchased(TicketType.MEMBER); i++) {
			System.out.print(this.memberTickets.get(i).getNumber() + " ");

		}
		//Iterates through all the student tickets' numbers and prints them out.
		System.out.println("\n" + "\n" + "Student Ticket Info:");
		for (int i = 0; i < this.howManyTicketsPurchased(TicketType.STUDENT); i++) {
			System.out.print(this.studentTickets.get(i).getNumber() + " ");
		}

	}

}
