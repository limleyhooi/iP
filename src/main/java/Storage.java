import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File dataFolder;
    private File dataFile;

    // Constructor: sets up the folder and file
    public Storage() {
        // Relative path: "./data/duke.txt"
        dataFolder = new File("data");           // folder named 'data'
        dataFile = new File(dataFolder, "Rick.txt"); // file named 'duke.txt' in that folder

        // Create the folder if it doesn't exist
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }

        // Create the file if it doesn't exist
        try {
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }


    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(dataFile)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                // Example line: "T | 1 | read book"
                String[] parts = line.split("\\|");
                // Trim each part to remove leading/trailing spaces
                for (int i = 0; i < parts.length; i++) {
                    parts[i] = parts[i].trim();
                }
                // parts[0] = T/D/E, parts[1] = 1/0, the rest are description/time fields
                switch (parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        Deadline deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                        break;
                    default:
                        // Unrecognized type
                        System.out.println("Warning: Unrecognized task type in file: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
        }
        return tasks;
    }


    public void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(dataFile))) {
            for (Task task : tasks) {
                writer.println(task.toDataString());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
