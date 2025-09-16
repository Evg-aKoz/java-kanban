package taskmanager;

import tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> history = new ArrayList<>();
    private final HashMap<Integer, Node<Task>> historyMap = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;


    @Override
    public List<Task> getHistory() {
        history = new ArrayList<>();
        Node<Task> current = head;
        while (current != null) {
            history.add(current.task);
            current = current.next;
        }
        return new ArrayList<>(history);
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            if (historyMap.get(task.getIdTask()) == null) {
                linkLast(task);
            } else {
                removeNode(historyMap.get(task.getIdTask()));
                historyMap.remove(task.getIdTask());
                linkLast(task);
            }
        }
    }

    @Override
    public void remove(int id) {
        if (historyMap.get(id) != null) {
            removeNode(historyMap.get(id));
            historyMap.remove(id);
        }
    }

    private void linkLast(Task task) {
        final Node<Task> oldTail = tail;
        final Node<Task> newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        historyMap.put(task.getIdTask(), newNode);
    }

   private void removeNode(Node<Task> node) {
        if (node != null) {
            if (node == head) {
                head = head.next;
                if (head != null) {
                    head.prev = null;
                } else {
                    tail = null;
                }
            } else if (node == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                node.next.prev = node.prev;
                node.prev.next = node.next;
            }
        }
    }
}
