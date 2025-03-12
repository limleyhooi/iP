
/**
 * The Task class provides basic functionalities for handling a task's description,
 * marking it as done or undone, and generating string representations of the task.
 */


public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }
    @Override
    public String toString(){
        return "[" + getStatusIcon() +"] " + description;
    }
    public String toDataString() {
        return "";
    }

}