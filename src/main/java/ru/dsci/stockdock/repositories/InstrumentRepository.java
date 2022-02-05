package ru.dsci.stockdock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsci.stockdock.models.entities.Instrument;

@Repository
public interface InstrumentRepository extends JpaRepository<Instrument, Long> {

    Instrument findByFigiIgnoreCase(String figi);

    Instrument findByTickerIgnoreCase(String ticker);

    Instrument findByFigiIgnoreCaseOrTickerIgnoreCase(String figi, String ticker);

    boolean existsInstrumentByFigiIgnoreCase(String figi);

}
