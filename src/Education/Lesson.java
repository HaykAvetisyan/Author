package Education;

import java.util.Objects;

public class Lesson {
    private String  name;
    private String lectureName;
    private int duration;
    private double price;

    public Lesson(String name, String lectureName, int duration, double price) {
        this.name = name;
        this.lectureName = lectureName;
        this.duration = duration;
        this.price = price;
    }

    public Lesson() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return duration == lesson.duration &&
                Double.compare(lesson.price, price) == 0 &&
                Objects.equals(name, lesson.name) &&
                Objects.equals(lectureName, lesson.lectureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lectureName, duration, price);
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "name='" + name + '\'' +
                ", lectureName='" + lectureName + '\'' +
                ", duration=" + duration +
                ", price=" + price +
                '}';
    }
}
