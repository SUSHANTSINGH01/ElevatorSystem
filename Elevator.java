package ElevatorSystem;

import java.util.LinkedList;
import java.util.Queue;

public class Elevator implements Runnable{

	 int totalFloors;
	 int currentFloor;
	 ElevatorStatus status;
	 Queue<Request>  requestQueue;
	 Object lock;
	 
	 public Elevator(int floor) {
		 this.totalFloors = floor;
		 this.currentFloor = 0;
		 this.status = ElevatorStatus.IDLE;
		 this.requestQueue = new LinkedList<>();
		 this.lock = new Object();
		 
		 Thread newThread = new Thread(this);
		 newThread.start();
		 
	 }
	 
	 public void addRequest(Request req) {
		 synchronized(lock){
			 System.out.println("New floor request added.");
			 requestQueue.add(req);
			 lock.notify();
		 }
	 }
	 
	 @Override
	 public void run() {
		 while(true) {
			  synchronized(lock) {
				  while(requestQueue.isEmpty()) {
					  try{
						  lock.wait();
					  }catch(InterruptedException e){
	                        Thread.currentThread().interrupt();
	                        return;
	                    }
				  }
			  }
			 if(!requestQueue.isEmpty())
			 handleRequest(requestQueue.poll()); 
		 }
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
