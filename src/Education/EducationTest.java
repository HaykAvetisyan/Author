package Education;

import Education.model.Lesson;
import Education.model.Student;
import Education.model.User;
import Education.storage.LessonStorage;
import Education.storage.StudentStorage;
import Education.storage.UserStorage;
import Education.utill.MyDate;

import java.util.Date;
import java.util.Scanner;

public class EducationTest implements Commands, MyDate {
    private static Scanner scanner = new Scanner(System.in);
    private static LessonStorage lessonStorage = new LessonStorage();
    private static StudentStorage studentStorage = new StudentStorage();
    private static UserStorage userStorage = new UserStorage();


    public static void main(String[] args) {

        boolean isRun = true;
        while (isRun) {
            Commands.printMainMenuCommands();
            String menu = scanner.nextLine();
            switch (menu) {
                case EXIT:
                    isRun = false;
                    break;
                case LOG_IN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    System.out.println("Invalid command!!!");
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
            Student student = new Student(name, surname, age, email, phone, lessons, MyDate.printUtil(new Date()));
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

    private static void login() {
        System.out.println("please input user's email and password ,");
        String response = scanner.nextLine();
        String[] data = response.split(",");
        if (userStorage.getUserbyEmailandPassword(data[0], data[1]) == null) {
            System.out.println("Wrong email or password, Type Yes if you want to change your password? ");
            String answer = scanner.nextLine();
            if (answer.equals("Yes")) {
                System.out.println("please input user's email");
                String email = scanner.nextLine();
                User current = userStorage.getByEmail(email);
                if (current == null) {
                    System.out.println("Ops Wrong email");
                    login();
                } else {
                    System.out.println("Hi " + current.getName() + " " + current.getSurname());
                    System.out.println("please input new password");
                    String newPass = scanner.nextLine();
                    System.out.println("please input new password again");
                    String newPassVerify = scanner.nextLine();
                    if (newPass.equals(newPassVerify)) {
                        current.setPassword(newPass);
                        System.out.println("Password is changed!!!");
                    } else {
                        System.out.println("Passwords must match");
                    }

                }
            }
        } else {
            if (userStorage.getUserbyEmailandPassword(data[0], data[1]).getType().equals("admin")) {
                admin();
            } else {
                user();
            }
        }

    }

    private static void admin() {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
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

    private static void user() {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
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
                default:
                    System.out.println("invalid command!");

            }
        }
    }


    private static void register() {
        System.out.println("please input user's name,surname, email and password ,");
        String response = scanner.nextLine();
        String[] data = response.split(",");
        if (userStorage.getByEmail(data[2]) == null) {
            if (data[2].equals("admin") && data[3].equals("admin")) {
                String name = data[0];
                String surname = data[1];
                String email = data[2];
                String password = data[3];
                User admin = new User(name, surname, email, password, "admin");
                userStorage.add(admin);
            } else {
                String name = data[0];
                String surname = data[1];
                String email = data[2];
                String password = data[3];
                User admin = new User(name, surname, email, password, "user");
                userStorage.add(admin);
            }

        } else {
            System.out.println("User with this email already exist!");
        }

    }

}
