/**
 * TicketSolutionTest.java
 */

package question4;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Helen Treharne
 */
public class TicketTest {
  
  /**
   * Creating a Ticket object with a valid name and type and price.
   * Testing that the number and type and price
   * can be retrieved correctly and that the toString method returns the correct string representation of the Ticket.
   */
  @Test
  public void testConstruction() {
  
    Ticket ticket = new Ticket(10, TicketType.STUDENT,3.50);

    assertEquals(10, ticket.getNumber());
    assertEquals(TicketType.STUDENT, ticket.getType());
    assertEquals(3.50, ticket.getPrice(),0);

    assertEquals("10, STUDENT, £3.50",ticket.toString());
  
  }

  
}
