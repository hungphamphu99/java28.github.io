package service;

public interface Edit<T> {
    void add();
    void update();
    T findById(int id);
    void displayAll();
    void delete();

}
