package Repository;
import java.util.List;

public interface Repository<T> {
    void add(T item);
    void update(T item);
    void delete(T item);
    T getId(int id);
    int getIdFromItem(T item);
    List<T> getAll();
}