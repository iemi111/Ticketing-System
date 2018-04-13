package question4;

import java.util.Map;

public class FreeEvent extends Event {

    public FreeEvent(String name, EventType eventType, int total, Map<TicketType, Double> prices) {
        super(name, eventType, total, prices);
    }

}
