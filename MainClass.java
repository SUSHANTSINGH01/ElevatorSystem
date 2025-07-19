package ElevatorSystem;

public class MainClass {
	
	public static void main(String[] args) {
		
		ElevatorSystem elevatorSystem = new ElevatorSystem(12);
	
		elevatorSystem.sendRequest(new Request(8));
		elevatorSystem.sendRequest(new Request(5));
		elevatorSystem.sendRequest(new Request(15));
		elevatorSystem.sendRequest(new Request(9));
		elevatorSystem.sendRequest(new Request(12));
	}
}
