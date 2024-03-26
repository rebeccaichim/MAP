package Controller_SpringBoot;

import Controller.Controller;
import DAO.Repo_DAO;
import Domain.Bestellung_im_Restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bestellungen")
public class Bestellung_im_RestaurantRestController {

    private final Controller<Bestellung_im_Restaurant> bestellungController;

    @Autowired
    public Bestellung_im_RestaurantRestController(Repo_DAO<Bestellung_im_Restaurant> repository) {
        this.bestellungController = new Controller<>(repository);
    }

    @PostMapping
    public Bestellung_im_Restaurant createBestellung(@RequestBody Bestellung_im_Restaurant bestellung) {
        return bestellungController.create(bestellung);
    }

    @GetMapping("/{id}")
    public Bestellung_im_Restaurant getBestellung(@PathVariable int id) {
        return bestellungController.read(id);
    }

    @GetMapping
    public List<Bestellung_im_Restaurant> getAllBestellungen() {
        return bestellungController.getAll();
    }

    @PutMapping("/{id}")
    public Bestellung_im_Restaurant updateBestellung(@PathVariable int id, @RequestBody Bestellung_im_Restaurant bestellung) {
        return bestellungController.update(id, bestellung);
    }

    @DeleteMapping("/{id}")
    public Bestellung_im_Restaurant deleteBestellung(@PathVariable int id) {
        return bestellungController.delete(id);
    }
}
