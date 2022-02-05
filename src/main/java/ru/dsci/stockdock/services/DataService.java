package ru.dsci.stockdock.services;

import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.models.entities.InstrumentType;
import ru.dsci.stockdock.models.entities.Timeframe;

import java.time.ZonedDateTime;

public interface DataService {

    /**
     * обновляет информацию по инструментам
     */
    void updateInstruments() throws Exception;

    /**
     * обновляет информацию по инструменту
     * @param instrumentTypeCode - код типа инструмента
     */
    void updateInstruments(String instrumentTypeCode) throws Exception;

    /**
     * обновляет информацию по инструменту
     * @param instrumentType - тип инструмента
     */
    void updateInstruments(InstrumentType instrumentType) throws Exception;

    /**
     * обновляет исторические данные по инструменту
     * период - с начала GlobalContext.BEG_DATE - по настоещее время
     * @param instrument - инструмент
     * @param timeframe - таймфрейм
     */
    void updateCandlesticks(Instrument instrument, Timeframe timeframe);

    /**
     * обновляет исторические данные по инструменту
     * период - с начала GlobalContext.BEG_DATE - по настоещее время
     * @param instrument - инструмент
     * @param timeframe - таймфрейм
     * @param begInterval - начало периода
     * @param endInterval - окончание периода
     */
    void updateCandlesticks(
            Instrument instrument, Timeframe timeframe, ZonedDateTime begInterval, ZonedDateTime endInterval);

}
