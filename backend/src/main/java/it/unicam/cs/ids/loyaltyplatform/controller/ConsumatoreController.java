package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Consumatore;
import it.unicam.cs.ids.loyaltyplatform.service.ConsumatoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumatore")
@AllArgsConstructor
public class ConsumatoreController extends EntityValidator{
    private final ConsumatoreService consumatoreService;

    @GetMapping(value = "/getConsumatoreById/{id}")
    public Consumatore getConsumatoreById(@PathVariable("id") Integer id) {
        return this.consumatoreService.getConsumatoreById(id);
    }

    @GetMapping(value = "/getAllConsumatori")
    public List<Consumatore> getAllConsumatori() {
        return this.consumatoreService.getAllConsumatori();
    }

    @PostMapping(value = "/addConsumatore")
    public Consumatore addConsumatore(@RequestBody Consumatore consumatore) {
        validateEntity(consumatore);
        return this.consumatoreService.addConsumatore(consumatore);
    }

    @DeleteMapping(value = "/deleteConsumatoreById/{id}")
    public void deleteConsumatoreById(@PathVariable("id") Integer id) {
        if(getConsumatoreById(id)==null)
            throw new IllegalArgumentException("Consumatore non esiste");
        this.consumatoreService.deleteConsumatoreById(id);
    }

    @PutMapping(value = "/updateConsumatore")
    public void updateConsumatore(@RequestBody Consumatore consumatore) {
        validateEntity(consumatore);
        if(getConsumatoreById(consumatore.getIdConsumatore())==null)
            throw new IllegalArgumentException("il record da aggiornare non esiste");
        this.consumatoreService.updateConsumatore(consumatore);
    }
}
