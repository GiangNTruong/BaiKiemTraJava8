package ra.run;

import ra.design.IProductDesign;
import ra.impl.ProductImpl;

import java.util.Scanner;

public class ProductManager {
    static IProductDesign productDesign = new ProductImpl();
    public void displayProductMenu(){
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*******************PRODUCT MANAGEMENT*****************\n" +
                    "1. Nhập thông tin các sản phẩm\n" +
                    "2. Hiển thị thông tin các sản phẩm\n" +
                    "3. Sắp xếp các sản phẩm theo giá\n" +
                    "4. Cập nhật thông tin sản phẩm theo mã sản phẩm\n" +
                    "5. Xóa sản phẩm theo mã sản phẩm\n" +
                    "6. Tìm kiếm các sản phẩm theo tên sản phẩm\n" +
                    "7. Tìm kiếm sản phẩm trong khoảng giá a – b (a,b nhập từ bàn\n" +
                    "phím)\n" +
                    "8. Quay lại");
            System.out.println("Chọn chức năng");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    productDesign.addNew();
                    break;
                case 2:
                    productDesign.displayAll();
                    break;
                case 3:
                    productDesign.sortPriceProduct();
                    break;
                case 4:
                    productDesign.update();
                    break;
                case 5:
                    productDesign.delete();
                    break;
                case 6:
                    productDesign.findProductName();
                    break;
                case 7:
                    productDesign.findPriceAtoB();
                    break;
                case 8:
                    return;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        }while (true);
    }
}
