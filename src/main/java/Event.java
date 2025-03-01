public class Event extends Task{
    protected String description;
    protected String time1;
    protected String time2;
    public Event(String description, String time1, String time2){
        super(description);
        this.time1 = time1;
        this.time2 = time2;
    }
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + time1 + " to: " + time2 + ")";
    }
}
