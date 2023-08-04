package org.example;

//Кабина лифта

import java.util.Random;

public class Elevator {

    private Random random = new Random();

    private int id;
    private int currentFloor;
    private int capacity;
    private int currentLoad;
    private Direction direction;
    private DoorState doorState;

    private static final int FLOOR_MOVEMENT_DELAY = 1000;
    private static final int DOOR_OPERATION_DELAY = 2000;

    private enum Direction {
        UP,
        DOWN,
        IDLE
    }

    private enum DoorState {
        OPEN,
        CLOSED
    }

    public Elevator(int id, int capacity) {
        this.id = id;
        this.currentFloor = 1;
        this.capacity = capacity;
        this.currentLoad = 0;
        this.direction = Direction.IDLE;
        this.doorState = DoorState.CLOSED;
    }

    public void pressFloorButton(int floor, int passengerWeight) {
        if (floor < 1 || floor > 20) {
            System.out.println("Invalid floor number");
            return;
        }

        if (floor == currentFloor) {
            System.out.println("You are already on floor " + floor);
            doorState = DoorState.OPEN;
            System.out.println("Door is now open");
            return;
        }

        if (direction != Direction.IDLE && (direction == Direction.UP && floor > currentFloor) ||
                (direction == Direction.DOWN && floor < currentFloor)) {
            System.out.println("Elevator is already moving in the " + direction.name().toLowerCase() + " direction");
            return;
        }

        if (currentLoad + passengerWeight > capacity) {
            System.out.println("Elevator is overloaded");
            return;
        }

        doorState = DoorState.OPEN;
        currentLoad += passengerWeight;
        direction = floor > currentFloor ? Direction.UP : Direction.DOWN;
        System.out.println("Elevator " + id + " is moving " + direction + " to floor " + floor);


        try {
            Thread.sleep(FLOOR_MOVEMENT_DELAY);
        } catch (InterruptedException e) {
            System.out.println("Error: Failed to sleep");
            e.printStackTrace();
        }


        checkDoors();

        doorState = DoorState.CLOSED;
        System.out.println("Door is now closed");

        while (currentFloor != floor) {
            if (floor > currentFloor) {
                moveUp();
            } else {
                moveDown();
            }
        }

        doorState = DoorState.OPEN;
        if (currentFloor == floor) {
            System.out.println("Elevator " + id + " has arrived at floor " + floor);
        }
        System.out.println("Door is now open");
        currentLoad -= passengerWeight;
        direction = Direction.IDLE;

        try {
            Thread.sleep(DOOR_OPERATION_DELAY);
        } catch (InterruptedException e) {
            System.out.println("Error: Failed to sleep");
            e.printStackTrace();
        }
        checkDoors();
        doorState = DoorState.CLOSED;
        System.out.println("Door is now closed");
    }

    public void openDoor() {
        if (doorState == DoorState.OPEN) {
            System.out.println("Door is already open");
            return;
        }

        doorState = DoorState.OPEN;
        System.out.println("Door is open now");
    }

    public void closeDoor() {
        if (doorState == DoorState.CLOSED) {
            System.out.println("Door is already closed");
            return;
        }

        doorState = DoorState.CLOSED;
        System.out.println("Door is now closed");
    }

    public void callDispatcher() {
        System.out.println("Dispatcher has been called");
    }

    public void detectElevatorMoving() {
        System.out.println("Elevator has detected moving between doors");
    }

    public void detectNoElevatorMoving() {
        System.out.println("Elevator did not detected moving between doors");
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getId() {
        return id;
    }

    public boolean isDoorOpen() {
        return doorState == DoorState.OPEN;
    }

    private void moveUp() {
        if (direction == Direction.DOWN) {
            System.out.println("Elevator is moving down");
            return;
        }

        direction = Direction.UP;
        currentFloor++;
        System.out.println("Elevator " + id + " is moving up to floor " + currentFloor);

        try {
            Thread.sleep(FLOOR_MOVEMENT_DELAY);
        } catch (InterruptedException e) {
            System.out.println("Error: Failed to sleep");
            e.printStackTrace();
        }
    }

    private void moveDown() {
        if (direction == Direction.UP) {
            System.out.println("Elevator is moving up");
            return;
        }

        direction = Direction.DOWN;
        currentFloor--;
        System.out.println("Elevator " + id + " is moving down to floor " + currentFloor);

        try {
            Thread.sleep(FLOOR_MOVEMENT_DELAY);
        } catch (InterruptedException e) {
            System.out.println("Error: Failed to sleep");
            e.printStackTrace();
        }
    }

    private boolean sensorsReadings() {
        boolean sensorReading = random.nextBoolean();
        return sensorReading;
    }

    public void checkDoors() {
        boolean elevatorMoving = sensorsReadings();
        while (elevatorMoving) {
            detectElevatorMoving();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Error: Failed to sleep");
                e.printStackTrace();
            }
            elevatorMoving = sensorsReadings();
        }

        detectNoElevatorMoving();
    }
}
