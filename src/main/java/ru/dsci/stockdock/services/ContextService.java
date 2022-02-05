package ru.dsci.stockdock.services;

import org.springframework.stereotype.Service;
import ru.dsci.stockdock.models.entities.Candlestick;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.InstrumentType;
import ru.dsci.stockdock.models.entities.Timeframe;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public interface ContextService {

    List<Instrument> getInstruments() throws Exception;

    List<Instrument> getInstruments(InstrumentType instrumentType) throws Exception;

    List<Candlestick> getCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod);

}
