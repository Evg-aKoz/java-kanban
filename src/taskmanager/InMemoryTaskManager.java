package taskmanager;

import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    public HistoryManager historyManager = Managers.getDefaultHistory();

    protected int id = 0;

    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    protected int getId() {
        return ++id;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    @Override
    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> getSubTasksByIdEpic(int idEpic) {
        return new ArrayList<>((epics.get(idEpic)).getSubTasks());
    }

    @Override
    public Task getTaskById(int idTask) {
        if (tasks.containsKey(idTask)) {
            historyManager.add(tasks.get(idTask));
        }
        return tasks.get(idTask);
    }

    @Override
    public SubTask getSubTaskById(int idSubTask) {
        if (subTasks.containsKey(idSubTask)) {
            historyManager.add(subTasks.get(idSubTask));
        }
        return subTasks.get(idSubTask);
    }

    @Override
    public Epic getEicById(int idEpic) {
        if (epics.containsKey(idEpic)) {
            historyManager.add(epics.get(idEpic));
        }
        return epics.get(idEpic);
    }


    @Override
    public void addTask(Task task) {
        task.setIdTask(getId());
        tasks.put(task.getIdTask(), task);
    }

    private void addSubTask(SubTask subTask) {
        subTask.setIdTask(getId());
        subTasks.put(subTask.getIdTask(), subTask);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setIdTask(getId());
        for (SubTask subtask : epic.getSubTasks()) {
            subtask.setIdEpic(epic.getIdTask());
            addSubTask(subtask);
        }
        updateStatusEpic(epic);
        epics.put(epic.getIdTask(), epic);
    }


    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getIdTask())) {
            tasks.put(task.getIdTask(), task);
        }
    }


    @Override
    public void updateSubTask(SubTask subTask) {
        if (subTasks.containsKey(subTask.getIdTask())) {
            subTasks.put(subTask.getIdTask(), subTask);
            Epic updateEpic = epics.get(subTask.getIdEpic());
            List <SubTask> updateSudTasks = updateEpic.getSubTasks();
            for (int i = 0; i < updateSudTasks.toArray().length; i++) {
               if (updateSudTasks.get(i).getIdTask() == subTask.getIdTask()) {
                   updateSudTasks.set(i,subTask);
               }
            }
            updateStatusEpic(updateEpic);
            epics.put(subTask.getIdEpic(), updateEpic);
        }
    }


    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getIdTask())) {
            epics.put(epic.getIdTask(), epic);
        }
    }



    @Override
    public void removeAllTasks() {
        tasks.clear();
    }


  @Override
  public void removeAllSubTasks() {
      for (Epic epic : epics.values()) {
          epic.getSubTasks().clear();
          updateStatusEpic(epic);
      }
      subTasks.clear();
  }


    @Override
    public void removeAllEpics() {
        for (Epic epic : epics.values()) {
            for (SubTask subtask : epic.getSubTasks()) {
                subTasks.remove(subtask.getIdTask());
            }
        }
        epics.clear();
    }


    @Override
    public void removeTaskById(int idTask) {
        tasks.remove(idTask);
    }


    @Override
    public void removeSubTaskById(int idSubTask) {
        SubTask subtask = subTasks.get(idSubTask);
        Epic epic = epics.get(subtask.getIdEpic());
        epic.getSubTasks().remove(subtask);
        updateStatusEpic(epic);
        subTasks.remove(idSubTask);
    }


    @Override
    public void removeEpicById(int idEpic) {
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










