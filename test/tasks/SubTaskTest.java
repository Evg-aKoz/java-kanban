package tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.Managers;
import taskmanager.TaskManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SubTaskTest {

    TaskManager manager = Managers.getDefault();
    SubTask subTask1;
    SubTask subTask2;
    List<SubTask> subTasks1 = new ArrayList<>();
    Epic epic1;

    @BeforeEach
    public void beforeEach () {

        subTask1 = new SubTask(7, "Test addNewSubTask1", "Test addNewSubTask description1", Status.NEW, 7);
        subTask2 = new SubTask(2, "Test addNewSubTask2", "Test addNewSubTask description2", Status.DONE, 1);
        subTasks1.add(subTask1);
        epic1 = new Epic(9, "Test addNewEpic1", "Test addNewEpic description1", subTasks1);
    }

    @Test
    public void addNewSubTask() {

        manager.addEpic(epic1);
        final SubTask savedSubTask = manager.getSubTaskById(2);
        final List<SubTask> savedSubTasksList = manager.getSubTasksByIdEpic(1);

        assertNotNull(savedSubTask, "Подзадача не найдена.");
        assertEquals(subTask1, savedSubTask, "Подзадачи не совпадают.");
        assertEquals(savedSubTasksList.toString(), subTasks1.toString(),"Списки подзадач не совпадают.");

        final List<SubTask> subTasks = manager.getSubTasks();

        assertNotNull(subTasks, "Подзадачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество подзадач.");
        assertEquals(savedSubTask, subTasks.getFirst(), "Подзадачи не совпадают.");
    }

    @Test
    public void equalsSubTasksAfterUpdateById() {

        manager.addEpic(epic1);
        manager.updateSubTask(subTask2);
        final SubTask savedSubTask = manager.getSubTaskById(2);
        final SubTask savedSubTask2 = manager.getSubTasksByIdEpic(1).getFirst();
        assertEquals(subTask2, savedSubTask, "Подзадачи не совпадают.");
        assertEquals(subTask2, savedSubTask2, "Подзадачи не совпадают.");
    }

    @Test
    public void shouldReturnZeroAfterRemoveAllSubTasks () {

        subTasks1.add(subTask2);
        manager.addEpic(epic1);
        manager.removeAllSubTasks();
        assertTrue(manager.getSubTasks().isEmpty(), "Подзадачи остались в списке.");
        final List<SubTask> savedSubTasksList = manager.getSubTasksByIdEpic(1);
        assertTrue(savedSubTasksList.isEmpty(), "Подзадачи остались в эпике.");
    }

    @Test
    public void shouldReturnZeroAfterRemoveSubTaskById2 () {

        manager.addEpic(epic1);
        manager.removeSubTaskById(2);
        final SubTask savedSubTask = manager.getSubTaskById(2);
        assertNull(savedSubTask, "Задача осталась в списке.");
        final List<SubTask> savedSubTasksList = manager.getSubTasksByIdEpic(1);
        assertTrue(savedSubTasksList.isEmpty(), "Задача осталась в эпике");
    }
}