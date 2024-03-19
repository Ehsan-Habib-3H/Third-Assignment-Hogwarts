/*
    TODO: Import all the classes that you have defined and make use of them to build the program.
 */


import java.util.*;

public class Main {
    private static enum loginStatus {success, fail, unsigned}

    public static void main(String[] args) {
        while (true) {
            int result = Menu.runMenu();
            //Teacher
            if (result == 1) {
                result = Menu.loginMenu();
                //Login
                if (result == 1) {
                    Teacher.login();
                    continue;
                }
                //Sign Up
                if (result == 2) {
                    Teacher.signUp();
                    continue;
                }
                //Back
                if (result == 3)
                    continue;
                //Student
            } else if (result == 2) {
                result = Menu.loginMenu();

                if (result == 1) {
                    Student.login();
                    continue;
                }
                //Sign Up
                if (result == 2) {
                    Student.signUp();
                    continue;
                }
            }
            //Assistant
            else if (result == 3) {
                result = Menu.loginMenu();

                //Login
                if (result == 1) {
                    Assistant.login();
                    continue;
                }
                //Sign Up
                if (result == 2) {
                    Teacher.signUp();
                    continue;
                }
                //Back
                if (result == 3)
                    continue;
            } else if (result == 4) {
                System.out.println("Bye Farid...");
                System.exit(0);
            }
        }
    }


}
