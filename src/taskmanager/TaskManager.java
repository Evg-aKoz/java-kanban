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
        ArrayList<Task> allTasks = new ArrayList<>();
        for (Task task : tasks.values()) {
            allTasks.add(task);
        }
      return allTasks;
    }

    public ArrayList<SubTask> getSubTasks() {
        ArrayList<SubTask> allSubTasks = new ArrayList<>();
        for (SubTask subTask : subTasks.values()) {
            allSubTasks.add(subTask);
        }
        return allSubTasks;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> allEpics = new ArrayList<>();
        for (Epic epic : epics.values()) {
            allEpics.add(epic);
        }
        return allEpics;
    }

    public ArrayList<SubTask> getSubTasksByIdEpic (int idEpic) {
        ArrayList<SubTask> subTasksByIdEpic = new ArrayList<>();
            for (SubTask subtask : (epics.get(idEpic)).getSubTasks()) {
                subTasksByIdEpic.add(subtask);
            }
        return subTasksByIdEpic;
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

    //в таск оставла статус нью, как поняла по ТЗ при добавлении задачи он должен быть нью
    //если так не должно быть могу его убрать. Переделать, чтобы статуса не было в конструкторе у меня не получилось пока
    public void addTask(Task task) {
        task.setStatus(Status.NEW);
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
        if (epics.containsKey(getId())) {
            epics.put(getId(),epic);
        }
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeTaskById(int idTask) {
            tasks.remove(idTask);
    }

  public void removeAllSubTasks() {
      for (Epic epic : epics.values()) {
          epic.getSubTasks().clear();
          updateStatusEpic(epic);
      }
      subTasks.clear();
  }

    public void removeSubTaskById(int idSubTask) {
        SubTask subtask = subTasks.get(idSubTask);
        Epic epic = epics.get(subtask.getIdEpic());
        epic.getSubTasks().remove(subtask);
        updateStatusEpic(epic);
        subTasks.remove(idSubTask);
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










