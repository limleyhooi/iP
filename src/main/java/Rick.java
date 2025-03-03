import java.util.Scanner ;

public class Rick {
    private static final String DIVIDER = "    ____________________________________________________________";
    private static void printDivider() {
        System.out.println(DIVIDER);
    }


    public static void main(String[] args) {
        String logo =
                " ____  _      _    \n" +
                        "|  _ \\(_) ___| | __\n" +
                        "| |_) | |/ __| |/ /\n" +
                        "|  _ <| | (__|   < \n" +
                        "|_| \\_\\_|\\___|_|\\_\\";
        System.out.println("Hello from\n" + logo);
        System.out.println("""
                Hello, I'm rick
                What can I do for you?
                """);
        Scanner s = new Scanner(System.in);
        Task[] tasks = new Task[100]; //create an Task array called tasks that hold 100 Task elements;
        int i = 0;

        while (true) {
            System.out.print(">");
            String input = s.nextLine(); //enter string element;
            input = input.replaceAll("\\s","");//remove all spaces in user input.


            if (input.startsWith("bye")) {
                System.out.println("bye bye! Hope to see you soon ^-^ ");
                break;
            }
            if (input.startsWith("list")){
                if (i ==0) {
                    System.out.println(" No tasks added."); //tasks array is empty
                } else {
                    System.out.println("Here are the items in your list:");
                    for (int j = 0; j < i; j++) {
                        System.out.print(j + 1+"."); //iterate through tasks array;
                        System.out.println(tasks[j]);

                    }
                }
                continue;
            }
            if(input.startsWith("mark")){
                int taskIndex = Integer.parseInt(input.substring(4))-1; //finding the index to mark;
                if (taskIndex >= 0 && taskIndex < i) {
                    tasks[taskIndex].markAsDone();
                    printDivider();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(tasks[taskIndex]);
                    printDivider();
                } else {
                    System.out.println("Invalid task number!");
                }
                continue;
            }
            if(input.startsWith("unmark")){
                int taskIndex = Integer.parseInt(input.substring(6))-1;
                if (taskIndex >= 0 && taskIndex < i) {
                    tasks[taskIndex].markAsUndone();
                    printDivider();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(tasks[taskIndex]);
                    printDivider();
                } else {
                    System.out.println("Invalid task number!");
                }
                continue;
            }
            if(input.startsWith("todo")){
                input = input.substring(4);
                try{
                if(input.isEmpty()){
                    throw new RickException();
                }}catch(RickException e){
                    System.out.println("Add your description for todo! ಠ益ಠ");
                    continue;
                }
                tasks[i] = new Todo(input);
            }
            if(input.startsWith("deadline")){
                String modifiedInput = input.substring(8);//remove the word deadline;
                try{
                if(modifiedInput.isEmpty()){
                    throw new RickException();
                }}catch(RickException e){
                System.out.println("WHERE IS THE DESCRIPTION FOR DEADLINE?! (╯°□°）╯︵ ┻━┻ ");
                continue;
            }
                String[] part = modifiedInput.split("/by",2); //split modified string to description and date;
                String Deadline_description = part[0].trim();
                String Deadline_date = part[1].trim();
                tasks[i] = new Deadline(Deadline_description,Deadline_date);


            }
            if(input.startsWith("event")){
                String modifiedInput = input.substring(5);
                try{
                    if(modifiedInput.isEmpty()){
                        throw new RickException();
                    }}catch(RickException e) {
                    System.out.println("No description for event? Brilliant! ಠ_ಠ");
                    continue;
                }
                String[] part = modifiedInput.split("/from|/to", 3);
                String description = part[0].trim();
                String time1 = part[1].trim();
                String time2 = part[2].trim();
                int eventIndex = i + 1;
                tasks[i] = new Event(description, time1, time2);


            }

            System.out.println("Okie doki, added to task! ʕ•ᴥ•ʔ ");
            System.out.println(tasks[i]);
            System.out.println("Now you have "+ String.valueOf(i + 1)+ " tasks in list ✿");
            i++;


        }
    }

}
