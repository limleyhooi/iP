public class Todo extends Task{
    public Todo(String description){
        super(description);
    }
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    @Override
    public String toDataString() {
        // Format: T | 1/0 | description
        return "T | " + (isDone ? "1" : "0") + " | " + this.description;
    }
}
