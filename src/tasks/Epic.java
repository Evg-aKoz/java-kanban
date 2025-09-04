package tasks;
import java.util.List;

public class Epic extends Task {

protected  List <SubTask> subTasks;

    public Epic(int idTask, String nameTask, String descriptionTask, List<SubTask> subTasks) {
        super(idTask, nameTask, descriptionTask, Status.NEW);
        this.subTasks = subTasks;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SubTask> subTasks) {
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
