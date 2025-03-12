public class Event extends Task{
    protected String description;
    protected String startTime;
    protected String endTime;
    public Event(String description, String startTime, String endTime){
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
    @Override
    public String toDataString() {
        // Format: E | 1/0 | description | startTime| endTime
        return "E | " + (isDone ? "1" : "0") + " | " + this.description + " | " + this.startTime + " | " + this.endTime;
    }
}
