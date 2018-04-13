/**
 * FreeEvent.java
 */

package question4;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the FreeEvent class.
 * <p>
 * Please note that this is not the most efficient way to create the FreeEvent objects.
 * In the optional extra question of the coursework
 * by using a static method in the constructor you can get rid of the
 * overhead of constructing a map of prices which are all zero.
 * There is a way of being able to do that in the FreeEvent class.
 * But since we haven't explicitly covered it during the lectures I haven't included
 * it in the tests for question 3 and therefore the tests
 * look a little verbose for FreeEvent objects
 * because the code itself should be able to know that there is no prices attached to free tickets.
 * However, it is not a problem to define them explicitly beforehand and pass them in as values
 * to the constructor. In question 3, I am expecting them to be defined beforehand and
 * passed in when a FreeEvent object is created.
 *
 * @author Helen Treharne
 */
public class FreeEventTest {

    /**
     * Creating a paid Event object with valid input parameters for the  event.
     * Test that the name, type and number of seats can be retrieved correctly,
     * and that the lists have been created successfully.
     */

    @Test
    public void testFreeConstruction() {
        //set up the map of zero cost for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 0.0);
        prices.put(TicketType.STUDENT, 0.0);
        prices.put(TicketType.CONCESSION, 0.0);

        // event with name, type, number of seats and zero cost tickets
        FreeEvent event = new FreeEvent("Halloween Evening", EventType.FREE, 10, prices);

        //now asserting that all the values and initial set up is as expected
        assertEquals("Halloween Evening", event.getEventName());
        assertEquals(EventType.FREE, event.getEventType());
        assertEquals(10, event.getTotalNumberOfSeats());
        assertEquals(0, event.howManyTicketsPurchased(TicketType.MEMBER), 0);
        assertEquals(0, event.howManyTicketsPurchased(TicketType.STUDENT), 0);
    }


    /**
     * Test to calculate how many tickets have been purchased for an event
     * Purchase 5 member tickets and 4 student tickets and then experiment to see that too
     * many tickets can't be bought but then in question 3 since the functionality has been
     * generalised we can show easily that more types can be added.
     */
    @Test
    public void howManyTicketsPurchased() {

        //set up the map of zero cost for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 0.0);
        prices.put(TicketType.STUDENT, 0.0);
        prices.put(TicketType.CONCESSION, 0.0);

        //create the event object

        FreeEvent event = new FreeEvent("Halloween Evening", EventType.FREE, 12, prices);

        //purchase some tickets and assert that the appropriate number been included in the lists
        event.purchaseTickets(5, TicketType.MEMBER);
        event.purchaseTickets(4, TicketType.STUDENT);
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));

        //try to buy number of tickets that can't because there aren't enough left
        event.purchaseTickets(6, TicketType.MEMBER);
        event.purchaseTickets(6, TicketType.STUDENT);
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));

        //add code to add purchase of one concession type ticket which would be allowed
        //and check how many were purchased again
        event.purchaseTickets(3, TicketType.CONCESSION);
        assertEquals("wrong number purchased", 5, event.howManyTicketsPurchased(TicketType.MEMBER));
        assertEquals("wrong number purchased", 4, event.howManyTicketsPurchased(TicketType.STUDENT));
        assertEquals("wrong number purchased", 3, event.howManyTicketsPurchased(TicketType.CONCESSION));


    }


    /**
     * Test to display printing of purchased tickets
     */
    @Test
    public void printPurchasedTicketInformation() {
        //set up the map of zero cost for the different types of tickets
        Map<TicketType, Double> prices = new HashMap<>();
        prices.put(TicketType.MEMBER, 0.0);
        prices.put(TicketType.STUDENT, 0.0);
        prices.put(TicketType.CONCESSION, 0.0);

        FreeEvent event = new FreeEvent("Halloween Evening", EventType.FREE, 12, prices);
        event.purchaseTickets(5, TicketType.MEMBER);
        event.purchaseTickets(4, TicketType.STUDENT);
        event.printPurchasedTicketInformation();

        //add purchase of 3 concession tickets
        //then print out again
        event.purchaseTickets(3, TicketType.CONCESSION);
        event.printPurchasedTicketInformation();

    }
}