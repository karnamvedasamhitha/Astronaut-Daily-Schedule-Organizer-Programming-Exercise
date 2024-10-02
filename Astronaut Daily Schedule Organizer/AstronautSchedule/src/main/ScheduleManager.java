package main;

import patterns.observer.TaskObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<TaskObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addTask(Task task) {
        if (validateTask(task)) {
            tasks.add(task);
            notifyObservers(task);
        } else {
            System.out.println("Error: Task conflicts with an existing task.");
        }
    }

    public void removeTask(String description) {
        Task taskToRemove = findTask(description);
        if (taskToRemove != null) {
            tasks.remove(taskToRemove);
            System.out.println("Task removed successfully.");
        } else {
            System.out.println("Error: Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
        } else {
            Collections.sort(tasks, Comparator.comparing(Task::getStartTime));
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }

    private boolean validateTask(Task newTask) {
        for (Task task : tasks) {
            if (overlaps(task, newTask)) {
                System.out.println("Error: Task conflicts with existing task \"" + task.getDescription() + "\".");
                return false;
            }
        }
        return true;
    }

    private boolean overlaps(Task task1, Task task2) {
        return !(task2.getEndTime().compareTo(task1.getStartTime()) <= 0 || task2.getStartTime().compareTo(task1.getEndTime()) >= 0);
    }

    private Task findTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return task;
            }
        }
        return null;
    }

    public void registerObserver(TaskObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(Task task) {
        for (TaskObserver observer : observers) {
            observer.update(task);
        }
    }
}
