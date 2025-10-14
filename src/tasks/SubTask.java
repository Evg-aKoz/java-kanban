package tasks;

public class SubTask extends Task {

    protected int idEpic;

    public SubTask(int idTask, String nameTask, String descriptionTask, Status status, int idEpic) {
        super(idTask, nameTask, descriptionTask, status);
        this.idEpic = idEpic;
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
    public TypeTask getType() {
        return TypeTask.SUBTASK;
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%d\n",idTask, getType(), nameTask, descriptionTask, status, idEpic);
    }
}

