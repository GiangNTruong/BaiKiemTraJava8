package ra.run;

import java.util.Scanner;

public class ShopManagement {
    private static CategoryManager categoryManager = new CategoryManager();
    private static ProductManager productManager = new ProductManager();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************SHOP MENU************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                   categoryManager.displayCategoryMenu();
                    break;
                case 2:
                    productManager.displayProductMenu();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }
}
