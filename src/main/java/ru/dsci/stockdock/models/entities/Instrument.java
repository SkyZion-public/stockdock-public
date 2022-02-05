package ru.dsci.stockdock.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Instrument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    private String figi;

    private String isin;

    private String ticker;

    private String currency;

    private String name;

    private BigDecimal increment;

    private int lot;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "instrument_type_id")
    private InstrumentType instrumentType;

    @Override
    public String toString() {
        return String.format("%s [%s] (%s)", this.ticker, this.figi, this.instrumentType.getCode());
    }

}
