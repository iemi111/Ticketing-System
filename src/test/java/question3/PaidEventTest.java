/**
 * EventSolutionTest.java
 */

package question3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the Event class.
 * 
 * @author Helen Treharne
 */
public class PaidEventTest {
  
/**
  * Creating a paid Event object with valid input parameters for the  event. 
  * Test that the name, type and number of seats can be retrieved correctly, 
  * and that the lists have been created successfully.
  */
  
@Test
public void testPaidConstruction() {
  PaidEvent event = new PaidEvent("Fireworks Evening",EventType.PAID,10);
  assertEquals("Fireworks Evening", event.getEventName());
  assertEquals(EventType.PAID, event.getEventType());
  assertEquals(10,event.getTotalNumberOfSeats());
  assertEquals(0,event.howManyTicketsPurchased(TicketType.MEMBER),0);
  assertEquals(0,event.howManyTicketsPurchased(TicketType.STUDENT),0);
} 


/**
 *  Test to calculate how many tickets have been purchased for an event
 *  Purchase 5 member tickets and 4 student tickets and then experiment to see that too
 *  many tickets can't be bought 
 */
@Test
public void howManyTicketsPurchased() {
  PaidEvent event = new PaidEvent("Fireworks Evening",EventType.PAID,10);
  event.purchaseTickets(5, 4);
  assertEquals("wrong number purchased",5, event.howManyTicketsPurchased(TicketType.MEMBER));
  assertEquals("wrong number purchased",4, event.howManyTicketsPurchased(TicketType.STUDENT));
  //try to buy number of tickets that can't because there aren't enough left
  event.purchaseTickets(1, 1);
  assertEquals("wrong number purchased",5, event.howManyTicketsPurchased(TicketType.MEMBER));
  assertEquals("wrong number purchased",4, event.howManyTicketsPurchased(TicketType.STUDENT));
  //ok so just buy one more
  event.purchaseTickets(1, 0);
  assertEquals("wrong number purchased",6, event.howManyTicketsPurchased(TicketType.MEMBER));
  assertEquals("wrong number purchased",4, event.howManyTicketsPurchased(TicketType.STUDENT));
  
}


/**
 * Test to display printing of purchased tickets
 */
@Test
public void printPurchasedTicketInformation() {
  PaidEvent event = new PaidEvent("Fireworks Evening",EventType.PAID,10);
  event.purchaseTickets(5, 4);
  event.printPurchasedTicketInformation();
  }
}