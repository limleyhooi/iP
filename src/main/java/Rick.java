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


            if (input.equalsIgnoreCase("bye")) {
                System.out.println("bye bye! Hope to see you soon ^-^ ");
                break;
            }
            if (input.equalsIgnoreCase("list")){
                if (i ==0) {
                    System.out.println(" No tasks added."); //tasks array is empty
                } else {
                    System.out.println("Here are the items in your list:");
                    for (int j = 0; j < i; j++) {
                        System.out.println((j + 1) + ".[" + tasks[j].getStatusIcon()+ "]"+ tasks[j].description); //iterate through tasks array;

                    }
                }
                continue;
            }
            if(input.startsWith("mark ")){
                int taskIndex = Integer.parseInt(input.substring(5))-1; //finding the index to mark;
                if (taskIndex >= 0 && taskIndex < i) {
                    tasks[taskIndex].markAsDone();
                    printDivider();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("[" + tasks[taskIndex].getStatusIcon()+"]" + tasks[taskIndex].description);
                    printDivider();
                } else {
                    System.out.println("Invalid task number!");
                }
                continue;
            }
            if(input.startsWith("unmark ")){
                int taskIndex = Integer.parseInt(input.substring(7))-1;
                if (taskIndex >= 0 && taskIndex < i) {
                    tasks[taskIndex].markAsUndone();
                    printDivider();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("[" + tasks[taskIndex].getStatusIcon()+"]" + tasks[taskIndex].description);
                    printDivider();
                } else {
                    System.out.println("Invalid task number!");
                }
                continue;
            }


            tasks[i] = new Task(input); //fill in element in tasks array of type Task;
            printDivider();
            System.out.println("ok! I've added " + input);
            printDivider();
            i++;



        }
    }

}
