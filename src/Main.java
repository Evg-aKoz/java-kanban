import taskmanager.FileBackedTaskManager;
import taskmanager.Managers;
import taskmanager.TaskManager;
import tasks.Status;
import tasks.Task;

import java.io.File;
import java.io.IOException;

import static tasks.TypeTask.TASK;

public class Main {

    public static void main(String[] args) {
        try {
            File file = File.createTempFile("file", ".csv");
            System.out.println("Временный файл создан: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskManager manager = Managers.getDefault();
        FileBackedTaskManager fileManager = new FileBackedTaskManager(new File("file"));
        Task task1 = new Task (4, TASK, "nameTask1", "descriptionTask1", Status.NEW);
        Task task2 = new Task(4, TASK, "nameTask2", "descriptionTask2", Status.DONE);
        manager.addTask(task1);
        fileManager.addTask(task1);
        manager.addTask(task2);
        fileManager.addTask(task2);
        System.out.println(manager.getTasks());
        System.out.println(fileManager.getTasks());
        FileBackedTaskManager restoredManager = fileManager.loadFromFile(new File("file"));
        System.out.println(restoredManager.getTasks());
    }
}



