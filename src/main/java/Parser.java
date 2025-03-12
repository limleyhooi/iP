import java.util.ArrayList;

/**
 *Identify key command words such as "bye", "list", "find", "mark", "unmark", "delete", "todo", "deadline" and "event".
 */
public class Parser {

    /**
     *
     * @param input
     * @param taskList
     * @param storage
     * @param ui
     * @return true to exit program. If return false, while loop in Rick class will keep running parse method.
     */
    public static boolean parse(String input, TaskList taskList, Storage storage, Ui ui) {
        String trimmedInput = input.trim();

        if (trimmedInput.equals("bye")) {
            ui.showGoodbye();
            return true; // signal to exit the program
        } else if (trimmedInput.equals("list")) {
            ui.showTaskList(taskList.getTasks());
        }
        else if (trimmedInput.startsWith("find")) { // NEW: find command
            String keyword = trimmedInput.substring(4).trim();
            if (keyword.isEmpty()) {
                ui.showError("The search keyword cannot be empty.");
            } else {
                // Use TaskList's findTasks method to search for tasks containing the keyword
                ArrayList<Task> foundTasks = taskList.findTasks(keyword);
                ui.showFoundTasks(foundTasks);
            }
        }
        else if (trimmedInput.startsWith("mark")) {
            try {
                String noSpace = trimmedInput.replaceAll("\\s","");
                int index = Integer.parseInt(noSpace.substring(4)) - 1;
                Task task = taskList.getTask(index);
                task.markAsDone();
                ui.showTaskDone(task);
                storage.saveTasks(taskList.getTasks());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showError("Invalid task number for marking!");
            }
        } else if (trimmedInput.startsWith("unmark")) {
            try {
                String noSpace = trimmedInput.replaceAll("\\s","");
                int index = Integer.parseInt(noSpace.substring(6)) - 1;
                Task task = taskList.getTask(index);
                task.markAsUndone();
                ui.showTaskUndone(task);
                storage.saveTasks(taskList.getTasks());
            } catch (NumberFormatException |  IndexOutOfBoundsException e) {
                ui.showError("Invalid task number for unmarking!");
            }
        } else if (trimmedInput.startsWith("delete")) {
            try {
                String noSpace = trimmedInput.replaceAll("\\s","");
                int index = Integer.parseInt(noSpace.substring(6)) - 1;
                taskList.deleteTask(index);
                ui.showTaskDeleted();
                storage.saveTasks(taskList.getTasks());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                ui.showError("Invalid task number for deletion!");
            }
        } else if (trimmedInput.startsWith("todo")) {
            String description = trimmedInput.substring(4).trim();
            if (description.isEmpty()) {
                ui.showError("The description of a todo cannot be empty.");
            } else {
                Task task = new Todo(description);
                taskList.addTask(task);
                ui.showTaskAdded(task, taskList.size());
                storage.saveTasks(taskList.getTasks());
            }
        } else if (trimmedInput.startsWith("deadline")) {
            String[] parts = trimmedInput.substring(8).split("/by", 2);
            if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                ui.showError("Your deadline task needs a description and a due date.\nFormat: deadline <description> /by <date>");
            } else {
                String description = parts[0].trim();
                String by = parts[1].trim();
                Task task = new Deadline(description, by);
                taskList.addTask(task);
                ui.showTaskAdded(task, taskList.size());
                storage.saveTasks(taskList.getTasks());
            }
        } else if (trimmedInput.startsWith("event")) {
            // Expecting format: event <description> /from <start> /to <end>
            String[] parts = trimmedInput.substring(5).split("/from|/to");
            if (parts.length < 3 ||
                    parts[0].trim().isEmpty() ||
                    parts[1].trim().isEmpty() ||
                    parts[2].trim().isEmpty()) {
                ui.showError("Your event task needs a description and times.\nFormat: event <description> /from <start time> /to <end time>");
            } else {
                String description = parts[0].trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                Task task = new Event(description, from, to);
                taskList.addTask(task);
                ui.showTaskAdded(task, taskList.size());
                storage.saveTasks(taskList.getTasks());
            }
        } else {
            ui.showError("I'm sorry, but I don't know what that means.");
        }
        return false;
    }
}

