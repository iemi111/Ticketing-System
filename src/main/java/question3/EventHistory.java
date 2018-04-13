package question3;

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

    /**
     * Constructor for creating a History of Events.
     *
     * @param organisation The EventHistory should be associated with an organisation.
     */
    public EventHistory(Organisation organisation) {
        super();
        this.organisation = organisation;
    }


    public String displayFreeEvents() {

        String m = "";
        //Iterates through events' names and types.
        for (FreeEvent freeEvent : freeEvents) {

            m += freeEvent.getEventName() + ": "
                    + freeEvent.getEventType() + "\n";

        }
        return m;

    }


    public String displayPaidEvents() {

        StringBuilder n = new StringBuilder();
        //Iterates through events' names and types.
        for (PaidEvent paid : this.paidEvents) {

            n.append(paid.getEventName()).append(": ").append(paid.getEventType()).append("\n");
        }
        return n.toString();
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

        for (FreeEvent free : this.freeEvents) {
            //Check if the free event is sold out.
            if (free.getTotalNumberOfSeats() == (free
                    .howManyTicketsPurchased(TicketType.MEMBER) + free
                    .howManyTicketsPurchased(TicketType.STUDENT))) {
                soldOutEvents.add(free.getEventName());
            }
        }

        for (PaidEvent paid : this.paidEvents) {
            //Calculate the total sold tickets for every paid event.
            if (paid.getTotalNumberOfSeats() == (paid
                    .howManyTicketsPurchased(TicketType.MEMBER) + paid
                    .howManyTicketsPurchased(TicketType.STUDENT))) {
                soldOutEvents.add(paid.getEventName());
            }

        }
        return soldOutEvents;

    }

}
