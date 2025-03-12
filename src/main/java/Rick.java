/**
 * The main entry point of the application.
 */



public class Rick {
    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.loadTasks());

        ui.showWelcome();

        boolean exit = false;
        while (!exit) {
            String input = ui.readCommand();
            exit = Parser.parse(input, taskList, storage, ui);
        }
    }
}
