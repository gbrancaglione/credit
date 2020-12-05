package com.capim.credit.core.repository;
import com.capim.credit.core.model.Offer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OfferRepository extends GenericRepository<Offer>{

    @Query("SELECT DISTINCT o FROM Offer o WHERE o.request.cpf = ?1")
    List<Offer> findAllByRequestId(Long id);
}
