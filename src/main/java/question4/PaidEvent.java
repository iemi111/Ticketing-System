package question4;

import java.util.Map;

public class PaidEvent extends Event {

    public PaidEvent(String name, EventType eventType, int total, Map<TicketType, Double> prices) {
        super(name, eventType, total, prices);
    }

    public double calculateTotalTicketCostOfPurchasedTickets() {
        double sumOfExistingTickets = 0.0;
        for (TicketType type : TicketType.values()) {
            sumOfExistingTickets += this.prices.get(type) * this.tickets.get(type).size();
        }
        return sumOfExistingTickets;
    }

}
