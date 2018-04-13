/**
 * EventHistoryTest.java
 */

package question4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    //create maps for the prices of the ticket types for all the different event types
    Map<TicketType,Double> freePrices = new HashMap<>();
    freePrices.put(TicketType.MEMBER,0.0);
    freePrices.put(TicketType.STUDENT,0.0);
   
    
    Map<TicketType,Double> paidPrices = new HashMap<>();
    paidPrices.put(TicketType.MEMBER,10.0);
    paidPrices.put(TicketType.STUDENT,5.0);
     
    //create five different events, with names,event type, total seats and the prices
    FreeEvent event1 = new FreeEvent("Halloween Evening1",EventType.FREE,10,freePrices);
    FreeEvent event2 = new FreeEvent("Halloween Evening2",EventType.FREE,10,freePrices);
    FreeEvent event3 = new FreeEvent("Halloween Evening3",EventType.FREE,10,freePrices);
    PaidEvent event4 = new PaidEvent("Fireworks Evening1",EventType.PAID,10,paidPrices);
    PaidEvent event5 = new PaidEvent("Fireworks Evening2",EventType.PAID,10,paidPrices);
  
    //add them to the event lists encapsulated within the eventHistory object
    eventHistory.addFreeEvent(event1);
    eventHistory.addFreeEvent(event2);
    eventHistory.addFreeEvent(event3);
    eventHistory.addPaidEvent(event4);
    eventHistory.addPaidEvent(event5);

    //assert that they have have been stored
    assertEquals("Halloween Evening1: FREE"+"\n"+"Halloween Evening2: FREE"+"\n"+"Halloween Evening3: FREE"+"\n",eventHistory.displayFreeEvents());
    assertEquals("Fireworks Evening1: PAID"+"\n"+"Fireworks Evening2: PAID"+"\n",eventHistory.displayPaidEvents());
     
 }

  /**
   * Creating an EventHistory object with a valid organisation. 
   * Creating one free and two paid events and adding them to the EventHistory object.
   * Testing to see whether the events are sold out.
   * Simulating this by selling out on two of the events
   * 
   */
  @Test
  public void testListOfSoldOutEvents() {
    Organisation organisation = new Organisation("Surrey University");
    EventHistory eventHistory = new EventHistory(organisation);
    
    //create maps for the prices of the ticket types for all the different event types
    Map<TicketType,Double> freePrices = new HashMap<>();
    freePrices.put(TicketType.MEMBER,0.0);
    freePrices.put(TicketType.STUDENT,0.0);
    //not using this but we declare it anyway
    //because it is part of the type
 
    freePrices.put(TicketType.CONCESSION,0.0);    
    
    Map<TicketType,Double> paidPrices = new HashMap<>();
    paidPrices.put(TicketType.MEMBER,10.0);
    paidPrices.put(TicketType.STUDENT,5.0);
    //not using this but we declare it anyway
    //because it is part of the type
   paidPrices.put(TicketType.CONCESSION,7.50); 
    
      //create the objects with different numbers of total tickets
    FreeEvent freeEventObj = new FreeEvent("testEvent",EventType.FREE,4,freePrices);
    PaidEvent paidEventObj = new PaidEvent("testEvent",EventType.PAID,2,paidPrices);
    PaidEvent paidEventObj2 = new PaidEvent("testEvent",EventType.PAID,2,paidPrices);
      
    //add three events to the events history
    eventHistory.addFreeEvent(freeEventObj);
    eventHistory.addPaidEvent(paidEventObj);
    eventHistory.addPaidEvent(paidEventObj2);
    
    //sell out on two of the events
    //by just buying member and student tickets
    freeEventObj.purchaseTickets(2, TicketType.MEMBER);
    freeEventObj.purchaseTickets(2, TicketType.STUDENT);
   
    paidEventObj.purchaseTickets(1, TicketType.MEMBER);
    paidEventObj.purchaseTickets(1, TicketType.STUDENT);
   
     // do not purchase any tickets for paidEventObject2 so that it is not sold out
     
    List<String> list = eventHistory.listOfSoldOutEvents();
    //check that the number of sold out events is as expected, only expecting 2 sold out
    assertEquals("wrong list stored",2, list.size());
    //check that the names of the sold out events also match
      for (String aList : list) {
          assertEquals("testEvent", aList);
      }
    }

  /**
   * Creating an EventHistory object with a valid organisation. 
   * Creating one two paid events and adding them to the EventHistory object.
   * Testing to see whether the events and their associated purchased tickets give the correct
   * total costs.
   * 
   */
  @Test
  public void testcalculateTotalTicketCostOfPaidEvents() {
    Organisation organisation = new Organisation("Surrey University");
    EventHistory eventHistory = new EventHistory(organisation);
    
    //create maps for the prices of the ticket types for a paid event type
    Map<TicketType,Double> paidPrices = new HashMap<>();
    paidPrices.put(TicketType.MEMBER,10.0);
    //not using students and concession in the scenario but included in the map for completeness
    //of the map definition
    paidPrices.put(TicketType.STUDENT,5.0);
    paidPrices.put(TicketType.CONCESSION,7.50);
    
    //create the objects with total tickets
    PaidEvent paidEventObj = new PaidEvent("testEvent",EventType.PAID,4,paidPrices);
    PaidEvent paidEventObj2 = new PaidEvent("testEvent",EventType.PAID,4,paidPrices);
      
    //add two events to the events history
    eventHistory.addPaidEvent(paidEventObj);
    eventHistory.addPaidEvent(paidEventObj2);
    
    //choosing ticket purchases for the objects so that the overall calculate method has
    //to go round the loop twice for more than one paid event object
    // because going to have to go round the loop
    paidEventObj.purchaseTickets(2, TicketType.MEMBER);
    paidEventObj2.purchaseTickets(3, TicketType.MEMBER);
   
    //first lets check the individual costs again for each object
    //this is not strictly needed in the scenario because the test is on the overall
    //calculation but this just gives us confidence that the inner calculations
    //of the loop are still working
    assertEquals("wrong costs for paidEventObj",(2*10),paidEventObj.calculateTotalTicketCostOfPurchasedTickets(),0);
    assertEquals("wrong costs for paidEventObj2",(3*10.0),paidEventObj2.calculateTotalTicketCostOfPurchasedTickets(),0);
    
    
    //total cost should be 2 tickets at 10.0 for the first event object
    //and 3 tickets at 10.0 for the second event object
    assertEquals("wrong total cost",((2*10.0)+ (3*10.0)),eventHistory.calculateTotalTicketCostOfPaidEvents(),0);
    }
  
}
