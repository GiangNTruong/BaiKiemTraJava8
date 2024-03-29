package ra.impl;

import ra.design.ICategories;
import ra.entity.Categories;
import ra.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static ra.impl.ProductImpl.productList;

public class CategoriesImpl implements ICategories {

    static List<Categories> categoriesList = new ArrayList<>();
    @Override
    public Categories changerOfStatus(int id, boolean active) {
        for (Categories categories : categoriesList){
            if (categories.getCatalogId()==id){
                categories.setCatalogStatus(active);
                return categories;
            }
        }
        System.err.println("Khong ti thấy phòng ban với ID:" + id);
        return null;
    }

    @Override
    public void addNew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong danh muc ban muon them");
        byte count = Byte.parseByte(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            System.out.println("Nhập thong tin danh muc thứ  ");
            Categories categories = new Categories();
            categories.inputData(scanner,categoriesList);
            categoriesList.add(categories);
        }
        System.out.println("Thêm thành cong");
    }

    @Override
    public void displayAll() {
        if (categoriesList.isEmpty()){
            System.err.println("Danh sách trống");
        }else {
            categoriesList.forEach(Categories::displayData);
        }
    }

    @Override
    public void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hãy chọn ID danh mục muốn cập nhật");
        int id = Integer.parseInt(scanner.nextLine());

        Categories categoryToUpdate = findById(id);
        if (categoryToUpdate == null) {
            System.err.println("ID danh mục không tìm thấy.");
            return;
        }
        System.out.println("Thông tin hiện tại:");
        categoryToUpdate.displayData();

        System.out.println("Nhập thông tin mới");
        categoryToUpdate.inputData(scanner, categoriesList);
        System.out.println("Cập nhật thành công!");
    }

    @Override
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Chọn id danh mục bạn muốn xóa");
        int id = Integer.parseInt(scanner.nextLine());

        // Tìm danh mục theo ID
        Categories categoryToDelete = null;
        for (Categories category : categoriesList) {
            if (category.getCatalogId() == id) {
                categoryToDelete = category;
                break;
            }
        }

        // Kiểm tra sản phẩm liên quan
        boolean hasProducts = false;
        if (categoryToDelete != null) {
            for (Product product : productList) {
                if (product.getCatalogId() == categoryToDelete.getCatalogId()) {
                    hasProducts = true;
                    break;
                }
            }
        }

        // Xử lý xóa danh mục
        if (hasProducts) {
            System.err.println("Không thể xóa danh mục này vì nó chứa sản phẩm.");
        } else if (categoryToDelete != null) {
            categoriesList.remove(categoryToDelete);
            System.out.println("Xóa danh mục thành công!");
        } else {
            System.err.println("ID danh mục không tìm thấy.");
        }
    }


    @Override
    public Categories findById(Integer id) {
      for (Categories c :categoriesList){
          if (c.getCatalogId()==id){
              return c;
          }
      }
      return null;
    }
}
