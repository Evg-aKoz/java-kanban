package taskmanager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tasks.Status;
import tasks.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    TaskManager manager = Managers.getDefault();

    Task task1;


    @BeforeEach
    public void beforeEach () {
        task1 = new Task(9, "Test addNewTask1", "Test addNewTask description1", Status.NEW);

    }

    @Test
    void equalsIdTasksInManager() {
        manager.addTask(task1);
        final Task savedTask = manager.getTaskById(1);
        assertEquals(task1.getIdTask(), savedTask.getIdTask(), "ID конфликтуют в менеджере.");
    }
}