package Education;

public class StudentStorage {
    private Student[] students = new Student[10];
    private int size = 0;


    public void add(Student student) {
        if (size == students.length) {
            extend();
        }
        students[size++] = student;
    }

    private void extend() {
        Student[] tmp = new Student[students.length + 10];

        System.arraycopy(students, 0, tmp, 0, students.length);

        students = tmp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

    public Student getByEmail(String email) {
        for (int i = 0; i < size; i++) {
            if (students[i].getEmail().equals(email)) {
                return students[i];
            }
        }
        return null;
    }

    public void getStudentByLesson(String title) {
        for (int i = 0; i < size; i++) {
            int length = students[i].getLessons().length;
            for (int j = 0; j <length ; j++) {
                if(students[i].getLessons()[j].getName().equals(title)){
                    System.out.println(students[i]);
                }
            }
        }
    }

    public void delete(Student student) {
        for (int i = getIndex(student); i < size; i++) {
            students[i] = students[i + 1];
            size--;

        }

    }

    private int getIndex(Student student) {
        for (int i = 0; i < size; i++) {
            if (students[i].equals(student)) {
                return i;
            }
        }
        return -1;
    }
}
