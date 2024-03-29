package ra.impl;

import ra.design.IProductDesign;
import ra.entity.Categories;
import ra.entity.Product;

import java.util.*;

import static ra.impl.CategoriesImpl.categoriesList;

public class ProductImpl implements IProductDesign {
    static List<Product> productList = new ArrayList<>();

    @Override
    public void addNew() {
        Scanner scanner= new Scanner(System.in);
        if (categoriesList.isEmpty()) {
            System.err.println("chưa có danh muc, them danh muc trươc");
            return;
        }
        System.out.println("Nhập so san pham ban muon them");
        byte count = Byte.parseByte(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.println("Nhập thông tin san phẩm " );
            Product product = new Product();
            product.inputData(true,scanner,productList,categoriesList);
            productList.add(product);
        }
        System.out.println("THêm thanh cong");
    }

    @Override
    public void displayAll() {
        if (productList.isEmpty()){
            System.err.println("Danh sách trống");
        }else {
            productList.forEach(Product::displayData);
        }
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập mã sản phẩm cần cập nhật:");
        String productId = scanner.nextLine();

        Product productToUpdate = findById(productId);
        if (productToUpdate == null) {
            System.err.println("Không tìm thấy sản phẩm với mã: " + productId);
            return;
        }

        System.out.println("Thông tin sản phẩm hiện tại:");
        productToUpdate.displayData();

        System.out.println("Nhập thông tin mới (trừ Mã sản phẩm):");
        productToUpdate.inputData(false,scanner, productList, categoriesList);

        System.out.println("Cập nhật thành công!");
    }



    @Override
    public void delete() {
        System.out.println("Hãy chọn department muốn xoa");
        String id = new Scanner(System.in).nextLine();
        Product delete = findById(id);
        if (delete == null) {
            System.err.println("id không tim thấy");
            return;
        }

        productList.remove(delete);

        System.out.println("Xóa thành cong");
    }

    @Override
    public Product findById(String id) {
        for (Product product : productList) {
            if (product.getProductId().equals(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void sortPriceProduct() {

        Collections.sort(productList, Comparator.comparing(Product::getPrice));

        for (Product product : productList) {
            product.displayData();
        }
    }

    @Override
    public void findProductName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm bạn muốn tìm kiếm:");
        String productName = scanner.nextLine().trim();

        // Tìm kiếm sản phẩm theo tên
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getProductName().toLowerCase().contains(productName.toLowerCase())) {
                foundProducts.add(product);
            }
        }

        // Hiển thị kết quả
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào với tên '" + productName + "'.");
        } else {
            System.out.println("Tìm thấy " + foundProducts.size() + " sản phẩm với tên '" + productName );
            for (Product product : foundProducts) {
                product.displayData();
            }
        }
    }


    @Override
    public void findPriceAtoB() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập giá tối thiểu (a):");
        float minPrice = scanner.nextFloat();
        System.out.println("Nhập giá tối đa (b):");
        float maxPrice = scanner.nextFloat();

        // Kiểm tra giá hợp lệ
        if (minPrice > maxPrice) {
            System.err.println("Giá tối thiểu phải nhỏ hơn hoặc bằng giá tối đa.");
            return;
        }

        // Tìm kiếm sản phẩm trong khoảng giá
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : productList) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                foundProducts.add(product);
            }
        }

        // Hiển thị kết quả
        if (foundProducts.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào trong khoảng giá " + minPrice + " - " + maxPrice + ".");
        } else {
            System.out.println("Tìm thấy " + foundProducts.size() + " sản phẩm trong khoảng giá " + minPrice + " - " + maxPrice + ":");
            for (Product product : foundProducts) {
                product.displayData();
            }
        }
    }

}
