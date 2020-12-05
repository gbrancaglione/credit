package com.capim.credit.core.repository;
import com.capim.credit.core.model.Offer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends GenericRepository<Offer>{

    @Query("SELECT DISTINCT o FROM Offer o Where o.request.cpf = :cpf ORDER BY o.createdAt DESC")
    List<Offer> findAllByRequestCpfOrderByCreatedAt(@Param("cpf") String cpf);

}
