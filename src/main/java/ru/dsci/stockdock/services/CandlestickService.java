package ru.dsci.stockdock.services;

import ru.dsci.stockdock.models.entities.Candlestick;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.Timeframe;

import java.time.ZonedDateTime;
import java.util.List;

public interface CandlestickService {

    Candlestick getById(Long id);

    List <Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe);

    List <Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod);

    List <Candlestick> getCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

    void saveAllIfNotExists(Candlestick candlestick);

}
