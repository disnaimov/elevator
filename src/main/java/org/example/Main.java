package org.example;

public class Main {

    public static void simulatePassengerActions(Floor floor, Elevator elevator, int initialFloor, int destinationFloor,
                                                int initialLoad, int passengerWeight) {
        System.out.println("Passenger is on floor " + initialFloor);
        floor.callElevator(initialFloor, initialLoad);
        elevator.pressFloorButton(destinationFloor, passengerWeight);
        if (elevator.isPassengerDelivered()) {
            System.out.println("Passenger has reached the destination floor " + destinationFloor);
        } else {
            System.out.println("Passenger could not be delivered to the destination floor due to elevator being full.");
        }
    }

    public static void main(String[] args) {

        Elevator elevator1 = new Elevator(1, 400);
        Elevator elevator2 = new Elevator(2, 800);

        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);

        floor1.addElevator(elevator1);
        floor2.addElevator(elevator2);

        simulatePassengerActions(floor1, elevator1, 1, 14, 0, 90); // Пассажир 1
        simulatePassengerActions(floor2, elevator2, 15, 1, 0, 120); // Пассажир 2

    }

}