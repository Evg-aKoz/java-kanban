package taskmanager;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    static final int MAX_TASKS_HISTORY = 10;
    private final List<Task> history = new ArrayList<>(MAX_TASKS_HISTORY);

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            if (history.size() < MAX_TASKS_HISTORY) {
                history.add(task);
            } else {
                history.removeFirst();
                history.add(task);
            }
        }
    }
}
