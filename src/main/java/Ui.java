import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String DIVIDER = "    ____________________________________________________________";
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo =
                " ____  _      _    \n" +
                        "|  _ \\(_) ___| | __\n" +
                        "| |_) | |/ __| |/ /\n" +
                        "|  _ <| | (__|   < \n" +
                        "|_| \\_\\_|\\___|_|\\_\\";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello, I'm rick (´｡• ᵕ •｡`)\nWhat can I do for you?");
        showDivider();
    }

    public void showGoodbye() {
        System.out.println("bye bye! Hope to see you soon (づ￣ ³￣)づ");
        showDivider();
    }

    public String readCommand() {
        System.out.print(">");
        return scanner.nextLine();
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(message);
        showDivider();
    }

    public void showMessage(String message) {
        System.out.println(message);
        showDivider();
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("Okie doki, added to task! ʕ•ᴥ•ʔ ");
        System.out.println(task);
        System.out.println("Now you have " + totalTasks + " tasks in list ᕙ(⇀‸↼‶)ᕗ");
        showDivider();
    }

    public void showTaskDone(Task task) {
        System.out.println("Nicee! I've marked this task as done ᕕ( ᐛ )ᕗ :");
        System.out.println(task);
        showDivider();
    }

    public void showTaskUndone(Task task) {
        System.out.println("Gotcha! I've unmarked this task (≧▽≦)");
        System.out.println(task);
        showDivider();
    }

    public void showTaskDeleted() {
        System.out.println("okie deleted!");
        showDivider();
    }

    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(" No tasks added (;ↀ⌓ↀ)");
        } else {
            System.out.println("Here are the items in your list ᕦ(ò_óˇ)ᕤ :");
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
        showDivider();
    }
    public void showFoundTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int index = 1;
            for (Task task : tasks) {
                System.out.println(index + ". " + task);
                index++;
            }
        }
        showDivider();
    }
}

