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

        while (true) {
            System.out.print(">");
            String input = s.nextLine();


            if (input.equalsIgnoreCase("bye")) {
                System.out.println("bye bye! Hope to see you soon ^-^ ");
                break;
            }


            System.out.println(input);
        }
    }
}

