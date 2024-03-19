import java.util.*;

public class Teacher extends Account {
    public boolean isSigned = false;
    public int score = 0;
    public List<Course> courses = new ArrayList<>();

    Teacher(String username, String password, UUID uuid) {
        changeUsername(username);
        changePassword(password);
        this.accountID = uuid;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public static void signUp() {
        String[] credentials = Menu.credentialsMenu(true);
        UUID uuid = Hogwarts.addTeacher(credentials);
        System.out.println("The UUID generated for you is: " + uuid.toString());
    }

    public static void login() {
        String[] credentials = Menu.credentialsMenu(false);
        Hogwarts.LoginResultTeacher lr = new Hogwarts.LoginResultTeacher();
        lr = Hogwarts.checkTeacher(credentials[0], credentials[1]);
        //Checking the login results
        if (lr.loginStatusValue == Hogwarts.loginStatus.success) {
            System.out.println("Success!");
            while (true) {
                Menu.teacherPanel(lr.teacher);
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (!Objects.equals(input, "b"))
                    Hogwarts.takeCourseTeacher(lr.teacher, UUID.fromString(input));
                else
                    break;
            }
        } else if (lr.loginStatusValue == Hogwarts.loginStatus.fail) {
            System.out.println("Invalid!");
        } else if (lr.loginStatusValue == Hogwarts.loginStatus.unsigned) {
            System.out.println("You account is not conformed yet! Stay away!\n"
                    + "UUID: " + lr.teacher.accountID);
        }
    }

    public void takeCourse(UUID uuid) {

    }
}
