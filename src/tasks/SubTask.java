package tasks;

import static tasks.TypeTask.SUBTASK;

public class SubTask extends Task {

    protected int idEpic;

    public SubTask(int idTask, TypeTask type, String nameTask, String descriptionTask, Status status, int idEpic) {
        super(idTask, type, nameTask, descriptionTask, status);
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
        return super.getType();
    }

    @Override
    public void setType(TypeTask type) {
        this.type = SUBTASK;
    }

    public int getIdEpic() {
        return idEpic;
    }

    public void setIdEpic(int idEpic) {
        this.idEpic = idEpic;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%d\n",idTask, type, nameTask, descriptionTask, status, idEpic);
    }
}

