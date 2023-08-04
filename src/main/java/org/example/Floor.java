package org.example;

import java.util.ArrayList;
import java.util.List;

//Этаж
public class Floor {

    private int floorNumber;
    private List<Elevator> elevators;

    private int currentFloorElevator1;
    private String statusElevator1;
    private int currentFloorElevator2;
    private String statusElevator2;
    private boolean elevatorButtonStatus;

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.elevators = new ArrayList<>();
    }

    public void callElevator(int floor, int passengerWeight) {
        if (elevators.isEmpty()) {
            System.out.println("No elevators available");
            return;
        }

        Elevator closestElevator = null;
        int closestDistance = Integer.MAX_VALUE;

        for (Elevator elevator : elevators) {
            if (elevator.getCurrentFloor() == floor && elevator.isDoorOpen()) {
                System.out.println("Elevator is already here");
                return;
            }

            if (elevator.isDoorOpen()) {
                continue;
            }

            int distance = Math.abs(elevator.getCurrentFloor() - floor);

            if (distance < closestDistance) {
                closestDistance = distance;
                closestElevator = elevator;
            }
        }

        if (closestElevator != null) {
            closestElevator.pressFloorButton(floor, passengerWeight);
        } else {
            System.out.println("No available elevators");
        }

        // Вызываем остальные лифты
        for (Elevator elevator : elevators) {
            if (elevator != closestElevator) {
                elevator.pressFloorButton(floor, passengerWeight);
            }
        }
    }

    public void addElevator(Elevator elevator) {
        elevators.add(elevator);
    }

    public void removeElevator(Elevator elevator) {
        elevators.remove(elevator);
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void pressElevatorButton() {
        elevatorButtonStatus = true;
    }

    public int getCurrentFloorElevator1() {
        return currentFloorElevator1;
    }

    public void setCurrentFloorElevator1(int currentFloorElevator1) {
        this.currentFloorElevator1 = currentFloorElevator1;
    }

    public String getStatusElevator1() {
        return statusElevator1;
    }

    public void setStatusElevator1(String statusElevator1) {
        this.statusElevator1 = statusElevator1;
    }

    public int getCurrentFloorElevator2() {
        return currentFloorElevator2;
    }

    public void setCurrentFloorElevator2(int currentFloorElevator2) {
        this.currentFloorElevator2 = currentFloorElevator2;
    }

    public String getStatusElevator2() {
        return statusElevator2;
    }

    public void setStatusElevator2(String statusElevator2) {
        this.statusElevator2 = statusElevator2;
    }

    public boolean isElevatorButtonStatus() {
        return elevatorButtonStatus;
    }
}
