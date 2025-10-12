package taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static tasks.TypeTask.TASK;

class InMemoryHistoryManagerTest {

    HistoryManager historyManager = Managers.getDefaultHistory();

    Task task1;

    @BeforeEach
    public void beforeEach () {
        task1 = new Task(1, TASK,"Test addNewTask1", "Test addNewTask description1", Status.NEW);
    }

    @Test
    void addTask() {

        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");
    }

    @Test
    void orderOfAddTask() {

        historyManager.add(task1);
        Task task2 = new Task(2, TASK,"Test addNewTask2", "Test addNewTask description2", Status.IN_PROGRESS);
        historyManager.add(task2);
        final List<Task> history = historyManager.getHistory();
        assertEquals(task1, history.get(0), "Первая задача не в начале списка");
        assertEquals(task2, history.get(1), "Последняя задача не в конце списка");
    }

    @Test
    void updateHistoryById() {

        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();
        assertEquals(task1, history.getFirst(), "Задачи не совпадают.");
        task1 = new Task(1, TASK, "Test addNewTask1", "Test addNewTask description1", Status.IN_PROGRESS);
        historyManager.add(task1);
        final List<Task> newHistory = historyManager.getHistory();
        assertEquals(newHistory.getFirst(), history.getFirst(), "Задачи не совпадают.");
        assertEquals(task1, newHistory.getFirst(), "Задачи не совпадают.");
    }

    @Test
    void orderOfUpdatingHistoryIfIdEquals() {

        historyManager.add(task1);
        Task task2 = new Task(2,TASK, "Test addNewTask2", "Test addNewTask description2", Status.IN_PROGRESS);
        historyManager.add(task2);
        historyManager.add(task1);
        final List<Task> history = historyManager.getHistory();
        assertEquals(task2, history.get(0), "Первая задача не в начале списка");
        assertEquals(task1, history.get(1), "Последняя задача не в конце списка");
        assertEquals(2, history.size(), "Задач больше чем истории");
    }

    @Test
    void removeTaskById() {

        historyManager.add(task1);
        historyManager.remove(task1.getIdTask());
        final List<Task> history = historyManager.getHistory();
        assertEquals(0, history.size(), "После удаления задачи, история должна быть пустой.");
    }

    @Test
    void orderOfUpdatingHistoryAfterRemoveTaskById() {

        historyManager.add(task1);
        Task task2 = new Task(2, TASK,"Test addNewTask2", "Test addNewTask description2", Status.IN_PROGRESS);
        historyManager.add(task2);
        Task task3 = new Task(3, TASK, "Test addNewTask3", "Test addNewTask description3", Status.IN_PROGRESS);
        historyManager.add(task3);
        historyManager.remove(task2.getIdTask());
        final List<Task> history = historyManager.getHistory();
        assertEquals(task1, history.get(0), "Первая задача не в начале списка");
        assertEquals(task3, history.get(1), "Последняя задача не в конце списка");
        assertEquals(2, history.size(), "Задач больше чем истории");
    }
}