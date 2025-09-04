package tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.Managers;
import taskmanager.TaskManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    TaskManager manager = Managers.getDefault();
    Epic epic1;
    Epic epic2;

    @BeforeEach
    public void beforeEach() {

        SubTask subTask1 = new SubTask(7, "Test addNewSubTask1", "Test addNewSubTask description1", Status.NEW, 7);
        SubTask subTask2 = new SubTask(55, "Test addNewSubTask2", "Test addNewSubTask description2", Status.DONE, 9);
        ArrayList<SubTask> subTasks1 = new ArrayList<>();
        subTasks1.add(subTask1);
        subTasks1.add(subTask2);
        epic1 = new Epic(9, "Test addNewEpic1", "Test addNewEpic description1", subTasks1);
        epic2 = new Epic(1, "Test addNewEpic2", "Test addNewEpic description2", subTasks1);
    }

    @Test
    public void addNewEpic() {

        manager.addEpic(epic1);
        final Epic savedEpic = manager.getEicById(1);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic1, savedEpic, "Эпики не совпадают.");

        final List<Epic> epics = manager.getEpics();

        assertNotNull(epics, "Эпики не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество эпиков.");
        assertEquals(savedEpic, epics.getFirst(), "Эпики не совпадают.");
    }

    @Test
    public void equalsEpicsAfterUpdateById() {

        manager.addEpic(epic1);
        manager.updateEpic(epic2);
        final Epic savedEpic = manager.getEicById(1);
        assertEquals(epic2, savedEpic, "Эпики не совпадают.");
    }

    @Test
    public void shouldReturnZeroAfterRemoveAllEpics() {

        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.removeAllEpics();
        assertTrue(manager.getEpics().isEmpty(), "Эпики остались в списке.");
    }

    @Test
    public void shouldReturnZeroAfterRemoveEpicById1() {

        manager.addEpic(epic1);
        manager.addEpic(epic2);
        manager.removeEpicById(1);
        final Epic savedEpic = manager.getEicById(1);
        assertNull(savedEpic, "Эпик остался в списке.");
    }
}