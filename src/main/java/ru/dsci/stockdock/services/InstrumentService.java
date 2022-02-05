package ru.dsci.stockdock.services;

import ru.dsci.stockdock.models.entities.Instrument;

import java.util.List;

public interface InstrumentService {

    List<Instrument> getAll();

    Instrument getById(Long id);

    Instrument getByFigi(String figi);

    Instrument getByTicker(String ticker);

    Instrument getByFigiOrTicker(String identifier);

    void saveAllIfNotExists(List<Instrument> instruments);

    void saveIfNotExists(Instrument instrument);

}