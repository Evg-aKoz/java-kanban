package taskmanager;

import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    protected int id = 0;

    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();


    public HashMap<Integer, Task> getTasks (){
      return tasks;
    }

    public HashMap<Integer, SubTask> getSubTasks (){
        return subTasks;
    }

    public HashMap<Integer, Epic> getEpics (){
        return epics;
    }

    public ArrayList<SubTask> getSubTasksByIdEpic (int idEpic) {
        ArrayList<SubTask> SubTasksByIdEpic = new ArrayList<>();
        for (SubTask subtask : subTasks.values()) {
            if (subtask.getIdEpic() == idEpic) {
                SubTasksByIdEpic.add(subtask);
            }
        }
        return SubTasksByIdEpic;
    }

    public Epic getEicById (int idEpic) {
        return epics.get(idEpic);
    }

    public Task getTaskById (int idTask) {
        return tasks.get(idTask);
    }

    public SubTask getSubTaskById (int idSubTask) {
        return subTasks.get(idSubTask);
    }

    protected int getId() {
        return ++id;
    }

    public void addTask(Task task) {
        task.setStatus(Status.NEW);
        task.setIdTask(getId());
        tasks.put(task.getIdTask(), task);
    }

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getIdTask())) {
            tasks.put(task.getIdTask(), task);
        }
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeTaskById(int idTask) {
            tasks.remove(idTask);
    }

    private void addSubTask(SubTask subTask) {
        subTask.setIdTask(getId());
        subTasks.put(subTask.getIdTask(), subTask);
    }

    public void updateSubTask(SubTask subTask) {
        if (subTasks.containsKey(subTask.getIdTask())) {
            subTasks.put(subTask.getIdTask(), subTask);
        }
    }

  public void removeAllSubTasks() {
      for (Epic epic : epics.values()) {
          epic.getSubTasks().clear();
          updateStatusEpic(epic);
      }
      subTasks.clear();
  }

    public void removeSubTaskById(int idSubTask) {
        for (Epic epic : epics.values()) {
            for (int i = 0; i < epic.getSubTasks().toArray().length; i++ ) {
                if ((epic.getSubTasks().get(i)).getIdTask() == idSubTask) {
                    epic.getSubTasks().remove(i);
                }
            }
            updateStatusEpic(epic);
        }
        subTasks.remove(idSubTask);
    }

    public void addEpic(Epic epic) {
        epic.setIdTask(getId());
        for (SubTask subtask : epic.getSubTasks()) {
            subtask.setIdEpic(epic.getIdTask());
            addSubTask(subtask);
        }
        updateStatusEpic(epic);
        epics.put(epic.getIdTask(), epic);
    }

    public void updateEpic (Epic epic) {
        if (epics.containsKey(getId())) {
            epics.put(getId(),epic);
        }
    }

    public void removeAllEpics() {
        for (Epic epic : epics.values()) {
            for (SubTask subtask : epic.getSubTasks()) {
                subTasks.remove(subtask.getIdTask());
            }
        }
        epics.clear();
    }

    public void removeEpicById (int idEpic) {
        for (SubTask subtask : (epics.get(idEpic)).getSubTasks()) {
            subTasks.remove(subtask.getIdTask());
        }
        epics.remove(idEpic);
    }

    private Status updateStatusEpic(Epic epic) {
        ArrayList<Status> status = new ArrayList<>();
        for (SubTask subtask : epic.getSubTasks()) {
            status.add(subtask.getStatus());
        }
        if (epic.getSubTasks().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            if (!(status.contains(Status.IN_PROGRESS))) {
                    if (!(status.contains(Status.NEW))) {
                        epic.setStatus(Status.DONE);
                    } else if (!(status.contains(Status.DONE))) {
                        epic.setStatus(Status.NEW);
                    } else {
                        epic.setStatus(Status.IN_PROGRESS);
                    }
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
        return epic.getStatus();
    }
}










