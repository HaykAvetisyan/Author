package Education.storage;

import Education.model.User;

public class UserStorage {
    private User[] array = new User [10];
    private int size = 0;

    public void add(User user) {

        if (size == array.length) {
            extend();
        }
        array[size++] = user;
    }


    private void extend() {
        User[] newArr = new User[array.length + 10];

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

    public User getUserbyEmailandPassword(String email, String password) {
        for (int i = 0; i < size; i++) {
            if(array[i].getEmail().equals(email) && array[i].getPassword().equals(password)){
                return array[i];
            }
        }
        return null;
    }

    public User getByEmail(String email) {
        for (int i = 0; i <size ; i++) {
            if(array[i].getEmail().equals(email)){
                return array[i];
            }
        }
        return null;
    }
}
