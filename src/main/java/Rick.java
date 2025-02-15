import java.util.Scanner ;

public class Rick {
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
        String[] tasks = new String[100];
        int i = 0;

        while (true) {
            System.out.print(">");
            String input = s.nextLine();


            if (input.equalsIgnoreCase("bye")) {
                System.out.println("bye bye! Hope to see you soon ^-^ ");
                break;
            }
            if (input.equalsIgnoreCase("list")){
                if (i ==0) {
                    System.out.println(" No tasks added.");
                } else {
                    for (int j = 0; j < i; j++) {
                        System.out.println(" " + (j + 1) + ". " + tasks[j]);

                    }
                }
                continue;
            }
            System.out.println("added: " + input);

            tasks[i] = input;
            i++;



        }
    }
}

