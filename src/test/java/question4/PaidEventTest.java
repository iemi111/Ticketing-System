/**
 * PaidEvent.java
 */

package question4;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the PaidEvent class.
 *
 * @author Helen Treharne
 */
public class PaidEventTest {

    /**
     * Creating a paid Event object with valid input parameters for the  event.
     * Test that the name, type and number of seats can be retrieved correctly,
     * and that the lists have been created successfully and that nothing has yet been purchased.
     */

    @Test
    public void testPaidConstruction() {
        //set up the map of prices for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 10.0);
        prices.put(TicketType.STUDENT, 5.0);
        prices.put(TicketType.CONCESSION, 7.50);

        //now create the event with the associated prices so the code should be able to cope
        //with whatever prices you give it depending on how many enumerations are included in the
        //TicketType enumeration
        PaidEvent event = new PaidEvent("Fireworks Evening", EventType.PAID, 10, prices);

        //now assert that the initial values are as expected
        assertEquals("Fireworks Evening", event.getEventName());
        assertEquals(EventType.PAID, event.getEventType());
        assertEquals(10, event.getTotalNumberOfSeats());
        //the following checks that the lists have been created properly
        assertEquals(0, event.howManyTicketsPurchased(TicketType.MEMBER), 0);
        assertEquals(0, event.howManyTicketsPurchased(TicketType.STUDENT), 0);
        assertEquals(0, event.calculateTotalTicketCostOfPurchasedTickets(), 0);
    }


    /**
     * Test to calculate how many tickets have been purchased for an event
     * Purchase 5 member tickets and 4 student tickets and then experiment to see that too
     * many tickets can't be bought
     */
    @Test
    public void howManyTicketsPurchased() {
        //set up the map of prices for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 10.0);
        prices.put(TicketType.STUDENT, 5.0);
        prices.put(TicketType.CONCESSION, 7.50);

        //now create the event to have a maximum of 12 tickets
        PaidEvent event = new PaidEvent("Fireworks Evening", EventType.PAID, 12, prices);
        //first purchase 9 tickets
        event.purchaseTickets(5, TicketType.MEMBER);
        event.purchaseTickets(4, TicketType.STUDENT);
        //check that the tickets have been added to the lists as expected
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));


        //try to buy number of tickets that can't because there aren't enough left
        event.purchaseTickets(6, TicketType.MEMBER);
        event.purchaseTickets(6, TicketType.STUDENT);
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));

        //purchase of one concession type ticket which would be allowed and
        //check how many were purchased again
        event.purchaseTickets(3, TicketType.CONCESSION);
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));
        assertEquals("wrong number purchased", 3, event.howManyTicketsPurchased(TicketType.CONCESSION));
    }

    /**
     * Test to calculate the cost of tickets have been purchased for an event
     * Purchase 5 member tickets and 4 student tickets and then calculate the associate cost of
     * the tickets.
     * Also do experiments to check that the cost can't change if the tickets haven't been purchased
     * And demonstrate flexibility when more than one type is included
     */
    @Test
    public void calculateTotalTicketCostOfPurchasedTickets() {
        //set up the map of prices for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 10.0);
        prices.put(TicketType.STUDENT, 5.0);
        prices.put(TicketType.CONCESSION, 7.50);

        //now create the event to have a maximum of 12 tickets
        PaidEvent event = new PaidEvent("Fireworks Evening", EventType.PAID, 12, prices);
        //first purchase 9 tickets
        event.purchaseTickets(5, TicketType.MEMBER);
        event.purchaseTickets(4, TicketType.STUDENT);
        //check that the cost of the tickets are recorded properly
        assertEquals("wrong number purchased", (5 * 10.0 + 4 * 5.0),
                event.calculateTotalTicketCostOfPurchasedTickets(), 0);

        //try to buy number of tickets that can't because there aren't enough left
        event.purchaseTickets(6, TicketType.MEMBER);
        event.purchaseTickets(6, TicketType.STUDENT);
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));

        //purchase of one concession type ticket which would be allowed and
        //check how many were purchased again
        event.purchaseTickets(3, TicketType.CONCESSION);
        assertEquals("wrong number purchased", (5 * 10.0 + 4 * 5.0 + 3 * 7.50),
                event.calculateTotalTicketCostOfPurchasedTickets(), 0);
    }


    /**
     * Test to display printing of purchased tickets
     */
    @Test
    public void printPurchasedTicketInformation() {
        //set up the map of prices for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 10.0);
        prices.put(TicketType.STUDENT, 5.0);
        prices.put(TicketType.CONCESSION, 7.50);

        //now create the event to have a maximum of 12 tickets
        PaidEvent event = new PaidEvent("Fireworks Evening", EventType.PAID, 12, prices);
        //first purchase 9 tickets
        event.purchaseTickets(5, TicketType.MEMBER);
        event.purchaseTickets(4, TicketType.STUDENT);
        event.purchaseTickets(1, TicketType.CONCESSION);

        //now we can print out the information
        event.printPurchasedTicketInformation();
    }
}