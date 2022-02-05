package ru.dsci.stockdock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsci.stockdock.models.entities.Timeframe;

@Repository
public interface TimeframeRepository extends JpaRepository<Timeframe, Long> {

    Timeframe findByCodeIgnoreCase(String code);

}
