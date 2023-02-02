package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.service.PagamentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamento")
@AllArgsConstructor
public class PagamentoController extends EntityValidator{
    private final PagamentoService pagamentoService;
    @GetMapping("/getPagamentoById/{id}")
    public Pagamento getPagamentoById(@PathVariable("id") Integer id) {return this.pagamentoService.getPagamentoById(id);}
    @GetMapping("/getAllPagamenti")
    public List<Pagamento> getAllPagamenti() {return this.pagamentoService.getAllPagamenti();}
    @PostMapping("/addPagamento")
    public Pagamento addPagamento(@RequestBody Pagamento pagamento) {
        validateEntity(pagamento);
        if(pagamento.getIdPagamento() != null && getPagamentoById(pagamento.getIdPagamento()) != null)
            throw new IllegalArgumentException("il record da aggiungere esiste già");

        return this.pagamentoService.addPagamento(pagamento);
    }
    @DeleteMapping("/deletePagamentoById/{id}")
    public void deletePagamentoById(@PathVariable("id") Integer id) {
        if(getPagamentoById(id) == null)
            throw new IllegalArgumentException("il record da rimuovere non esiste");

        this.pagamentoService.deletePagamentoById(id);
    }
    @PutMapping("/updatePagamento")
    public void updatePagamento(@RequestBody Pagamento pagamento) {
        validateEntity(pagamento);
        if(pagamento.getIdPagamento() == null)
            throw new NullPointerException("id pagamento non deve essere nullo per l'update");
        if(getPagamentoById(pagamento.getIdPagamento()) == null)
            throw new IllegalArgumentException("il record da aggiornare non esiste");

        this.pagamentoService.updatePagamento(pagamento);
    }
}
