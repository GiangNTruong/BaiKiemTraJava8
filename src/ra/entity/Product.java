package ra.entity;

import javax.swing.plaf.ListUI;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import ra.config.ShopConfig;
import ra.entity.Categories;
public class Product {
    private String productId;
    private String productName;
    private float price;
    private String description;
    private LocalDate created;
    private int catalogId;
    private ProductStatus productStatus;

    public Product() {
    }


    public enum ProductStatus {
        ACTIVE("Đang bán"),
        BLOCK("Hết hàng"),
        INACTIVE("Không bán");

        private final String description;

        ProductStatus(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
    public String getProductId() {
        return productId;
    }


    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }
    public void inputData (boolean isAdd, Scanner scanner , List<Product> productList , List<Categories> categoriesList){
        if (isAdd){
            this.productId = getInputProductId(scanner,productList);
        }
        this.productName = getInputProductName(scanner,productList);
        this.price = getInputPrice(scanner);
        System.out.println("Nhập mô tả sản phẩm");
        this.description = scanner.nextLine();
        this.created = getInputCreated(scanner);
        this.catalogId = inputCatalogId(scanner,categoriesList);
        this.productStatus = inputProductStatus(scanner);
    }
    public int inputCatalogId(Scanner scanner, List<Categories> categoriesList) {
        while (true) {
            System.out.println("Danh sách danh muc");
            for (Categories categories:categoriesList){
                System.out.println(categories.getCatalogId()+ "----" + categories.getCatalogName());
            }

            System.out.print("Nhập mã danh mục cho sản phẩm: ");
            try {
                int catalogId = Integer.parseInt(scanner.nextLine().trim());

                // Validate existence in categoriesList
                Categories selectedCategory = categoriesList.stream()
                        .filter(category -> category.getCatalogId() == catalogId)
                        .findFirst()
                        .orElse(null);

                if (selectedCategory != null) {
                    return catalogId;
                } else {
                    System.out.println("Mã danh mục không tồn tại. Vui lòng chọn từ danh sách trên.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập mã số nguyên.");
            }
        }
    }

    public LocalDate getInputCreated(Scanner scanner) {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Nhập vào ngày sinh dd/MM/yyyy");
            String productDateInput = sc.nextLine();
            try {
                return LocalDate.parse(productDateInput, ShopConfig.DTF);
            }catch (DateTimeParseException e){
                System.err.println("Koong dung dinh dang");
            }
        }
    }
    public String getInputProductId(Scanner scanner , List<Product> productList){
        final String regex= "^[CSA][A-Za-z0-9]{3}$";
        while (true){
            System.out.println("Nhập vào mã nhân viên");
            String productIdInput = scanner.nextLine();
            if (productIdInput.matches(regex)){
                // đúng định dạng -> kiểm tra trùng lặp
                if (productList.stream().noneMatch(t->t.getProductId().equals(productIdInput))){
                    // trùng lặp
                    return productIdInput;
                }
                System.err.println("Id đã tồn tại, vui long nhập giá trị khác");
            }else {
                System.err.println("Không đúng định dạng (C___)(S___)(A___)");
            }
        }
    }

    public String getInputProductName(Scanner scanner,List<Product> productList) {
        System.out.println("Nhập tên của sản phẩm");
        do {
            String productNameInput = scanner.nextLine();
            // 1. Độ dài tối đa 50 ký tự
            if (productNameInput.length() <= 50) {
                //đun định dang ->kiemr tra trung lap
                if(productList.stream().noneMatch(categories -> categories.getProductName().equals(productNameInput))){

                    return productNameInput;
                }
                System.err.println("tên đã tôn tại,nhập lại");
            } else {
                System.err.println("Tên danh mục tối đa 50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float getInputPrice(Scanner scanner ){
        System.out.println("Nhâp vào giá sản phẩm");
        do {
            float priceInput = Float.parseFloat(scanner.nextLine());
            if (priceInput>0){
                return priceInput;
            }else System.err.println("Nhập lại giá trị phải lớn hơn 0");
        }while (true);
    }
    public ProductStatus inputProductStatus(Scanner scanner) {
        while (true) {
            System.out.print("Nhập trạng thái sản phẩm (ACTIVE/BLOCK/INACTIVE): ");
            String statusInput = scanner.nextLine().toUpperCase();
            try {
                productStatus = ProductStatus.valueOf(statusInput);
                return productStatus;
            } catch (IllegalArgumentException e) {
                System.out.println("Trạng thái không hợp lệ. Vui lòng nhập 'ACTIVE', 'BLOCK' hoặc 'INACTIVE'.");
            }
        }
    }

    public void displayData() {
        System.out.printf("| ID : %-5s | Name : %-15s | price : %-10s  | description : %-20s  | created : %-10s | Categoryid : %-12d | Status : %-10s |\n",
                productId, productName, price, description, created.format(ShopConfig.DTF), catalogId , productStatus.getDescription());
    }


}
