package ru.dsci.stockdock.services;

import ru.dsci.stockdock.models.entities.InstrumentType;
import java.util.List;

public interface InstrumentTypeService {

    List<InstrumentType> getAll();

    InstrumentType getById(Long id);

    InstrumentType getByCode(String code);

}
