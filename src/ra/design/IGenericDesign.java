package ra.design;

public interface IGenericDesign <T,E> {
    void addNew();
    void displayAll();
    void update();
    void delete();
    T findById(E id);
}
