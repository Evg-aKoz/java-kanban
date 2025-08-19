import java.util.ArrayList;

public class Epic extends Task {

ArrayList <SubTask> subTasks;

    public Epic(String nameTask, String descriptionTask, Status status, ArrayList<SubTask> subTasks) {
        super(nameTask, descriptionTask, status);
        this.subTasks = subTasks;
        subTasks = new ArrayList<>();
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
        public int getId() {
            return ++id;
        }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasks=" + subTasks +
                "} " + super.toString();
    }
}
