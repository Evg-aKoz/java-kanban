package taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();

    Task task1;

    @BeforeEach
    public void beforeEach () {
        task1 = new Task(1, "Test addNewTask1", "Test addNewTask description1", Status.NEW);
    }


    @Test
    void addHistory() {

        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void updateHistory() {
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();
        assertEquals(task1, history.getFirst(), "Задачи не совпадают.");
        task1 = new Task(1, "Test addNewTask1", "Test addNewTask description1", Status.IN_PROGRESS);
        historyManager.add(task1);
        final List<Task> newHistory = historyManager.getHistory();
        assertEquals(newHistory.get(0), history.getFirst(), "Задачи не совпадают.");
        assertEquals(task1, newHistory.get(1), "Задачи не совпадают.");
    }

    @Test
    void sizeHistoryMax10() {
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        historyManager.add(task1);
        Task task2 = new Task(2, "Test addNewTask2", "Test addNewTask description2", Status.IN_PROGRESS);
        historyManager.add(task2);
        final List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size(), "Добавлено больше 10 задач");
        assertEquals(task2, history.get(9), "Последняя задача не в конце списка");
    }
}