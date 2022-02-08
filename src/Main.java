import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {
        try {
            loadItems("products.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            manageItems();
        }

    }

    public static void manageItems() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n\t********************MISC STORE***********\n");
            System.out.println(store + "\n");
            System.out.println("Options: \n\ta) Add to cart\n\tb) Remove from cart\n\tc) Checkout");
            String response = scanner.nextLine();

            switch (response) {
                case "a":
                    System.out.println("\nChoose an aisle number between: 1-7: ");
                    int row = scanner.hasNextInt() ? scanner.nextInt() - 1 : 404;
                    scanner.nextLine();
                    System.out.println("\nChoose an item number between: 1-3: ");
                    int column = scanner.hasNextInt() ? scanner.nextInt() - 1 : 404;
                    scanner.nextLine();

                    if (row == 404 || column == 404) {
                        continue;
                    } else if (row < 0 || row > 6 || column < 0 || column > 2) {
                        continue;
                    }
                    Item item = store.getItem(row, column);
                    if (!(cart.add(item))) {
                        System.out.println(item.getName() + " is already in your cart.");
                    } else {
                        System.out.println(item.getName() + " was added to your shopping cart.");
                    }

                    break;
                case "b":
                    if (cart.isEmpty()) {
                        continue;
                    }
                    System.out.println("Enter the item you'd like to remove: ");
                    String name = scanner.nextLine();
                    cart.remove(name);
                    break;
                case "c":
                    if(cart.isEmpty()){
                        continue;
                    }
                    System.out.println(cart.checkout());
                    scanner.close();
                    return;
                default:
                    continue;

            }
            System.out.println("\n\nSHOPPING CART\n\n" + cart);
            System.out.println("Enter anything to continue: ");
            scanner.nextLine();
        }
    }

    public static void loadItems(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis);

        for (int i = 0; scanner.hasNextLine(); i++) {
            String line = scanner.nextLine();
            String[] items = line.split(";");
            for (int j = 0; j < items.length; j++) {
                String[] fields = items[j].split("=");
                store.setItem(i, j, new Item(fields[0], Double.parseDouble(fields[1])));
            }
        }
        scanner.close();
    }
}

