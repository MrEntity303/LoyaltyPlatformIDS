package it.unicam.cs.ids.loyaltyplatform.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "COALIZIONI")
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Coalizione
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "COALIZIONI_SEQ")
    @SequenceGenerator(name = "COALIZIONI_SEQ", sequenceName = "COALIZIONI_SEQ", allocationSize = 1)
    private Integer idCoalizione;
    @Column(nullable = false)
    private Float parametroBuoniSpesa;  //buoni spesa ogni tot euro (maggiore di 0)
    @Column(nullable = false)
    @NonNull
    private Boolean condivisionePunti;
    @NonNull
    private Float penalitaCondivisione; //maggiore o uguale a 1
    @NonNull
    private Integer percentualeRitiroPremi; //Si possono usare i punti ottenuti da altre aziende, ma per
                                            // ritirare il premio da una di queste devi avere guadagnato da essa almeno la percentuale indicata.
}
