public class SubTask extends Task {

    String subTask;

    public SubTask(String nameTask, String descriptionTask, Status status, String subTask) {
        super(nameTask, descriptionTask, status);
        this.subTask = subTask;
    }

    public String getSubTask() {
        return subTask;
    }

    public void setSubTask(String subTask) {
        this.subTask = subTask;
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
        return "SubTask{" +
                "subTask='" + subTask + '\'' +
                "} " + super.toString();
    }
}
