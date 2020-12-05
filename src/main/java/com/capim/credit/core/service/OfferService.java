package com.capim.credit.core.service;

import com.capim.credit.core.model.Offer;
import com.capim.credit.core.model.Request;
import com.capim.credit.core.model.Status;
import com.capim.credit.core.repository.OfferRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfferService extends GenericService<Offer>{

    private final OfferRepository offerRepository;

    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public List<Offer> findAllByRequestId(Long request_id) {
        return offerRepository.findAllByRequestId(request_id);
    }

    public Offer createNewOfferThenSave(Offer offer, Request request) {
        offer.setRequest(request);
        offer.setStatus(Status.PENDING);
        offer.calculateInstallmentValue();
        return offerRepository.save(offer);
    }
}
