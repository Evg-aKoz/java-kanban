/*import taskmanager.FileBackedTaskManager;
import taskmanager.Managers;
import taskmanager.TaskManager;
import tasks.Epic;
import tasks.Status;
import tasks.SubTask;
import tasks.Task;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static tasks.TypeTask.*;*/

public class Main {

    public static void main(String[] args) {
       /*try {
            File file = File.createTempFile("file", ".csv");
            System.out.println("Временный файл создан: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        TaskManager manager = Managers.getDefault();
        FileBackedTaskManager fileManager = new FileBackedTaskManager(new File("file"));
       SubTask subTask3 = new SubTask(7, EPIC, "SubTask3", "SubTask description3", Status.NEW, 7);
        SubTask subTask4 = new SubTask(2, EPIC, "SubTask4", "SubTask description4", Status.NEW, 1);
        ArrayList<SubTask> subTasks2 = new ArrayList<>();
        subTasks2.add(subTask3);
        subTasks2.add(subTask4);
        Epic epic2 = new Epic(9, EPIC, "NewEpic1", "NewEpic description1", Status.DONE, subTasks2);
        manager.addEpic(epic2);
        fileManager.addEpic(epic2);
        Task task1 = new Task (4, TASK, "nameTask1", "descriptionTask1", Status.NEW);
        Task task2 = new Task(4, TASK, "nameTask2", "descriptionTask2", Status.DONE);
        manager.addTask(task1);
        fileManager.addTask(task1);
        manager.addTask(task2);
        fileManager.addTask(task2);
        SubTask subTask1 = new SubTask(7, SUBTASK, "SubTask1", "SubTask description1", Status.NEW, 7);
        SubTask subTask2 = new SubTask(2, SUBTASK, "SubTask2", "SubTask description2", Status.DONE, 1);
        ArrayList<SubTask> subTasks1 = new ArrayList<>();
        subTasks1.add(subTask1);
        subTasks1.add(subTask2);
        Epic epic1 = new Epic(9, EPIC, "NewEpic1", "NewEpic description1", Status.DONE, subTasks1);
        manager.addEpic(epic1);
        fileManager.addEpic(epic1);

        System.out.println(manager.getTasks());
        System.out.println(manager.getSubTasks());
        System.out.println(manager.getEpics());
        System.out.println(fileManager.getTasks());
        System.out.println(fileManager.getSubTasks());
        System.out.println(fileManager.getEpics());

        FileBackedTaskManager restoredManager = fileManager.loadFromFile(new File("file"));

        System.out.println(restoredManager.getTasks());
        System.out.println(restoredManager.getSubTasks());
        System.out.println(restoredManager.getEpics());*/
    }
}



