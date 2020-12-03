package com.capim.credit.core.repository;
import com.capim.credit.core.model.Offer;
import java.util.List;

public interface OfferRepository extends GenericRepository<Offer>{

    List<Offer> findAllByRequestId(Long id);
}
