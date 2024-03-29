package ra.design;

import ra.entity.Categories;

public interface ICategories extends IGenericDesign<Categories , Integer> {
    Categories changerOfStatus (int id , boolean active );
}
