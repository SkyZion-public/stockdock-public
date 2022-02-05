package ru.dsci.stockdock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dsci.stockdock.models.entities.InstrumentType;

@Repository
public interface InstrumentTypeRepository extends JpaRepository<InstrumentType, Long> {

    InstrumentType findByCodeIgnoreCase(String code);

}
