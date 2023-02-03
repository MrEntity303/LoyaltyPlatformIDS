package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.entity.Adesione;
import it.unicam.cs.ids.loyaltyplatform.entity.Consumatore;
import it.unicam.cs.ids.loyaltyplatform.entity.Pagamento;
import it.unicam.cs.ids.loyaltyplatform.entity.Premio;
import it.unicam.cs.ids.loyaltyplatform.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/adesione")
@AllArgsConstructor
public class AdesioneController extends EntityValidator
{
    private final AdesioneService adesioneService;
    private final PagamentoService pagamentoService;
    private final PremioService premioService;
    private final ConsumatoreService consumatoreService;

    @GetMapping(value = "/getAdesioniByIdConsumatore/{id}")
    public List<Adesione> getAdesioniByIdConsumatore(@PathVariable("id") Integer id) {
        return this.adesioneService.getAdesioniByIdConsumatore(id);
    }
    @GetMapping(value = "/getAdesioniByIdAzienda/{id}")
    public List<Adesione> getAdesioniByIdAzienda(@PathVariable("id") Integer id) {
        return this.adesioneService.getAdesioniByIdAzienda(id);
    }
    @GetMapping(value = "/getAdesioneByConsumatoreAndAzienda/{idAzienda}&{idConsumatore}")
    public Optional<Adesione> getAdesioneByConsumatoreAndAzienda(@PathVariable("idConsumatore") Integer idConsumatore,
                                                                 @PathVariable("idAzienda") Integer idAzienda) {
        return this.adesioneService.getAdesioneByConsumatoreAndAzienda(idConsumatore, idAzienda);
    }

    @GetMapping(value = "/getAllAdesioni")
    public List<Adesione> getAllAdesioni() {
        return this.adesioneService.getAllAdesioni();
    }

    @PostMapping(value = "/addAdesione")
    public Adesione addAdesione(@RequestBody Adesione adesione) {
        validateEntity(adesione);
        if (getAdesioneByConsumatoreAndAzienda(adesione.getQualeConsumatore(),adesione.getQualeAzienda()).isPresent())
            throw new IllegalArgumentException("ID del record da aggiungere già presente");
        return this.adesioneService.addAdesione(adesione);
    }

    @DeleteMapping(value = "/deleteAdesione")
    public void deleteAdesione(@RequestBody Adesione adesione) {
        validateEntity(adesione);
        if(getAdesioneByConsumatoreAndAzienda(adesione.getQualeConsumatore(),adesione.getQualeAzienda()).isEmpty())
            throw new IllegalArgumentException("ID del record da eliminare non presente");
        this.adesioneService.deleteAdesione(adesione);
    }

    @PutMapping(value = "/updateAdesione")
    public void updateAdesione(@RequestBody Adesione adesione) {
        validateEntity(adesione);
        if (getAdesioneByConsumatoreAndAzienda(adesione.getQualeConsumatore(),adesione.getQualeAzienda()).isEmpty())
            throw new IllegalArgumentException("ID del record da modificare non presente");
        this.adesioneService.updateAdesione(adesione);
    }

    @PutMapping(value = "/incrementoPunti")
    public Adesione incrementoPunti(@RequestBody Pagamento pagamento) {
        validateEntity(pagamento);
        if(this.pagamentoService.getPagamentoById(pagamento.getIdPagamento()) == null)
            throw new IllegalArgumentException("ID del record da modificare non presente");
        return this.adesioneService.incrementoPunti(pagamento);
    }

    @PutMapping(value = "/sottrazionePuntiPremio/{qualeAzienda}&{qualeProdotto}")
    public List<Adesione> sottrazionePuntiPremio(@RequestBody Consumatore consumatore,    //TODO discutere se mettere come PathVariable il premio o il consumatore
                                           @PathVariable("qualeAzienda") Integer qualeAzienda,
                                           @PathVariable("qualeProdotto") Integer qualeProdotto) {
        validateEntity(consumatore);
        if(this.consumatoreService.getConsumatoreById(consumatore.getIdConsumatore()) == null)
            throw new IllegalArgumentException("ID del record da modificare non presente");
        Optional<Premio> premio = this.premioService.getPremioByAziendaAndProdotto(qualeAzienda, qualeProdotto);
        if(premio.isEmpty())    //validazione degli ID
            throw new IllegalArgumentException("premio non esistente");
        return this.adesioneService.sottrazionePuntiPremio(consumatore, premio.get());
    }
}
