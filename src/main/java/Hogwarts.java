import java.util.*;


public class Hogwarts {

    //Define Attributes
    private static final String red = "\u001b[38;5;160m";
    private static final String blue = "\u001b[38;5;81m";
    private static final String reset = "\u001b[0m";
    private static Map<UUID, Teacher> teachers = new HashMap<UUID, Teacher>();
    private static Map<UUID, Assistant> assistants = new HashMap<UUID, Assistant>();
    private static Map<UUID, Student> students = new HashMap<UUID, Student>();
    private static Map<UUID, Course> courses = new HashMap<UUID, Course>();


    public enum loginStatus {success, fail, unsigned}

    public static class LoginResultTeacher {
        loginStatus loginStatusValue;
        Teacher teacher;
    }

    public static class LoginResultStudent {
        loginStatus loginStatusValue;
        Student student;
    }

    public static class LoginResultAssistant {
        loginStatus loginStatusValue;
        Assistant assistant;
    }


    //Functions

    public static UUID addTeacher(String[] credentials) {
        UUID uuid = UUID.randomUUID();
        teachers.put(uuid, new Teacher(credentials[0], credentials[1], uuid));
        return uuid;
    }

    public static UUID addStudent(String[] credentials) {
        UUID uuid = UUID.randomUUID();
        students.put(uuid, new Student(credentials[0], credentials[1], uuid));
        return uuid;
    }

    public static void addCourse(String name) {
        UUID uuid = UUID.randomUUID();
        courses.put(uuid, new Course(uuid, name));
        System.out.println("The course added. UUID: " + uuid);
    }

    public static void removeTeacher(UUID uuid) {
        if (teachers.containsKey(uuid)) {
            Teacher teacher = teachers.get(uuid);
            for (Course course : teacher.courses)
                course.teacher = null;
            teachers.remove(uuid);
            System.out.println(red + "Teacher deleted!" + reset);
        } else
            System.out.println(red + "Teacher not found!" + reset);

    }

    public static void removeStudent(UUID uuid) {
        if (students.containsKey(uuid)) {
            Student student = students.get(uuid);
            for (Course course : student.courses)
                course.students.remove(uuid);
            students.remove(uuid);
            System.out.println(red + "Student deleted!" + reset);
        } else
            System.out.println(red + "Student not found!" + reset);

    }

    public static void removeCourse(UUID uuid) {
        if (courses.containsKey(uuid)) {
            courses.remove(uuid);
            System.out.println(red + "Course deleted!" + reset);
        } else
            System.out.println(red + "Course not found!" + reset);

    }

    public static void signTeacher(UUID uuid) {
        if (teachers.containsKey(uuid)) {
            Teacher teacher = teachers.get(uuid);
            teacher.isSigned = (!teacher.isSigned);
            System.out.println(red + "Teacher modified!" + reset);
        } else
            System.out.println(red + "Teacher not found!" + reset);

    }

    public static UUID addAssistant(String[] credentials) {
        UUID uuid = UUID.randomUUID();
        assistants.put(uuid, new Assistant(credentials[0], credentials[1], uuid));
        return uuid;
    }


    public static LoginResultTeacher checkTeacher(String username, String password) {
        LoginResultTeacher lr = new LoginResultTeacher();
        for (Teacher teacher : teachers.values()) {
            if (Objects.equals(teacher.getUsername(), username) && teacher.validatePassword(password)) {
                if (teacher.isSigned) {
                    lr.loginStatusValue = loginStatus.success;
                    lr.teacher = teacher;
                    return lr;
                } else {
                    lr.loginStatusValue = loginStatus.unsigned;
                    lr.teacher = teacher;
                    return lr;
                }
            }
        }
        lr.loginStatusValue = loginStatus.fail;
        return lr;
    }

    public static LoginResultStudent checkStudent(String username, String password) {
        LoginResultStudent lr = new LoginResultStudent();
        for (Student student : students.values()) {
            if (Objects.equals(student.getUsername(), username) && student.validatePassword(password)) {
                lr.loginStatusValue = loginStatus.success;
                lr.student = student;
                return lr;
            }
        }
        lr.loginStatusValue = loginStatus.fail;
        return lr;
    }

    public static LoginResultAssistant checkAssistant(String username, String password) {
        //The default assistant is admin
        if (assistants.size() == 0)
            assistants.put(UUID.randomUUID(), new Assistant("admin", "admin", UUID.randomUUID()));

        LoginResultAssistant lr = new LoginResultAssistant();
        for (Assistant assistant : assistants.values()) {
            if (Objects.equals(assistant.getUsername(), username) && assistant.validatePassword(password)) {
                lr.loginStatusValue = loginStatus.success;
                lr.assistant = assistant;
                return lr;
            }
        }
        lr.loginStatusValue = loginStatus.fail;
        return lr;
    }


    // TODO: Define Functionalities
    public static void viewAllTeachers() {
        int i = 0;
        for (Map.Entry<UUID, Teacher> teacher : teachers.entrySet()) {
            ++i;
            System.out.println(blue + i + ". ID: " + reset + teacher.getValue().accountID.toString() + blue + " Username: " + reset +
                    teacher.getValue().getUsername() + blue + " Signed: " + reset + (teacher.getValue().isSigned ? "y" : "n") + blue + " Courses:" + reset);

            for (Course course : teacher.getValue().getCourses())
                System.out.println("Course ID:" + course.courseID + " | Title: " + course.name);
        }
    }

    public static void viewAllStudents() {
        int i = 0;
        for (Map.Entry<UUID, Student> student : students.entrySet()) {
            ++i;
            System.out.println(blue + i + ". ID: " + reset + student.getValue().accountID.toString() + blue + " Username: " + reset +
                    student.getValue().getUsername() + blue + " Courses:" + reset);

            for (Course course : student.getValue().getCourses())
                System.out.println("Course ID: " + course.courseID + " | Title: " + course.name + " | Teacher ID: " + course.courseID);
        }
    }

    public static void viewAllAssistants() {
        int i = 0;
        for (Map.Entry<UUID, Assistant> assistant : assistants.entrySet()) {
            ++i;
            System.out.println(blue + i + ". ID: " + reset + assistant.getValue().accountID.toString() + blue + "username: " + reset +
                    assistant.getValue().getUsername() + blue + " Promoted by: " + reset + assistant.getValue().getPromotedBy() + reset);
        }
    }

    public static void viewAllCourses() {
        int i = 0;
//        UUID uuid = UUID.randomUUID();
//        courses.put(uuid, new Course(uuid, "asfd"));
//        uuid = UUID.randomUUID();
//        courses.put(uuid, new Course(uuid, "janks"));
//        uuid = UUID.randomUUID();
//        courses.put(uuid, new Course(uuid, "awrwer"));
        for (Map.Entry<UUID, Course> course : courses.entrySet()) {
            ++i;
            System.out.println(blue + i + ". ID: " + reset + course.getValue().courseID.toString() + blue + " title: " + reset + course.getValue().name + blue + " Students:" + reset);

//            course.getValue().students.add(UUID.randomUUID());
//            course.getValue().students.add(UUID.randomUUID());
            for (UUID uuid2 : course.getValue().students)
                System.out.println(uuid2.toString());
            System.out.println(blue + "Teacher assigned: " + reset + ((course.getValue().teacher == null) ? ("none") : (course.getValue().teacher)));
        }
    }

    public static void takeCourseTeacher(Teacher teacher, UUID uuid) {
        if (courses.containsKey(uuid)) {
            if (courses.get(uuid).teacher == teacher.accountID) {
                System.out.println("You've already taken this!");
            } else {
                courses.get(uuid).addTeacher(teacher.accountID);
                teacher.courses.add(courses.get(uuid));
                System.out.println(red + "Done!" + reset);
            }
        } else
            System.out.println("The UUID doesn't exist!");
    }

    public static void takeCourseStudent(Student student, UUID uuid) {
        if (courses.containsKey(uuid)) {
            if (courses.get(uuid).students.contains(student.accountID)) {
                System.out.println("You've already taken this!");
            } else {
                courses.get(uuid).addStudent(student.accountID);
                student.courses.add(courses.get(uuid));
                System.out.println(red + "Done!" + reset);
            }
        } else
            System.out.println("The UUID doesn't exist!");
    }

    public static int numAssistant() {
        return assistants.size();
    }

    public static int numTeacher() {
        return teachers.size();
    }

    public static int numStudent() {
        return students.size();
    }

    public static int numCourse() {
        return courses.size();
    }


}
