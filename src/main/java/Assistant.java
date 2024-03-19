import java.util.*;

public class Assistant extends Account {

    String promotedBy = null;

    //Empty constructor
    Assistant() {

    }

    //Constructor with arguments
    Assistant(String username, String password, UUID uuid) {
        this.accountID = uuid;
        this.changeUsername(username);
        this.changePassword(password);
    }

    public static void signUp() {

    }

    public String getPromotedBy() {
        return promotedBy == null ? "none" : promotedBy;
    }

    public static void login() {
        String[] credentials = Menu.credentialsMenu(false);
        Hogwarts.LoginResultAssistant lr = new Hogwarts.LoginResultAssistant();
        lr = Hogwarts.checkAssistant(credentials[0], credentials[1]);
        //Checking the login results
        if (lr.loginStatusValue == Hogwarts.loginStatus.success) {
            System.out.println("Success!");
            while (true) {
                Menu.assistantPanel(lr.assistant);
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                if (!Objects.equals(input, "b")) {
                    if (Objects.equals(input, "1"))
                        Menu.assitantMenu();
                    if (Objects.equals(input, "2"))
                        Menu.teacherMenu();
                    if (Objects.equals(input, "3"))
                        Menu.studentMenu();
                    if (Objects.equals(input, "4"))
                        Menu.coursesMenu();

                } else
                    break;
            }
        } else {
            System.out.println("Invalid!");
        }
    }
}
