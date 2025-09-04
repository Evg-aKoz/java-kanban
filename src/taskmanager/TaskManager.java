package taskmanager;

import tasks.Epic;
import tasks.SubTask;
import tasks.Task;

import java.util.List;

public interface TaskManager {

    List<Task> getHistory();

    List<Task> getTasks();

    List<SubTask> getSubTasks();

    List<Epic> getEpics();

    List<SubTask> getSubTasksByIdEpic(int idEpic);

    Task getTaskById(int idTask);

    SubTask getSubTaskById(int idSubTask);

    Epic getEicById(int idEpic);

    void addTask(Task task);

    void addEpic(Epic epic);

    void updateTask(Task task);

    void updateSubTask(SubTask subTask);

    void updateEpic(Epic epic);

    void removeAllTasks();

    void removeAllSubTasks();

    void removeAllEpics();

    void removeTaskById(int idTask);

    void removeSubTaskById(int idSubTask);

    void removeEpicById(int idEpic);
}
