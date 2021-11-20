package AuthorBook;

public class BookStorage {
    private Book [] books = new Book[10];
    private int size = 0;


    public void add(Book book) {
        if (size == books.length) {
            extend();
        }
        books[size++] = book;
    }

    private void extend() {
        Book[] tmp = new Book[books.length + 10];

        System.arraycopy(books, 0, tmp, 0, books.length);

        books = tmp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(books[i]);
        }
    }

    public void searchBook(String keyword) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().contains(keyword) ||
                    books[i].getDescription().contains(keyword)) {
                System.out.println(books[i]);
            }
        }
    }

    public void searchBookByPrice(int minPrince, int maxPrice) {
        for (int i = 0; i < size; i++) {
            if (books[i].getPrice() >= minPrince &&
                    books[i].getPrice() <= maxPrice) {

                System.out.println(books[i]);
            }

        }

    }
}
