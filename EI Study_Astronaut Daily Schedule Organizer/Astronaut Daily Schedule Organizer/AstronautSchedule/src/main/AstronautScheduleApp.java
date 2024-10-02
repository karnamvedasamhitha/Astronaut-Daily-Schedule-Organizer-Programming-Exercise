package main;

import java.util.Scanner;

public class AstronautScheduleApp {
    public static void main(String[] args) {
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        scheduleManager.registerObserver(user);

        System.out.println("Welcome to the Astronaut Daily Schedule Organizer!");

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.println("Enter task description:");
                    String description = scanner.nextLine();

                    System.out.println("Enter start time (HH:mm):");
                    String startTime = scanner.nextLine();

                    System.out.println("Enter end time (HH:mm):");
                    String endTime = scanner.nextLine();

                    System.out.println("Enter priority (High, Medium, Low):");
                    String priority = scanner.nextLine();

                    Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                    scheduleManager.addTask(task);
                    break;

                case 2:
                    System.out.println("Enter the description of the task to remove:");
                    String removeDescription = scanner.nextLine();
                    scheduleManager.removeTask(removeDescription);
                    break;

                case 3:
                    scheduleManager.viewTasks();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
