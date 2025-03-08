import java.util.Scanner ;
import java.util.ArrayList;

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
                Hello, I'm rick (´｡• ᵕ •｡`)
                What can I do for you?
                """);

        Storage storage = new Storage();
        ArrayList<Task> tasks = storage.loadTasks();
        int i = tasks.size();

        Scanner s = new Scanner(System.in);




        while (true) {
            System.out.print(">");
            String originalInput = s.nextLine(); //enter string element;
            String input = originalInput.replaceAll("\\s","");//remove all spaces in user input.


            if (input.startsWith("bye")) {
                System.out.println("bye bye! Hope to see you soon (づ￣ ³￣)づ");
                break;
            }
            if (input.startsWith("list")){
                if (tasks.isEmpty()) {
                    System.out.println(" No tasks added (;ↀ⌓ↀ)"); //tasks array is empty
                } else {
                    System.out.println("Here are the items in your list ᕦ(ò_óˇ)ᕤ :");
                    int arrayIndex = 0;
                    for (Task task :tasks) {
                        System.out.print(arrayIndex + 1+"."); //iterate through tasks array;
                        System.out.println(task.toString());
                        arrayIndex++;

                    }
                }
                continue;
            }
            if(input.startsWith("mark")){
                int taskIndex = 0;//declare and initialise outside try-catch block;
                try {
                    taskIndex = Integer.parseInt(input.substring(4)) - 1; //finding the index to mark;
                    if (taskIndex >= 0 && taskIndex < i) {
                        tasks.get(taskIndex).markAsDone();
                        printDivider();
                        System.out.println(" Nicee! I've marked this task as done ᕕ( ᐛ )ᕗ :");
                        System.out.println(tasks.get(taskIndex));
                        printDivider();
                        storage.saveTasks(tasks);

                    } else {
                        System.out.println("Invalid task number!");
                    }
                }catch(NumberFormatException e){
                    System.out.println("only a number after mark! O_o");
                }
                continue;
            }
            if(input.startsWith("unmark")){
                int taskIndex = 0;
                try {
                    taskIndex = Integer.parseInt(input.substring(6)) - 1;
                    if (taskIndex >= 0 && taskIndex < i) {
                        tasks.get(taskIndex).markAsUndone();
                        printDivider();
                        System.out.println(" Gotcha! I've unmarked this task (≧▽≦)");
                        System.out.println(tasks.get(taskIndex));
                        printDivider();
                        storage.saveTasks(tasks);

                    } else {
                        System.out.println("Invalid task number!");
                    }
                }catch(NumberFormatException e){
                    System.out.println("only a number after unmark! o_O");
                }
                continue;
            }
            if(input.startsWith("todo")){
                input = input.substring(4);
                try{
                if(input.isEmpty()){
                    throw new RickException();
                }}catch(RickException e){
                    System.out.println("Your todo task needs a description with keyword: todo"+ System.lineSeparator()+"E.g. todo borrow books");
                    continue;
                }
                tasks.add(new Todo(input));
                storage.saveTasks(tasks);

            }
            if(input.startsWith("deadline")){
                String modifiedInput = input.substring(8);//remove the word deadline;
                try{
                if(modifiedInput.isEmpty()){
                    throw new RickException();
                }
                if (!modifiedInput.contains("/by")) {//check if user input contains keyword "/by"
                    throw new RickException();
                }}catch(RickException e) {
                    System.out.println("Your deadline task needs a description with keywords: deadline, /by" + System.lineSeparator() + " E.g. deadline return book /by sunday ");
                    continue;
                } catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Please provide both a description and a date using the /by keyword.");
                }
                String[] part = modifiedInput.split("/by",2); //split modified string to description and date;
                String Deadline_description = part[0].trim();
                String Deadline_date = part[1].trim();
                tasks.add(new Deadline(Deadline_description,Deadline_date));
                storage.saveTasks(tasks);



            }
            if(input.startsWith("event")) {
                String modifiedInput = input.substring(5);
                try {
                    if (modifiedInput.isEmpty()) {
                        throw new RickException();
                    }
                    if (!modifiedInput.contains("/from") | !modifiedInput.contains("/to")) {
                        throw new RickException();
                    }
                } catch (RickException e) {
                    System.out.println(" Your event task needs a description with keywords: event, /from & /to" + System.lineSeparator() + "E.g. event project meeting /from Mon 2pm /to 4pm ");
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Please provide both a description and a date using the /from & /to keyword.");
                    continue;
                }
                String[] part = modifiedInput.split("/from|/to", 3);
                String description = part[0].trim();
                String time1 = part[1].trim();
                String time2 = part[2].trim();
                int eventIndex = i + 1;
                tasks.add(new Event(description, time1, time2));
                storage.saveTasks(tasks);


            }
            if(input.startsWith("delete")) {
                String modified = input.substring(6).trim();
                try {
                    if (modified.isEmpty()) {
                        throw new RickException();
                    }
                    int deleteIndex = Integer.parseInt(modified) - 1;
                    tasks.remove(deleteIndex);
                } catch (RickException e) {
                    System.out.println("what do I help you delete?");
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid Number!");
                    continue;
                } catch (NumberFormatException e) {
                    System.out.print("Enter a number!");
                    continue;
                }
                storage.saveTasks(tasks);
                System.out.println("okie deleted!");
                int arrayIndex = 0;
                for (Task task :tasks) {
                    System.out.print(arrayIndex + 1+"."); //iterate through tasks array;
                    System.out.println(task.toString());
                    arrayIndex++;

                }
                continue;
            }



            try {
                Task task = tasks.get(i);

            } catch (IndexOutOfBoundsException e) {
                System.out.println("sorry pal, not a recognized command");
                continue;
            }

            System.out.println("Okie doki, added to task! ʕ•ᴥ•ʔ ");
            System.out.println(tasks.get(i).toString());
            System.out.println("Now you have "+ String.valueOf(i + 1)+ " tasks in list ᕙ(⇀‸↼‶)ᕗ");
            i++;


        }
    }

}
