package ru.dsci.stockdock.models.entities;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import ru.dsci.stockdock.core.tools.DateTimeTools;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity
@Data
public class Candlestick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(value = AccessLevel.PRIVATE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "timeframe_id")
    private Timeframe timeframe;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "instrument_id")
    private Instrument instrument;

    private BigDecimal maximumValue;

    private BigDecimal minimumValue;

    private BigDecimal openedValue;

    private BigDecimal closedValue;

    private int volume;

    private ZonedDateTime since;

    @Override
    public String toString() {
        return String.format("%s [%s] %s: %.4f",
                this.instrument.getTicker(),
                this.timeframe.getCode(),
                DateTimeTools.getTimeFormatted(this.since),
                this.closedValue);
    }

}
