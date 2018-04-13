package question4;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the  properties and behaviour of a history of events.
 *
 * @author ei00052
 */
public class EventHistory {

    // A list of all the created paid events.
    private List<PaidEvent> paidEvents = new ArrayList<>();
    // A list of all the created free events.
    private List<FreeEvent> freeEvents = new ArrayList<>();
    // The organisation which is associated with the history of its events.
    private Organisation organisation;


    public EventHistory(Organisation organisation) {
        super();
        this.organisation = organisation;
    }


    public String displayFreeEvents() {

        StringBuilder str = new StringBuilder();
        //Iterates through events' names and types.
        for (FreeEvent freeEvent : freeEvents) {
            str.append(freeEvent.getEventName()).append(": ").append(freeEvent.getEventType()).append("\n");
        }
        return str.toString();
    }

    public String displayPaidEvents() {
        StringBuilder str = new StringBuilder();

        for (PaidEvent paid : this.paidEvents) {
            str.append(paid.getEventName()).append(": ").append(paid.getEventType()).append("\n");
        }
        return str.toString();
    }

    /**
     * Add a free event to the history of events.
     *
     * @param event The specific free event to be added.
     */
    public void addFreeEvent(FreeEvent event) {
        this.freeEvents.add(event);
    }

    /**
     * Add a paid event to the history of events.
     *
     * @param event The specific paid event to be added.
     */
    public void addPaidEvent(PaidEvent event) {
        this.paidEvents.add(event);
    }


    public Organisation getOrganisation() {
        return this.organisation;
    }


    /**
     * @return list of all the sold out events.
     */
    public List<String> listOfSoldOutEvents() {
        //Create a list to hold all the sold out events.
        List<String> soldOutEvents = new ArrayList<>();

        for (FreeEvent freeEvent : this.freeEvents) {
            int freeSold = 0;
            for (TicketType type : TicketType.values()) {
                freeSold += freeEvent.howManyTicketsPurchased(type);
            }
            //Check if the free event is sold out.
            if (freeEvent.getTotalNumberOfSeats() == freeSold) {
                soldOutEvents.add(freeEvent.getEventName());
            }
        }
        for (PaidEvent paidEvent : this.paidEvents) {
            int paidSold = 0;
            for (TicketType type : TicketType.values()) {
                paidSold += paidEvent.howManyTicketsPurchased(type);
            }
            if (paidEvent.getTotalNumberOfSeats() == paidSold) {
                soldOutEvents.add(paidEvent.getEventName());
            }
        }

        return soldOutEvents;
    }


    /**
     * Calculate the total cost of the purchased tickets
     * from all the paid events associated with the organisation.
     *
     * @return total cost of all the purchased tickets for all paid events.
     */
    public double calculateTotalTicketCostOfPaidEvents() {
        double m = 0.0;
        for (PaidEvent paidEvent : paidEvents) {
            m += paidEvent.calculateTotalTicketCostOfPurchasedTickets();
        }
        return m;
    }

}
