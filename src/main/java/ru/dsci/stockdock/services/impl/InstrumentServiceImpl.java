package ru.dsci.stockdock.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsci.stockdock.exceptions.EntityNotFoundException;
import ru.dsci.stockdock.models.entities.Instrument;
import ru.dsci.stockdock.repositories.InstrumentRepository;
import ru.dsci.stockdock.services.InstrumentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentServiceImpl implements InstrumentService {

    private InstrumentRepository instrumentRepository;

    @Override
    public List<Instrument> getAll() {
        return instrumentRepository.findAll();
    }

    @Override
    public Instrument getById(Long id) {
        return instrumentRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Instrument.class, id));
    }

    @Override
    public Instrument getByTicker(String ticker) {
        return instrumentRepository.findByTickerIgnoreCase(ticker);
    }

    @Override
    public Instrument getByFigiOrTicker(String identifier) {
        return instrumentRepository.findByFigiIgnoreCaseOrTickerIgnoreCase(identifier, identifier);
    }

    @Override
    public Instrument getByFigi(String isin) {
        return instrumentRepository.findByFigiIgnoreCase(isin);
    }

    @Override
    public void saveAllIfNotExists(List<Instrument> instruments) {
        instruments.forEach(this::saveIfNotExists);
    }

    @Override
    @Transactional
    public void saveIfNotExists(Instrument instrument) {
        if (!instrumentRepository.existsInstrumentByFigiIgnoreCase(instrument.getFigi()))
            instrumentRepository.saveAndFlush(instrument);
    }

}
