import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {
    public UUID courseID;
    public UUID teacher;
    public String name;
    public List<UUID> students = new ArrayList<UUID>();


    Course(UUID uuid, String name) {
        this.name = name;
        courseID = uuid;
    }

    public void addTeacher(UUID uuid) {
        teacher = uuid;
    }

    public void addStudent(UUID uuid) {
        students.add(uuid);
    }
}
