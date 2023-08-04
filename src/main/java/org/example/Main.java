package org.example;

public class Main {

    public static void simulatePassengerActions(Floor floor, Elevator elevator, int initialFloor, int destinationFloor,
                                                int initialLoad, int passengerWeight) {
        System.out.println("Passenger is on floor " + initialFloor);
        floor.callElevator(initialFloor, initialLoad);
        elevator.pressFloorButton(destinationFloor, passengerWeight);
        System.out.println("Passenger has reached the destination floor " + destinationFloor);
    }

    public static void main(String[] args) {

        Elevator elevator1 = new Elevator(1, 500);
        Elevator elevator2 = new Elevator(2, 500);

        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);

        floor1.addElevator(elevator1);
        floor2.addElevator(elevator2);

        simulatePassengerActions(floor1, elevator1, 1, 14, 0, 150); // Пассажир 1
        simulatePassengerActions(floor2, elevator2, 15, 1, 0, 200); // Пассажир 2

    }

}