package tasks;

import java.util.Objects;

import static tasks.TypeTask.TASK;

public class Task {

    protected int idTask;
    protected TypeTask type;
    protected String nameTask;
    protected String descriptionTask;
    protected Status status;


    public Task(int idTask, TypeTask type, String nameTask, String descriptionTask, Status status) {
        this.idTask = idTask;
        this.type = TASK;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
        this.status = status;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        if (nameTask != null && !nameTask.isEmpty()) {
            this.nameTask = nameTask;
        }
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        if (descriptionTask != null && !descriptionTask.isEmpty()) {
            this.descriptionTask = descriptionTask;
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return idTask == task.idTask;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idTask);
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s,%s\n", idTask, type, nameTask, descriptionTask, status);
    }

    public static Task fromString(String value) {
        String[] parts = value.split(",");
        if (TypeTask.valueOf(parts[1]).equals(TASK)) {
            int idTask = Integer.parseInt(parts[0]);
            TypeTask type = TypeTask.valueOf(parts[1]);
            String nameTask = parts[2];
            String descriptionTask = parts[3];
            Status status = Status.valueOf(parts[4]);
            return new Task(idTask, type, nameTask, descriptionTask, status);
        } else if (TypeTask.valueOf(parts[1]).equals(TypeTask.SUBTASK)) {
                int idTask = Integer.parseInt(parts[0]);
                TypeTask type = TypeTask.valueOf(parts[1]);
                String nameTask = parts[2];
                String descriptionTask = parts[3];
                Status status = Status.valueOf(parts[4]);
                int idEpic = Integer.parseInt(parts[5]);
                return new SubTask(idTask, type, nameTask, descriptionTask, status, idEpic);
        } else if (TypeTask.valueOf(parts[1]).equals(TypeTask.EPIC)) {
            int idTask = Integer.parseInt(parts[0]);
            TypeTask type = TypeTask.valueOf(parts[1]);
            String nameTask = parts[2];
            String descriptionTask = parts[3];
            Status status = Status.valueOf(parts[4]);
            return new Epic(idTask, type, nameTask, descriptionTask, status);
        } else {
            throw new IllegalArgumentException("Неверный формат строки");
        }
    }
}








