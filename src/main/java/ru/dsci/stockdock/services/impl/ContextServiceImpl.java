package ru.dsci.stockdock.services.impl;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.dsci.stockdock.models.entities.Candlestick;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.InstrumentType;
import ru.dsci.stockdock.models.entities.Timeframe;
import ru.dsci.stockdock.services.ContextService;
import ru.tinkoff.invest.openapi.model.rest.MarketInstrument;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ContextServiceImpl implements ContextService {

    private final TcsContextServiceImpl tcsContextService;
    private final InstrumentTypeServiceImpl instrumentTypeService;
    private final ModelMapper modelMapper;

    @Override
    public List<Instrument> getInstruments() throws Exception {
        log.debug("Downloading all instruments");
        List<InstrumentType> instrumentTypes;
        List<Instrument> instruments = new ArrayList<>();
        instrumentTypes = instrumentTypeService.getAll();
        for (int i = 0; i < instrumentTypes.size(); i++) {
            instruments.addAll(getInstruments(instrumentTypes.get(i)));
        }
        return instruments;
    }

    @Override
    public List<Instrument> getInstruments(@NonNull InstrumentType instrumentType) throws Exception {
        log.debug(String.format("Downloading instruments of type: %s", instrumentType.getCode()));
        List<MarketInstrument> marketInstruments;
        log.info(String.format("Requesting instruments of type: %s", instrumentType.getCode()));
        switch (instrumentType.getCode()) {
            case ("bond"):
                marketInstruments = tcsContextService.getBonds();
                break;
            case ("etf"):
                marketInstruments = tcsContextService.getEtfs();
                break;
            case ("stock"):
                marketInstruments = tcsContextService.getStocks();
                break;
            case ("currency"):
                marketInstruments = tcsContextService.getCurrencies();
                break;
            default:
                throw new IllegalArgumentException(String.format(
                        "Illegal instrument type: %s", instrumentType.getCode()));
        }
        List<Instrument> instruments = marketInstruments
                .stream()
                .map(marketInstrument -> modelMapper.map(marketInstrument, Instrument.class))
                .collect(Collectors.toList());
        log.info(String.format("Records received: %d", instruments.size()));
        return instruments;
    }

    @Override
    public List<Candlestick> getCandlesticks(Instrument instrument, Timeframe timeframe, ZonedDateTime begPeriod, ZonedDateTime endPeriod) {
        return tcsContextService.getCandles(instrument, timeframe, begPeriod, endPeriod)
                .stream()
                .map(candle -> modelMapper.map(candle, Candlestick.class))
                .collect(Collectors.toList());
    }
}
