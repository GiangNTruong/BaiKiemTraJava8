package ra.design;

import ra.entity.Product;

public interface IProductDesign extends IGenericDesign<Product,String> {
    void sortPriceProduct();
    void findProductName();
    void findPriceAtoB();
}
