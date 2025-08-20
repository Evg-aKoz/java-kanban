import java.util.HashMap;

// не совсем поняла как перенести запуск из Main сюда

public class TaskManager {

    private static int count = 0;
    int idTask;
    int idSubTask;
    int idEpic;

    HashMap<Integer, Task> allTasks = new HashMap<>();
    HashMap<Integer, SubTask> allSubTasks = new HashMap<>();
    HashMap<Integer, Epic> allEpics = new HashMap<>();

    void addTask(int count, Task task) {
        count = task.getId();
        task.setStatus(Status.NEW);
        allTasks.put(count, task);
    }

    void updateTasks(int idTask, Task task) {
        if (allTasks.containsKey(idTask)) {
            allTasks.put(idTask, task);
        }
    }

    void removeAllTasks() {
        allTasks.clear();
    }

    void removeIdTask(int idTask) {
            allTasks.remove(idTask);
    }

    void printAllTasks() {
        for (Integer keys : allTasks.keySet()) {
            String key = keys.toString();
            System.out.println(key + " " + allTasks.get(keys).toString());
        }
    }

    void printIdTask(int idTask) {
        if (allTasks.containsKey(idTask)) {
            System.out.println(idTask + " " + allTasks.get(idTask).toString());
        }
    }


    void addSubTask(int count, SubTask subTask) {
        count = subTask.getId();
        allSubTasks.put(count, subTask);
    }

    void updateSubTasks(int idSubTask, SubTask subTask) {
        if (allSubTasks.containsKey(idSubTask)) {
            allSubTasks.put(idSubTask, subTask);
        }
    }

    void removeAllSubTasks() {
        allSubTasks.clear();
    }

    void removeIdSubTask(int idSubTask) {
        allSubTasks.remove(idSubTask);
    }

    void printAllSubTasks() {
        for (Integer keys : allSubTasks.keySet()) {
            String key = keys.toString();
            System.out.println(key + " " + allSubTasks.get(keys).toString());
        }
    }

    void printIdSubTask (int idSubTask) {
        if (allSubTasks.containsKey(idSubTask)) {
            System.out.println(idSubTask + " " + allSubTasks.get(idSubTask).toString());
        }
    }


    void addEpic(int count, Epic epic) {
        count = epic.getId();
        updateStatusEpic(epic);
        allEpics.put(count, epic);
    }

    void updateEpics (int idEpic, Epic epic) {
        if (allEpics.containsKey(idEpic)) {
            updateStatusEpic(epic);
            allEpics.put(idEpic,epic);
        }
    }

    void removeAllEpics() {
        allEpics.clear();
    }

    void removeIdEpic (int idEpic) {
        allEpics.remove(idEpic);
    }

    void printAllEpics() {
        for (Integer keys : allEpics.keySet()) {
            String key = keys.toString();
            System.out.println(key + " " + allEpics.get(keys).toString());
        }
    }

    void printIdEpic(int idEpic) {
        if (allEpics.containsKey(idEpic)) {
            System.out.println(idEpic + " " + allEpics.get(idEpic).toString());
        }
    }

    void printAllSubTasksEpic(int idEpic) {
        if (allEpics.containsKey(idEpic)) {
            System.out.println((allEpics.get(idEpic)).getSubTasks());
        }
    }


    private Status updateStatusEpic(Epic epic) {
        if (epic.getSubTasks().isEmpty()) {
            epic.setStatus(Status.NEW);
        } else {
            for (SubTask subtask : epic.getSubTasks()) {
                if (!((subtask.getStatus()).equals(Status.IN_PROGRESS))) {
                    if (!((subtask.getStatus()).equals(Status.DONE))) {
                        epic.setStatus(Status.NEW);
                    } else {
                        epic.setStatus(Status.DONE);
                    }
                } else {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
        }
        return epic.getStatus();
        }
}








