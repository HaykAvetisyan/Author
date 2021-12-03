package Education.storage;

import Education.model.Lesson;

public class LessonStorage {
    private Lesson[] lessons = new Lesson[10];
    private int size = 0;


    public void add(Lesson lesson) {
        if (size == lessons.length) {
            extend();
        }
        lessons[size++] = lesson;
    }

    private void extend() {
        Lesson[] tmp = new Lesson[lessons.length + 10];

        System.arraycopy(lessons, 0, tmp, 0, lessons.length);

        lessons = tmp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(lessons[i]);
        }
    }

    public Lesson getLessonByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (lessons[i].getName().equals(title)) {
                return lessons[i];
            }
        }
        return null;
    }

    public void deleteLesson(Lesson lessonByTitle) {
        for (int i = getIndex(lessonByTitle); i < size; i++) {
            lessons[i] = lessons[i + 1];
            size--;

        }

    }

    private int getIndex(Lesson lesson) {
        for (int i = 0; i < size; i++) {
            if (lessons[i].equals(lesson)) {
                return i;
            }
        }
        return -1;
    }
}
