package ru.dsci.stockdock.services.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dsci.stockdock.core.tools.DateTimeTools;
import ru.dsci.stockdock.models.entities.Candlestick;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.InstrumentType;
import ru.dsci.stockdock.models.entities.Timeframe;
import ru.dsci.stockdock.services.DataService;
import ru.dsci.stockdock.services.InstrumentService;

import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DataServiceImpl implements DataService {

    private final ContextServiceImpl contextService;
    private final InstrumentService instrumentService;
    private final InstrumentTypeServiceImpl instrumentTypeService;
    private final CandlestickServiceImpl candlestickService;

    @Override
    public void updateInstruments() throws Exception {
        log.info("Updating instruments");
        List<Instrument> instruments = contextService.getInstruments();
        instrumentService.saveAllIfNotExists(instruments);
        log.info(String.format("Updating instruments: Records processed: %d", instruments.size()));
    }

    @Override
    public void updateInstruments(String instrumentTypeCode) throws Exception {
        InstrumentType instrumentType = instrumentTypeService.getByCode(instrumentTypeCode);
        if (instrumentType == null) throw new IllegalArgumentException(String.format(
                "Illegal instrument type: %s", instrumentTypeCode));
        updateInstruments(instrumentType);
    }

    @Override
    public void updateInstruments(InstrumentType instrumentType) throws Exception {
        log.info(String.format("Updating instruments of type: %s", instrumentType.getCode()));
        List<Instrument> instruments = contextService.getInstruments(instrumentType);
        instrumentService.saveAllIfNotExists(instruments);
        log.info(String.format("Updating instruments: Records processed: %d", instruments.size()));
    }

    @Override
    public void updateCandlesticks(@NonNull Instrument instrument, @NonNull Timeframe timeframe) {
        candlestickService.getCandlesticks(instrument, timeframe);
    }

    @Override
    public void updateCandlesticks(
            @NonNull Instrument instrument,
            @NonNull Timeframe timeframe,
            ZonedDateTime begPeriod,
            ZonedDateTime endPeriod) {

        log.info(String.format("Updating candlesticks : %s [%s] %s - %s",
                instrument.getTicker(),
                timeframe,
                DateTimeTools.getTimeFormatted(begPeriod),
                DateTimeTools.getTimeFormatted(endPeriod)));
        List<Candlestick> candlesticks = contextService.getCandlesticks(instrument, timeframe, begPeriod, endPeriod);
        candlesticks.forEach(candlestickService::saveAllIfNotExists);
        log.info(String.format("Updating candlesticks: Records processed: %d", candlesticks.size()));
    }

}
