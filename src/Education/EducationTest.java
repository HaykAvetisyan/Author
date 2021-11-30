package Education;

import java.util.Scanner;

public class EducationTest {
    private static Scanner scanner = new Scanner(System.in);
    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();


    private static final String EXIT = "0";
    private static final String ADD_LESSON = "1";
    private static final String ADD_STUDENT = "2";
    private static final String PRINT_STUDENTS = "3";
    private static final String PRINT_STUDENTS_BY_LESSON = "4";
    private static final String PRINT_LESS0NS = "5";
    private static final String DELETE_LESSON_BY_NAME = "6";
    private static final String DELETE_STUDENT_BY_EMAIL = "7";


    public static void main(String[] args) {


        boolean isRun = true;
        while (isRun) {
            printCommands();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_LESSON:
                    addLesson();
                    break;
                case ADD_STUDENT:
                    addStudent();
                    break;
                case PRINT_STUDENTS:
                    studentStorage.print();
                    break;
                case PRINT_STUDENTS_BY_LESSON:
                    printStudentsByLesson();
                    break;
                case PRINT_LESS0NS:
                    lessonStorage.print();
                    break;
                case DELETE_LESSON_BY_NAME:
                    deleteLessonByName();
                    break;
                case DELETE_STUDENT_BY_EMAIL:
                    deleteStudentByEmail();
                    break;
                default:
                    System.out.println("invalid command!");

            }
        }
    }


    private static void addLesson() {
        System.out.println("please input lesson's title");
        String title = scanner.nextLine();

        System.out.println("please input lesson's lector name");
        String lectoreName = scanner.nextLine();


        System.out.println("please input lesson's duration");
        int duration = Integer.parseInt(scanner.nextLine());

        System.out.println("please input lesson's price");
        double price = Double.parseDouble(scanner.nextLine());

        if (lessonStorage.getLessonByTitle(title) == null) {
            Lesson lesson = new Lesson(title, lectoreName, duration, price);
            lessonStorage.add(lesson);
            System.out.println("Thank you the lesson was added!!!");
        } else {
            System.out.println("Lesson with that name already exist");
            addLesson();
        }

    }


    private static void addStudent() {
        System.out.println("please input student's name");
        String name = scanner.nextLine();
        System.out.println("please input student's surname");
        String surname = scanner.nextLine();
        System.out.println("please input student's age");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.println("please input student's email");
        String email = scanner.nextLine();
        System.out.println("please input student's phone");
        String phone = scanner.nextLine();
        System.out.println("please input student's lesson names");
        String nameOfLessons = scanner.nextLine();

        if (studentStorage.getByEmail(email) == null) {
            String[] names = nameOfLessons.split(",");
            Lesson[] lessons = new Lesson[names.length];
            for (int i = 0; i < lessons.length; i++) {
                if (lessonStorage.getLessonByTitle(names[i]) == null) {
                    System.out.println("Lesson with that name is not exist");
                } else {
                    lessons[i] = lessonStorage.getLessonByTitle(names[i]);
                }
            }
            Student student = new Student(name, surname, age, email, phone, lessons);
            studentStorage.add(student);
            System.out.println("student added");
        } else {
            System.out.println("student with this email already exist");
        }

    }


    private static void printStudentsByLesson() {
        System.out.println("please input lesson's title");
        String title = scanner.nextLine();
        if (lessonStorage.getLessonByTitle(title) != null) {
            studentStorage.getStudentByLesson(title);
        } else {
            System.out.println("We don't have such a lesson");
        }

    }


    private static void deleteLessonByName() {
        System.out.println("please input lesson's title");
        String title = scanner.nextLine();

        if (lessonStorage.getLessonByTitle(title) != null) {
            lessonStorage.deleteLesson(lessonStorage.getLessonByTitle(title));
            System.out.println("Thank you, lesson was deleted");
        } else {
            System.err.println("Invalid title: Lesson with this title does'nt exists");
        }
    }

    private static void deleteStudentByEmail() {

        System.out.println("please input student's email");
        String email = scanner.nextLine();
        if (studentStorage.getByEmail(email) != null) {
            studentStorage.delete(studentStorage.getByEmail(email));
            System.out.println("Thank you, student was deleted");
        } else {
            System.out.println("Invalid, email");
        }
    }


    private static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_LESSON + " for ADD_LESSON");
        System.out.println("Please input " + ADD_STUDENT + " for ADD_STUDENT");
        System.out.println("Please input " + PRINT_STUDENTS + " for  PRINT_STUDENTS");
        System.out.println("Please input " + PRINT_STUDENTS_BY_LESSON + " for PRINT_STUDENTS_BY_LESSON");
        System.out.println("Please input " + PRINT_LESS0NS + " for  PRINT_LESS0NS");
        System.out.println("Please input " + DELETE_LESSON_BY_NAME + " for DELETE_LESSON_BY_NAME");
        System.out.println("Please input " + DELETE_STUDENT_BY_EMAIL + " for DELETE_STUDENT_BY_EMAIL");

    }
}
