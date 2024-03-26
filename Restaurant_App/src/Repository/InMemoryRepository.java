package Repository;
import Exceptions.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T> implements Repository<T> {
    private Map<Integer, T> data = new HashMap<>();
    private int nextId = 1;

    @Override
    public void add(T item) {
        data.put(nextId, item);
        nextId++;
    }

    @Override
    public void update(T item) {
        int itemId = getIdFromItem(item);

        if (itemId > 0) {
            if (data.containsKey(itemId)) {
                data.put(itemId, item);
            } else {
                throw new EntityNotFoundException("Entitatea cu ID-ul " + itemId + " nu a fost gasita");
            }
        } else {
            throw new IllegalArgumentException("Entitatea trebuie sa aiba un ID valid pentru a fi actualizata");
        }
    }

    @Override
    public void delete(T item) {
        int itemId = getIdFromItem(item);

        if (itemId > 0) {
            if (data.containsKey(itemId)) {
                data.remove(itemId);
            } else {
                throw new EntityNotFoundException("Entitatea cu ID-ul " + itemId + " nu a fost gasita si nu poate fi stearsa");
            }
        } else {
            throw new IllegalArgumentException("Entitatea trebuie sa aiba un ID valid pentru a fi stearsa");
        }
    }

    @Override
    public T getId(int id) {
        if (id > 0) {
            return data.get(id);
        } else {
            throw new IllegalArgumentException("ID-ul trebuie sa fie un numar valid pentru a obtine entitatea");
        }
    }

    public int getIdFromItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Entitatea nu poate fi nula");
        }

        try {
            Field idField = item.getClass().getDeclaredField("id"); // obtinem tipul de clasa al item, cautam campul id
            idField.setAccessible(true); // facem accesibil campul id
            return (int) idField.get(item); // returnam valoarea campului id
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException("Entitatea trebuie sa aiba un camp 'id' pentru a obtine ID-ul");
        }
    }


    @Override
    public List<T> getAll() {
        return new ArrayList<>(data.values());
    }
}