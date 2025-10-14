package taskmanager;

import tasks.*;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager {

    protected File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    private void addSubTaskFileBackedTaskManager(int id, SubTask subTask) {
        subTasks.put(id, subTask);
    }

    private void addTaskFileBackedTaskManager(int id, Task task) {
        tasks.put(id, task);
    }

    private void addEpicFileBackedTaskManager(int id, Epic epic) {
        epics.put(id, epic);
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAllTasks() {
        super.removeAllTasks();
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAllSubTasks() {
        super.removeAllSubTasks();
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeAllEpics() {
        super.removeAllEpics();
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeTaskById(int idTask) {
        super.removeTaskById(idTask);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeSubTaskById(int idSubTask) {
        super.removeSubTaskById(idSubTask);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeEpicById(int idEpic) {
        super.removeEpicById(idEpic);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        try {
            save();
        } catch (ManagerSaveException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() throws ManagerSaveException {
        try (Writer value = new FileWriter(file)) {
            if (file.length() == 0) {
                value.write("id,type,name,status,description,epic\n");
            }
            for (Task task : tasks.values()) {
                value.write(task.toString());
            } for (Epic epic : epics.values()) {
                value.write(epic.toString());
            } for (SubTask subTask : subTasks.values()) {
                value.write(subTask.toString());
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении файла.");
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        try {
            FileBackedTaskManager restoredManager = new FileBackedTaskManager(file);
            List<String> lines = Files.readAllLines(file.toPath());
            for (int i = 1; i < lines.size(); i++) {
                Task task = Task.fromString(lines.get(i));
                if (task instanceof SubTask) {
                    restoredManager.addSubTaskFileBackedTaskManager(task.getIdTask(), (SubTask) task);
                } else if (task instanceof Epic) {
                    restoredManager.addEpicFileBackedTaskManager(task.getIdTask(), (Epic) task);
                } else {
                    restoredManager.addTaskFileBackedTaskManager(task.getIdTask(), task);
                }
                }
            return restoredManager;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
