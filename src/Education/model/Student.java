package Education.model;

import java.util.Arrays;
import java.util.Objects;

public class Student {
    private String name;
    private String surname;
    private int age;
    private String phone;
    private String email;
    private Lesson[] lessons;
    private String date;

    public Student(String name, String surname, int age, String phone, String email, Lesson[] lessons, String date) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.lessons = lessons;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Lesson[] getLessons() {
        return lessons;
    }

    public void setLessons(Lesson[] lessons) {
        this.lessons = lessons;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name) &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(phone, student.phone) &&
                Objects.equals(email, student.email) &&
                Arrays.equals(lessons, student.lessons) &&
                Objects.equals(date, student.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, surname, age, phone, email, date);
        result = 31 * result + Arrays.hashCode(lessons);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", lessons=" + Arrays.toString(lessons) +
                ", date='" + date + '\'' +
                '}';
    }
}
