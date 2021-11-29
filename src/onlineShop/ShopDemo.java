package onlineShop;

import java.util.Scanner;

public class ShopDemo {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductStorage productStorage = new ProductStorage();
    private static UserStorage userStorage = new UserStorage();

    // Main menu constants
    private static final String EXIT = "0";
    private static final String LOGIN = "1";
    private static final String REGISTRATION = "2";

    // user menu constants
    private static final String BACK = "0";
    private static final String EDIT_USER = "1";
    private static final String ADD_BALANCE = "2";
    private static final String BUY_PRODUCT = "3";

    // admin menu constants
    private static final String PRINT_USERS = "1";
    private static final String PRINT_PRODUCTS = "2";
    private static final String EDIT_PRODUCTS = "3";
    private static final String DELETE_USER = "4";
    private static final String FOR_UPDATE = "5";


    public static void main(String[] args) {
        Product zro = new Product("Coca", 5, 60);
        Product mek = new Product("Fanta", 4, 60);
        Product erku = new Product("Manta", 0, 60);

        productStorage.add(zro);
        productStorage.add(mek);
        productStorage.add(erku);


        boolean isrun = true;

        while (isrun) {
            printCommand();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isrun = false;
                    break;
                case LOGIN:
                    loginInterface();
                    break;
                case REGISTRATION:
                    registration();
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }


    }

    private static void registration() {
        System.out.println("please input your name");
        String name = scanner.nextLine();

        System.out.println("please input your surname");
        String surname = scanner.nextLine();

        System.out.println("please input your email");
        String email = scanner.nextLine();

        System.out.println("please input your gender");
        String gender = scanner.nextLine();

        System.out.println("please input your age");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("please input your password");
        String password = scanner.nextLine();

        System.out.println("please input your login");
        String login = scanner.nextLine();


        User user = new User(name, surname, age, gender, email, password, login);
        if (userStorage.getByEmail(user.getEmail()) != null) {
            System.err.println("Invalid email. Author with this email already exists");
        } else {
            userStorage.add(user);
            System.out.println("Congratulations ! You are registered in our shop");
        }

    }


    private static void loginInterface() {
        System.out.println("please input your login");
        String login = scanner.nextLine();

        System.out.println("please input your password");
        String password = scanner.nextLine();

        if (login.equals("Admin") && password.equals("Admin")) {
            adminInterface();
        } else {
            userInterface(login, password);
        }
    }

    private static void userInterface(String login, String password) {
        if (userStorage.check(login, password) != null) {
            User currentUser = userStorage.check(login, password);
            boolean isRun = true;
            while (isRun) {
                userCommands();
                String userCommand = scanner.nextLine();
                switch (userCommand) {
                    case BACK:
                        isRun = false;
                        break;
                    case EDIT_USER:
                        editUser(currentUser);
                        break;
                    case ADD_BALANCE:
                        addBalance(currentUser);
                        break;
                    case BUY_PRODUCT:
                        buyProduct(currentUser);
                        break;
                    default:
                        System.out.println("Invalid Command");


                }
            }
        } else {
            System.out.println("Forgot your password? !!!");

            System.out.println("please input your login");
            String logIn = scanner.nextLine();

            System.out.println("please input your email");
            String eMail = scanner.nextLine();
            User user = userStorage.getUserByEmailandLogin(eMail,logIn);
            if(user==null){
                System.out.println("Wrong email or login");
                loginInterface();
            }else {
                System.out.println("Now please type your new password");
                String pass = scanner.nextLine();
                System.out.println("type your password again");
                String passTwo = scanner.nextLine();
                if(pass.equals(passTwo)){
                    user.setPassword(pass);
                    System.out.println("Password changed!");
                }
                else {
                    System.out.println("Eror!!!");
                }
            }


        }

    }

    private static void buyProduct(User user) {
        productStorage.print();
        System.out.println("choose which product you want to buy");
        String productName = scanner.nextLine();
        Product product = productStorage.getProductbyName(productName);
        System.out.println("How many " + productName + " you want to buy?");
        int count = Integer.parseInt(scanner.nextLine());
        if (product != null) {
            if (user.getBalance() >= count * product.getPrice() && product.getCount() >= count) {
                double newBalance = user.getBalance() - count * product.getPrice();
                int newCount = product.getCount() - count;
                user.setBalance(newBalance);
                product.setCount(newCount);
            } else {
                System.out.println("You don't have enough money or We don't have that much " + productName);
            }
        } else {
            System.out.println("We don't have that product!!!");
        }
    }

    private static void addBalance(User check) {
        System.out.println("Type your money");
        double payment = Double.parseDouble(scanner.nextLine());
        userStorage.payment(check, payment);
        System.out.println("Your wallet updated!!!");
    }

    private static void editUser(User check) {
        System.out.println("please input your name");
        String name = scanner.nextLine();

        System.out.println("please input your surname");
        String surname = scanner.nextLine();

        System.out.println("please input your gender");
        String gender = scanner.nextLine();

        System.out.println("please input your age");
        int age = Integer.parseInt(scanner.nextLine());
        check.setAge(age);
        check.setGander(gender);
        check.setName(name);
        check.setSurname(surname);

        System.out.println("Your dates updated!!!");


    }



    private static void adminInterface() {
        boolean isRun = true;
        while (isRun) {
            adminCommands();
            String userCommand = scanner.nextLine();
            switch (userCommand) {
                case BACK:
                    isRun = false;
                    break;
                case PRINT_USERS:
                    userStorage.print();
                    break;
                case PRINT_PRODUCTS:
                    productStorage.print();
                    break;
                case EDIT_PRODUCTS:
                    editProduct();
                    break;
                case DELETE_USER:
                    deleteUser();
                    break;
                case FOR_UPDATE:
                    update();
                    break;
                default:
                    System.out.println("Invalid Command");


            }

        }
    }

    private static void editProduct() {
        System.out.println("please input product's name");
        String name = scanner.nextLine();

        if (productStorage.getProductbyName(name) == null) {
            System.err.println("Invalid name. Product with this name doesn't exists");
        } else {
            System.out.println("please input product's  new name");
            String newName = scanner.nextLine();
            System.out.println("please input product's  count");
            int count = Integer.parseInt(scanner.nextLine());
            System.out.println("please input product's  new name");
            double price = Double.parseDouble(scanner.nextLine());
            productStorage.getProductbyName(name).setName(newName);
            productStorage.getProductbyName(name).setCount(count);
            productStorage.getProductbyName(name).setPrice(price);

            System.out.println("Product updated!!!");
        }


    }

    private static void deleteUser() {
        System.out.println("please input user's email");
        String email = scanner.nextLine();

        if (userStorage.getByEmail(email) == null) {
            System.err.println("Invalid email. Author with this email doesn't exists");
        } else {
            userStorage.deleteUser(userStorage.getByEmail(email));
            System.out.println("User was deleted!!!");
        }


    }

    private static void update() {
        productStorage.update();
        System.out.println("Shop is updated!");
    }

    private static void adminCommands() {
        System.out.println("Please input " + BACK + " for EXIT");
        System.out.println("Please input " + PRINT_USERS + " for PRINT USERS");
        System.out.println("Please input " + PRINT_PRODUCTS + " for PRINT PRODUCTS");
        System.out.println("Please input " + EDIT_PRODUCTS + " for ADD PRODUCTS");
        System.out.println("Please input " + DELETE_USER + " for DELETE USER");
        System.out.println("Please input " + FOR_UPDATE + " for UPDATE");


    }

    private static void printCommand() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTRATION + " for REGISTRATION");


    }

    private static void userCommands() {
        System.out.println("Please Input " + BACK + " for BACK");
        System.out.println("Please Input " + EDIT_USER + " for edit your accaunt");
        System.out.println("Please Input " + ADD_BALANCE + " for ADD BALANCE your accaunt");
        System.out.println("Please Input " + BUY_PRODUCT + " for BUY PRODUCT");

    }
}
