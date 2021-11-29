package onlineShop;

public class ProductStorage {
    private Product[] array = new Product[10];
    private int size = 0;

    public void add(Product product) {

        if (size == array.length) {
            extend();
        }
        array[size++] = product;
    }

    private void extend() {
        Product[] newArray = new Product[array.length + 10];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    public Product getByIndex(int index) {
        if (index < array.length) {

            return array[index];
        } else {
            System.out.println("Product not found");
            return null;
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }

    }

    public Product getProductbyName(String productName) {
        for (int i = 0; i < size; i++) {
            if (array[i].getName().equals(productName)){
                return array[i];
            }
        }
        return null;
    }

    public void update() {
        for (int i = 0; i < size; i++) {
            if(array[i].getCount()==0){
                deleteProduct(array[i]);
            }
        }
    }

    public void deleteProduct(Product product) {
        for (int i = getIndex(product); i < size; i++) {
            array[i] = array[i+1];
            size--;

        }

    }

    private int getIndex(Product product){
        for (int i = 0; i < size; i++) {
            if (array[i].equals(product)){
                return i;
            }
        }
        return -1;
    }
}
