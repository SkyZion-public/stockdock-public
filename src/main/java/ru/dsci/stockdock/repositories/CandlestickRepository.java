package ru.dsci.stockdock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsci.stockdock.models.entities.Candlestick;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.Timeframe;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface CandlestickRepository extends JpaRepository<Candlestick, Long> {

    List<Candlestick> getCandlestickByInstrumentAndTimeframeAndSinceBetweenOrderBySince(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

    boolean existsByInstrumentAndTimeframeAndSince(Instrument instrument, Timeframe timeframe, ZonedDateTime since);

}
