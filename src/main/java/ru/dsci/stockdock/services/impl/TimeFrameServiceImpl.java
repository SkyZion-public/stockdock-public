package ru.dsci.stockdock.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsci.stockdock.exceptions.EntityNotFoundException;
import ru.dsci.stockdock.models.entities.Timeframe;
import ru.dsci.stockdock.repositories.TimeframeRepository;
import ru.dsci.stockdock.services.TimeFrameService;

import java.util.List;

@Service
@AllArgsConstructor
public class TimeFrameServiceImpl implements TimeFrameService {

    private TimeframeRepository periodRepository;

    @Override
    public List<Timeframe> getAll() {
        return periodRepository.findAll();
    }

    @Override
    public Timeframe getById(Long id) {
        return periodRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Timeframe.class, id));
    }

    @Override
    public Timeframe getByCode(String code) {
        return periodRepository.findByCodeIgnoreCase(code);
    }
}
