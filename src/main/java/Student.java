import java.util.*;

public class Student extends Account {
    //Constructor
    Student(String username, String password, UUID uuid) {
        changeUsername(username);
        changePassword(password);
        this.accountID = uuid;
    }

    public static void signUp() {
        String[] credentials = Menu.credentialsMenu(false);
        UUID uuid = Hogwarts.addStudent(credentials);
        System.out.println("The UUID generated for you is: " + uuid.toString());
    }

    public static void login() {
        String[] credentials = Menu.credentialsMenu(false);
        Hogwarts.LoginResultStudent lr = new Hogwarts.LoginResultStudent();
        lr = Hogwarts.checkStudent(credentials[0], credentials[1]);
        //Checking the login results
        if (lr.loginStatusValue == Hogwarts.loginStatus.success) {
            System.out.println("Success!");
            while (true) {
                Menu.studentPanel(lr.student);
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (!Objects.equals(input, "b"))
                    Hogwarts.takeCourseStudent(lr.student, UUID.fromString(input));
                else
                    break;
            }
        } else if (lr.loginStatusValue == Hogwarts.loginStatus.fail) {
            System.out.println("Invalid!");
        }
    }

    public List<Course> courses = new ArrayList<Course>();

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


}
