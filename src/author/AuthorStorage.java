package author;

public class AuthorStorage {
    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author author) {
        size++;

        if (size == array.length) {
            extend();
        }
        array[size-1] = author;
    }


    private void extend() {
        Author[] newArr = new Author[array.length + 10];

        for (int i = 0; i < array.length; i++) {
            newArr[i] = array[i];
        }
        array = newArr;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }


    }

    public Author getByIndex(int index) {
        if (index > size || index < 0) {
            System.out.println("0 books fuond for this Author");
            return null;

        } else {
            return array[index];
        }

    }}
