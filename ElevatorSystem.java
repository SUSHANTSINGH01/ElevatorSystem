package ElevatorSystem;

public class ElevatorSystem {

	private final Elevator elevator;
	
	public ElevatorSystem(int totalfloor) {
		this.elevator = new Elevator(totalfloor);
	}
	  
	public void sendRequest(Request req) {
        elevator.handleRequest(req);
    }
	
}
