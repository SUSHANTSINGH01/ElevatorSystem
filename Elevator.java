package ElevatorSystem;

public class Elevator {

	 int totalFloors;
	 int currentFloor;
	 ElevatorStatus status;
	 
	 public Elevator(int floor) {
		 this.totalFloors = floor;
		 this.currentFloor = 0;
		 this.status = ElevatorStatus.IDLE;
	 }
	 
	 public void handleRequest(Request req){
	      
		   int destFloor = req.getDestinationFloor();
		   System.out.println("\nRequest : Floor " + destFloor);
		 
	        if(destFloor>totalFloors) {
	            System.out.println("Invalid floor : " + destFloor);
	            return;
	        }

	        moveToFloor(destFloor);
	 }
	 
	 private void moveToFloor(int destFloor){
	        if(destFloor == currentFloor){
	            System.out.println("Elevator already at requested floor ");
	            return;
	        }

	        if(destFloor>currentFloor){
	            
	        	status = ElevatorStatus.MOVING_UP;
	            while(destFloor>currentFloor){
	                currentFloor++;
	                System.out.println("Moving up : Floor " + currentFloor);
	                try{
						Thread.sleep(1000);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
	            }
	        }else{
	        	
	            status = ElevatorStatus.MOVING_DOWN;
	            while(destFloor<currentFloor) {
	                currentFloor--;
	                System.out.println("Moving down : Floor " + currentFloor);
	                try{
						Thread.sleep(1000);
					}catch (InterruptedException e){
						e.printStackTrace();
					}
	            }
	        }
	        
	        status = ElevatorStatus.IDLE;
	        System.out.println("Arrived at floor " + destFloor);
	 }
	 
}
