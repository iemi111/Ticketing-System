package question4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Defines the main properties and behaviour of an event.
 *
 * @author ei00052
 */

public abstract class Event {

    // The event's name.
    private String name;
    // The type of the event (free or paid).
    private EventType eventType;
    // The total number of seats (or tickets) for the event.
    private int totalNumberOfSeats;
    // list of purchased tickets for type.
    protected Map<TicketType, List<Ticket>> tickets = new HashMap<>();
    // Purchased number of tickets for type
    private Map<TicketType, Integer> currentTicketNumbers = new HashMap<>();
    // Contains the prices for each type of ticket for the event.
    protected Map<TicketType, Double> prices;

    /**
     * Constructor for creating an event.
     *
     * @param name      The event's name.
     * @param eventType The type of the event (free or paid).
     * @param total     The total number of seats (or tickets) for the event.
     * @param prices    A map containing the prices for each type of ticket for the
     *                  event.
     */
    public Event(String name, EventType eventType, int total,
                 Map<TicketType, Double> prices) {
        super();
        this.name = name;
        this.eventType = eventType;
        this.totalNumberOfSeats = total;
        this.prices = new HashMap<>();
        this.prices = prices;
        populateMapsWithDefaultValues();
    }

    private void populateMapsWithDefaultValues() {
        for (TicketType type : TicketType.values()) {
            this.currentTicketNumbers.put(type, 0);
            this.tickets.put(type, new ArrayList<>());
        }
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
     * Updates the the information in the tickets map.
     *
     * @param ticket The ticket which we want to create/purchase.
     */
    private void addTicket(Ticket ticket) {
            //Creates a temporary list which corresponds to the existing list for the specific TypeTicket key.
            List<Ticket> listTickets = this.tickets.get(ticket.getType());
            listTickets.add(ticket);
            tickets.put(ticket.getType(), listTickets);
    }

    /**
     * Method for purchasing tickets.
     *
     * @param numberOfTickets The number of ticket to be purchased.
     * @param type            The ticket type which you want to buy.
     */
    public void purchaseTickets(int numberOfTickets, TicketType type) {
        int totalSoldTickets = getTotalSoldTickets();

        // Checks if there are still tickets available for purchase.
        if ((totalSoldTickets + numberOfTickets) <= totalNumberOfSeats) {
            int soldTicketsForCurrentType = currentTicketNumbers.get(type);
            // Loop which creates the desired number of purchased ticket.
            for (int i = 1; i <= numberOfTickets; i++) {
                soldTicketsForCurrentType++;
                this.currentTicketNumbers.put(type, soldTicketsForCurrentType);
                Ticket ticket = new Ticket(soldTicketsForCurrentType, type, prices.get(type));
                this.addTicket(ticket);
            }
        }
    }

    private int getTotalSoldTickets() {
        int number = 0;
        for (int i : currentTicketNumbers.values()) {
            number += i;
        }
        return number;
    }

    /**
     * Method for finding out how many tickets of a specific type are purchased.
     *
     * @param type The type of the tickets purchased.
     * @return the number of purchased tickets (of a specific type) for the
     * event.
     */
    public int howManyTicketsPurchased(TicketType type) {
        if (this.tickets.isEmpty() || !this.tickets.containsKey(type)) {
            return 0;
        } else {
            return this.tickets.get(type).size();
        }
    }

    /**
     * Method for printing out Information about the purchased tickets.
     */
    public void printPurchasedTicketInformation() {
        int totalSoldOutTickets = getTotalSoldTickets();
        int available = this.totalNumberOfSeats - totalSoldOutTickets;

        System.out.println("Event name: " + this.getEventName() + "\n"
                + "Number of Tickets Still Available: " + "\n" + available);
        // Iterates through all the type of tickets in the TicketType Enum.
        for (TicketType type : TicketType.values()) {
            System.out.println(type + " Info:");
            // Checks whether tickets of a specific type were bought.
            if (this.tickets.containsKey(type)) {
                for (int i = 0; i < this.howManyTicketsPurchased(type); i++) {
                    // Prints out the number of every purchased tickets.
                    System.out.print(this.tickets.get(type).get(i).getNumber() + " ");
                }
            } else if (!this.tickets.containsKey(type)) {
                System.out.print(0);
            }
            System.out.println();
        }
    }

}
