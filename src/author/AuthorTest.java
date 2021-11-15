package author;

import java.util.Scanner;

public class AuthorTest {
    public static void main(String[] args) {
        System.out.println("How many authors we have?");
        Scanner sc = new Scanner(System.in);
        int response = Integer.parseInt(sc.next());
        AuthorStorage au = new AuthorStorage();

        for (int i = 0; i < response ; i++) {
            System.out.println("Please type a name of author");
            String name = sc.next();
            System.out.println("Please type a surname of author");
            String surname = sc.next();
            System.out.println("Please type a age of author");
            int age = Integer.parseInt(sc.next());
            System.out.println("Please type a email of author");
            String email = sc.next();
            System.out.println("Please type a gender of author");
            String gender = sc.next();

            Author currentAuthor = new Author(name,surname,email,age,gender);

            au.add(currentAuthor);

        }

        au.print();




    }
}
