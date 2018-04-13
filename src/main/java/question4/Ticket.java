package question4;

import java.text.NumberFormat;
import java.util.Locale;

public class Ticket {
    private final int number;
    private final TicketType type;
    private final double price;

    public Ticket(int number, TicketType type, double price) {
        super();
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public int getNumber() {
        return this.number;
    }

    public TicketType getType() {
        return this.type;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        NumberFormat pound = NumberFormat.getCurrencyInstance(Locale.UK);
        return this.number + ", " + this.type + ", " + pound.format(price);
    }

}
