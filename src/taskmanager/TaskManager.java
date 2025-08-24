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

    protected int getId() {
        return ++id;
    }

    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public ArrayList<SubTask> getSubTasksByIdEpic (int idEpic) {
        return new ArrayList<>((epics.get(idEpic)).getSubTasks());
    }

    public Task getTaskById (int idTask) {
        return tasks.get(idTask);
    }

    public SubTask getSubTaskById (int idSubTask) {
        return subTasks.get(idSubTask);
    }

    public Epic getEicById (int idEpic) {
        return epics.get(idEpic);
    }

    //удалила статус при добавлении задачи (хотя может если задачу добавляют, то статус должен быть
    // NEW, а не какой введет пользователь. А по updateTask пойдет обновление статуса)
    public void addTask(Task task) {
        task.setIdTask(getId());
        tasks.put(task.getIdTask(), task);
    }

    private void addSubTask(SubTask subTask) {
        subTask.setIdTask(getId());
        subTasks.put(subTask.getIdTask(), subTask);
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

    public void updateTask(Task task) {
        if (tasks.containsKey(task.getIdTask())) {
            tasks.put(task.getIdTask(), task);
        }
    }

    public void updateSubTask(SubTask subTask) {
        if (subTasks.containsKey(subTask.getIdTask())) {
            subTasks.put(subTask.getIdTask(), subTask);
            Epic updateEpic = epics.get(subTask.getIdEpic());
            ArrayList <SubTask> updateSudTasks = updateEpic.getSubTasks();
            for (int i = 0; i < updateSudTasks.toArray().length; i++) {
               if (updateSudTasks.get(i).getIdTask() == subTask.getIdTask()) {
                   updateSudTasks.set(i,subTask);
               }
            }
            updateStatusEpic(updateEpic);
            epics.put(subTask.getIdEpic(), updateEpic);
        }
    }

    public void updateEpic (Epic epic) {
        if (epics.containsKey(epic.getIdTask())) {
            epics.put(epic.getIdTask(), epic);
        }
    }


    public void removeAllTasks() {
        tasks.clear();
    }

  public void removeAllSubTasks() {
      for (Epic epic : epics.values()) {
          epic.getSubTasks().clear();
          updateStatusEpic(epic);
      }
      subTasks.clear();
  }

    public void removeAllEpics() {
        for (Epic epic : epics.values()) {
            for (SubTask subtask : epic.getSubTasks()) {
                subTasks.remove(subtask.getIdTask());
            }
        }
        epics.clear();
    }

    public void removeTaskById(int idTask) {
        tasks.remove(idTask);
    }

    public void removeSubTaskById(int idSubTask) {
        SubTask subtask = subTasks.get(idSubTask);
        Epic epic = epics.get(subtask.getIdEpic());
        epic.getSubTasks().remove(subtask);
        updateStatusEpic(epic);
        subTasks.remove(idSubTask);
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










