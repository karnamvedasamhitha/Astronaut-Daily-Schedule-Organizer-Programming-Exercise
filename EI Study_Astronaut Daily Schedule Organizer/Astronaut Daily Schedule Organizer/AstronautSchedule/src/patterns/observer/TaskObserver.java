package patterns.observer;

import main.Task;

public interface TaskObserver {
    void update(Task task);
}
