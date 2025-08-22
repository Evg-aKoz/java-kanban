package tasks;

import java.util.ArrayList;

public class Epic extends Task {

protected  ArrayList <SubTask> subTasks;

// если по заданию эпик наследник класса Таск, то как мне из него убирать статус?
    public Epic(String nameTask, String descriptionTask, Status status, int idTask, ArrayList<SubTask> subTasks) {
        super(nameTask, descriptionTask, status, idTask);
        this.subTasks = subTasks;
    }

    public ArrayList<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public Status getStatus() {
        return super.getStatus();
    }

    @Override
    public void setStatus(Status status) {
        super.setStatus(status);
    }

    @Override
    public int getIdTask() {
        return super.getIdTask();
    }

    @Override
    public void setIdTask(int idTask) {
        super.setIdTask(idTask);
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasks=" + subTasks +
                "} " + super.toString();
    }
}
