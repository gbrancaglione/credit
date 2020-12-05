package com.capim.credit.web.controller;

import com.capim.credit.core.model.Offer;
import com.capim.credit.core.model.Request;
import com.capim.credit.core.service.OfferService;
import com.capim.credit.core.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class OfferController {
    
    private final OfferService offerService;
    private final RequestService requestService;
    
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
    
    public OfferController(OfferService offerService, RequestService requestService) {
        this.requestService = requestService;
        this.offerService = offerService;
    }

    @GetMapping({ "/{request_id}/offers" })
    public String lisOffers(Model model, @PathVariable Long request_id) {
        List<Offer> offers = offerService.findAllByRequestId(request_id);
        model.addAttribute("offers", offers);

        return "offers_list";
    }

    @PostMapping({"/offer/{request_id}" })
    public String createOffer(@ModelAttribute("offer") @Valid Offer offer,
                                BindingResult result, @PathVariable Long request_id) {

        if (result.hasErrors()) {
            logger.warn(" An offer attempt has failed (errors): {}", result);
            return "redirect:/request_list"; //Potentiel HTTP 403 ?
        }

        Request request = requestService.findById(request_id).orElse(null);
        if (request==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find request");

        Offer createdOffer = offerService.createNewOfferThenSave(offer, request);
        logger.info(" A new offer was saved (id): {} associated to request (id): {}",
                createdOffer.getId(), createdOffer.getRequest().getId());
        return "redirect:/request_list"; //Potential HTTP 200 status code
    }
}
