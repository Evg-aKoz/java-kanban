package tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.Managers;
import taskmanager.TaskManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    TaskManager manager = Managers.getDefault();
    Task task1;
    Task task2;

    @BeforeEach
    public void beforeEach () {
        task1 = new Task(9, "Test addNewTask1", "Test addNewTask description1", Status.NEW);
        task2 = new Task(1, "Test addNewTask2", "Test addNewTask description2", Status.NEW);
    }

    @Test
    public void addNewTask() {

        manager.addTask(task1);
        final Task savedTask = manager.getTaskById(1);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task1, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = manager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(savedTask, tasks.getFirst(), "Задачи не совпадают.");
    }

    @Test
    public void equalsTasksAfterUpdateById() {

        manager.addTask(task1);
        manager.updateTask(task2);
        final Task savedTask = manager.getTaskById(1);
        assertEquals(task2, savedTask, "Задачи не совпадают.");
    }

    @Test
    public void shouldReturnZeroAfterRemoveAllTasks () {

        manager.addTask(task1);
        manager.addTask(task2);
        manager.removeAllTasks();
        assertTrue(manager.getTasks().isEmpty(), "Задачи остались в списке.");
    }

    @Test
    public void shouldReturnZeroAfterRemoveTaskById1 () {

        manager.addTask(task1);
        manager.addTask(task2);
        manager.removeTaskById(1);
        final Task savedTask = manager.getTaskById(1);
        assertNull(savedTask, "Задача осталась в списке.");
    }

    @Test
    public void addNewTaskAndSetName() {

        manager.addTask(task1);
        task1.setNameTask("Update Name");
        task1.getNameTask();
        final Task savedTask = manager.getTaskById(1);

        assertEquals(task1, savedTask, "Названия задач не совпадают.");
    }

    @Test
    public void addNewTaskAndSetDescriptionTask() {

        manager.addTask(task1);
        task1.setDescriptionTask("Update DescriptionTask");
        final Task savedTask = manager.getTaskById(1);

        assertEquals(task1, savedTask, "Описание задач не совпадают.");
    }

    @Test
    public void addNewTaskAndSetNameNull() {

        manager.addTask(task1);
        final Task savedTask = manager.getTaskById(1);
        task1.setNameTask(null);

        assertEquals(task1, savedTask, "Названия задач не совпадают.");
    }

    @Test
    public void addNewTaskAndSetNameEmpty() {

        manager.addTask(task1);
        final Task savedTask = manager.getTaskById(1);
        task1.setNameTask("");

        assertEquals(task1, savedTask, "Названия задач не совпадают.");
    }

    @Test
    public void addNewTaskAndSetDescriptionTaskNull() {

        manager.addTask(task1);
        task1.setDescriptionTask(null);
        final Task savedTask = manager.getTaskById(1);

        assertEquals(task1, savedTask, "Описание задач не совпадают.");
    }

    @Test
    public void addNewTaskAndSetDescriptionTaskEmpty() {

        manager.addTask(task1);
        task1.setDescriptionTask("");
        final Task savedTask = manager.getTaskById(1);

        assertEquals(task1, savedTask, "Описания задач не совпадают.");
    }
}