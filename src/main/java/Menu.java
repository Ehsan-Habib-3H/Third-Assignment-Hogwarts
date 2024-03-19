import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static final String red = "\u001b[38;5;160m";
    private static final String blue = "\u001b[38;5;81m";
    private static final String reset = "\u001b[0m";

    public static int runMenu() {
        // TODO: Menu will be shown here...
        System.out.println(blue + "Welcome to the Hogwarts (:\n" + red + "1. " + reset + "I'm teacher\n" + red + "" +
                "2. " + reset + "I'm student\n" + red + "3. " + reset + "I'm Assistant\n" + red +
                "4. " + reset + "I'm Farid");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (true) {
            if (Integer.parseInt(input) <= 5 && Integer.parseInt(input) > 0)
                return Integer.parseInt(input);
            input = scan.nextLine();
        }
    }

    public static int loginMenu() {
        System.out.println(blue + "Login\n" + red + "1. " + reset +
                "Login\n" + red + "2. " + reset + "Sign Up\n" + red + "[B.] " + reset +
                "Back");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        while (true) {
            if (input.equals("b"))
                return 3;
            if (Integer.parseInt(input) == 2 || Integer.parseInt(input) == 1)
                return Integer.parseInt(input);
            input = scan.nextLine();
        }
    }

    public static String[] credentialsMenu(boolean isSignUp) {
        String[] values = new String[2];
        System.out.println("Enter Username:");
        Scanner scanner = new Scanner(System.in);
        values[0] = scanner.nextLine();
        System.out.println("Enter password:");
        values[1] = scanner.nextLine();
        if (isSignUp)
            System.out.println("Done! wait for assistant to conform");
        return values;
    }

    public static void teacherPanel(Teacher teacher) {
        System.out.println(red + "Your score: " + reset + teacher.score + red + " Number of courses taken: " + reset + teacher.courses.size());
        System.out.println(red + "Here are courses you can take:" + reset);
        Hogwarts.viewAllCourses();
        System.out.println(red + "Enter the UUID of the course you wanna take:\n[B.]Back");
    }

    public static void studentPanel(Student student) {
        System.out.println(red + " Number of courses taken: " + reset + student.courses.size());
        System.out.println(red + "Here are courses you can take:" + reset);
        Hogwarts.viewAllCourses();
        System.out.println(red + "Enter the UUID of the course you wanna take:\n[B.]Back");
    }

    public static void assistantPanel(Assistant assistant) {
        System.out.println(blue + "Number of assistants: " + reset + Hogwarts.numAssistant() + blue + " Number of teachers: " + reset +
                Hogwarts.numTeacher() + blue + " Number of students: " + reset + Hogwarts.numStudent() + blue +
                " Number of courses: " + Hogwarts.numCourse() + reset);
        System.out.println(red + "Which section do you wanna enter?");
        System.out.println(red + "1. " + reset + "Assistants\n" + red + "2. " + reset + "Teachers\n" +
                red + "3. " + reset + "Students\n" + red + "4. " + reset + "Courses\n" +
                red + "[B.] " + reset + "Back");
    }

    public static void assitantMenu() {
        System.out.println(blue + "Here is the list of assistants:" + reset);
        Hogwarts.viewAllAssistants();
        System.out.println(red + "[A.] " + reset + "Add assistant\n" + red + " [B.] " + reset + "Back");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "a")) {
            String[] cred = credentialsMenu(false);
            UUID uuid = Hogwarts.addAssistant(cred);
            System.out.println("Assistant made. UUID: " + uuid);
        }
    }

    public static void teacherMenu() {
        System.out.println(blue + "Here is the list of Teachers:" + reset);
        Hogwarts.viewAllTeachers();
        System.out.println("Enter a teacher's UUID to sign/unsign or delete (UUID + -s or -d), " + red + "[a.]" + reset + " add or " + red + " [b.]" + reset + " back");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "b"))
            return;
        else if (Objects.equals(input, "a")) {
            Teacher.signUp();

        } else {
            if (input.endsWith("-d"))
                Hogwarts.removeTeacher(UUID.fromString(input.substring(0, input.length() - 3)));
            if (input.endsWith("-s"))
                Hogwarts.signTeacher(UUID.fromString(input.substring(0, input.length() - 3)));

        }
    }

    public static void studentMenu() {
        System.out.println(blue + "Here is the list of Students:" + reset);
        Hogwarts.viewAllStudents();
        System.out.println("Enter a student's UUID to delete (UUID + -d), " + red + "[a.]" + reset + " add or " + red + " [b.]" + reset + " back");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "b"))
            return;
        else if (Objects.equals(input, "a")) {
            Student.signUp();
        } else {
            if (input.endsWith("-d"))
                Hogwarts.removeStudent(UUID.fromString(input.substring(0, input.length() - 3)));

        }
    }

    public static void coursesMenu() {
        System.out.println(blue + "Here is the list of Courses:" + reset);
        Hogwarts.viewAllCourses();
        System.out.println("add: (name + -a) or delete(UUID + -d)" + red + " [b.]" + reset + " back");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (Objects.equals(input, "b"))
            return;
        else {
            if (input.endsWith("-d"))
                Hogwarts.removeCourse(UUID.fromString(input.substring(0, input.length() - 3)));
            if (input.endsWith("-a"))
                Hogwarts.addCourse(input.substring(0, input.length() - 3));

        }
    }

}
