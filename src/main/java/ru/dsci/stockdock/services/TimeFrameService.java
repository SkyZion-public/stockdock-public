package ru.dsci.stockdock.services;

import ru.dsci.stockdock.models.entities.Timeframe;

import java.util.List;

public interface TimeFrameService {

    List<Timeframe> getAll();

    Timeframe getById(Long id);

    Timeframe getByCode(String code);

}
