package Controller_SpringBoot;

import DAO.Repo_DAO;
import Exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class BaseController<T, IdType> {
    Repo_DAO<T> repository;

    public BaseController(Repo_DAO<T> repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<T> add(@RequestBody T object) {
        try {
            T addedObject = repository.add(object);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedObject);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable(value = "id") IdType id, @RequestBody T object) {
        try {
            T updatedObject = repository.update(id, object);
            return ResponseEntity.status(HttpStatus.OK).body(updatedObject);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<T> delete(@PathVariable(value = "id") IdType id) {
        try {
            T deletedObject = repository.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(deletedObject);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        List<T> objects = repository.getAll();
        return ResponseEntity.ok().body(objects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getEntityById(@PathVariable(value = "id") IdType id) {
        try {
            T object = repository.getById(id);
            return ResponseEntity.ok().body(object);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
