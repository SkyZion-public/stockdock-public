package ru.dsci.stockdock.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsci.stockdock.core.GlobalContext;
import ru.dsci.stockdock.core.tools.DateTimeTools;
import ru.dsci.stockdock.models.entities.Candlestick;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.Timeframe;
import ru.dsci.stockdock.repositories.CandlestickRepository;
import ru.dsci.stockdock.services.CandlestickService;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CandlestickServiceImpl implements CandlestickService {

    private CandlestickRepository candlestickRepository;

    @Override
    public Candlestick getById(Long id) {
        return candlestickRepository.getById(id);
    }

    @Override
    public List<Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe) {
        return getCandlesticks(instrument, timeframe, null, null);
    }

    @Override
    public List<Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod) {
        return getCandlesticks(instrument, timeframe, begPeriod);
    }

    @Override
    public List<Candlestick> getCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod) {
        if (begPeriod == null)
            begPeriod = GlobalContext.BEG_DATE;
        if (endPeriod == null)
            endPeriod = ZonedDateTime.now();
        DateTimeTools.checkInterval(begPeriod, endPeriod);
        return candlestickRepository.getCandlestickByInstrumentAndTimeframeAndSinceBetweenOrderBySince(
                instrument, timeframe, begPeriod, endPeriod);
    }

    @Override
    @Transactional
    public void saveAllIfNotExists(Candlestick candlestick) {
        if (!candlestickRepository.existsByInstrumentAndTimeframeAndSince(
                candlestick.getInstrument(), candlestick.getTimeframe(), candlestick.getSince()))
            candlestickRepository.saveAndFlush(candlestick);
    }
}
