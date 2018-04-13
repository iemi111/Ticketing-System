/**
 * EventHistoryTest.java
 */

package question3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import org.junit.Test;

/**
 * Tests the EventHistory class.
 * 
 * @author Helen Treharne
 */
public class EventHistoryTest {


  /**
   * Creating an EventHistory object with an organisation. 
   * Testing that the object has been created. 
   * Test that the organisation information
   * has been stored correctly - this is only safe to do because Organisation 
   * is an immutable object.
   * 
   */
  @Test
  public void testConstruction() {
    Organisation organisation = new Organisation("Surrey University");
    EventHistory eventHistory = new EventHistory(organisation);
    assertNotNull(eventHistory);
    assertEquals("Surrey University", eventHistory.getOrganisation().getName());
  }

  /**
   * Creating an EventHistory object.
   * Add three free events and two paid events.
   * Display the resulting events.
   * 
   */
  @Test
  public void testDisplayFreeandPaidEvents() {
    Organisation organisation = new Organisation("Surrey University");
    EventHistory eventHistory = new EventHistory(organisation);
  
    FreeEvent event1 = new FreeEvent("Halloween Evening1",EventType.FREE,10);
    FreeEvent event2 = new FreeEvent("Halloween Evening2",EventType.FREE,10);
    FreeEvent event3 = new FreeEvent("Halloween Evening3",EventType.FREE,10);
    PaidEvent event4 = new PaidEvent("Fireworks Evening1",EventType.PAID,10);
    PaidEvent event5 = new PaidEvent("Fireworks Evening2",EventType.PAID,10);
  
    eventHistory.addFreeEvent(event1);
    eventHistory.addFreeEvent(event2);
    eventHistory.addFreeEvent(event3);
    eventHistory.addPaidEvent(event4);
    eventHistory.addPaidEvent(event5);

    assertEquals("Halloween Evening1: FREE"+"\n"+"Halloween Evening2: FREE"+"\n"+"Halloween Evening3: FREE"+"\n",eventHistory.displayFreeEvents());
    assertEquals("Fireworks Evening1: PAID"+"\n"+"Fireworks Evening2: PAID"+"\n",eventHistory.displayPaidEvents());
     
 }

  /**
   * Creating an EventHistory object with a valid organisation. 
   * Creating two dummy paid events and adding them to the EventHistory object.
   * Testing to see whether the events are sold out.
   * 
   */
  @Test
  public void testListOfSoldOutEvents() {
    Organisation organisation = new Organisation("Surrey University");
    EventHistory eventHistory = new EventHistory(organisation);
    FreeEvent freeEventObj = new FreeEvent("testEvent",EventType.FREE,4);
    PaidEvent paidEventObj = new PaidEvent("testEvent",EventType.PAID,2);
    PaidEvent paidEventObj2 = new PaidEvent("testEvent",EventType.PAID,2);
      
    //add three events to the events history
    eventHistory.addFreeEvent(freeEventObj);
    eventHistory.addPaidEvent(paidEventObj);
    eventHistory.addPaidEvent(paidEventObj2);
    
    //sell out on two of the events
    System.out.println("event");
    freeEventObj.purchaseTickets(2, 2);
    System.out.println("event2");
     paidEventObj.purchaseTickets(1, 1);
    
    List<String> list = eventHistory.listOfSoldOutEvents();
    //check that the number of sold out events is as expected
    assertEquals("wrong list stored",2, list.size());
    //check that the names of the sold out events also match
      for (String aList : list) {
          assertEquals("testEvent", aList);
      }
    }
  
}
