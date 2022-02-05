package ru.dsci.stockdock.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dsci.stockdock.exceptions.EntityNotFoundException;
import ru.dsci.stockdock.models.entities.InstrumentType;
import ru.dsci.stockdock.repositories.InstrumentTypeRepository;
import ru.dsci.stockdock.services.InstrumentTypeService;

import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentTypeServiceImpl implements InstrumentTypeService {

    private InstrumentTypeRepository instrumentTypeRepository;

    @Override
    public List<InstrumentType> getAll() {
        return instrumentTypeRepository.findAll();
    }

    @Override
    public InstrumentType getById(Long id) {
        return instrumentTypeRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException(InstrumentType.class, id));
    }

    @Override
    public InstrumentType getByCode(String code) {
        return instrumentTypeRepository.findByCodeIgnoreCase(code);
    }
}
