package question3;

public class Ticket {
	private final int number;
	private final TicketType type;
	
	
public Ticket(int number, TicketType type){
	super();
	this.number=number;
	this.type=type;
}
	
public int getNumber(){
	return this.number;
}

public TicketType getType(){
	return this.type;
}

@Override
public String toString() {
	return number + ", " + type;
}



}
