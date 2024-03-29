package ra.entity;

import java.util.List;
import java.util.Scanner;

public class Categories {
    private static int nextId = 0;
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;

    public Categories() {
        this.catalogId = ++ nextId;
    }

    public Categories(int catalogId, String catalogName, String description, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.description = description;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
    public void inputData(Scanner scanner,List<Categories> categoriesList){
        this.catalogName = getInputCatalogName(scanner,categoriesList);
        System.out.println("nhập mô tả danh muc");
        this.description = scanner.nextLine();
        this.catalogStatus = inputCategoriesStatus(scanner);

    }

    public String getInputCatalogName(Scanner scanner, List<Categories> categoriesList) {
        System.out.println("Nhập tên của danh mục (tối đa 50 ký tự):");
        while (true) {
            String catalogNameInput = scanner.nextLine().trim();
            if (catalogNameInput.length() <= 50 &&
                    categoriesList.stream().noneMatch(c -> c.getCatalogName().equals(catalogNameInput))) {
                return catalogNameInput;
            } else {
                System.err.println("Tên danh mục đã tồn tại hoặc vượt quá 50 ký tự. Vui lòng nhập lại.");
            }
        }
    }

    public boolean inputCategoriesStatus(Scanner scanner){
        do {
            System.out.println("Nhập trạng thái danh muc chỉ true or false :");
            String statusCategoriesInput = scanner.nextLine();
            if (statusCategoriesInput.equals("true")||statusCategoriesInput.equals("false")){
                return Boolean.parseBoolean(statusCategoriesInput);
            }else {
                System.err.println("Trạng thái danh muc nhận true or false thôi , nhap lại");
            }
        }while (true);
    }



    public void displayData(){
        System.out.println("Categories{" +
                "catalogId=" + catalogId +
                ", catalogName='" + catalogName + '\'' +
                ", description='" + description + '\'' +
                ", catalogStatus=" + (catalogStatus?"Hoạt đông":"Không hoạt đông") +
                '}');
    }


}
