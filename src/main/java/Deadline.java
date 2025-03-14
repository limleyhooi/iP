public class Deadline extends Task{

    protected String date;
    public Deadline(String description, String date){
        super(description);
        this.date = date;
    }
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
    @Override
    public String toDataString() {
        // Format: D | 1/0 | description | date
        return "D | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.date;
    }
}
