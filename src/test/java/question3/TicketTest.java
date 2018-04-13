/**
 * TicketSolutionTest.java
 */

package question3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Helen Treharne
 */
public class TicketTest {
  
  /**
   * Creating a Ticket object with a valid name and type.
   * Testing that the number and type 
   * can be retrieved correctly and that the toString method returns the correct string representation of the Ticket.
   */
  @Test
  public void testConstruction() {
    Ticket ticket = new Ticket(10, TicketType.STUDENT);

    assertEquals(10, ticket.getNumber());
    assertEquals(TicketType.STUDENT, ticket.getType());
  
    assertEquals("10, STUDENT",ticket.toString());
  }

  
}
