package main;

import patterns.observer.TaskObserver;

public class User implements TaskObserver {
    @Override
    public void update(Task task) {
        System.out.println("Task added/updated: " + task.toString());
    }
}
