package com.capim.credit.web.controller;

import com.capim.credit.core.model.Offer;
import com.capim.credit.core.model.Status;
import com.capim.credit.core.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OfferController {
    
    private final OfferService offerService;
    
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
    
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping({"/offers/{cpf}"})
    public String listOfOffers(Model model, @PathVariable String cpf) {
        List<Offer> offers = offerService.findAllByRequestCpfOrderByCreatedAt(cpf);
        model.addAttribute("offers", offers);

        return "offers_dashboard";
    }

    @PostMapping("/offer/{id}/accept")
    public String acceptOffer(Model model, @PathVariable Long id) {
        Offer offer = offerService.findById(id).orElse(null);
        offer.setStatus(Status.ACCEPTED);
        offerService.save(offer);

        return "redirect:/offers/" + offer.getRequest().getCpf();
    }
    @PostMapping("/offer/{id}/refuse")
    public String refuseOffer(@PathVariable Long id) {
        Offer offer = offerService.findById(id).orElse(null);
        offer.setStatus(Status.REFUSED);
        offerService.save(offer);

        return "redirect:/offers/" + offer.getRequest().getCpf();
    }

    @PostMapping({"/offer"})
    public String createRequest(@ModelAttribute("offer") @Valid Offer offer,
                                BindingResult result) {

        if (result.hasErrors()) {
            logger.info(" An offer attempt has failed (errors): {}", result);
            return "request_list"; //Potentiel HTTP 403 ?
        }

        Offer createdOffer = offerService.save(offer);
        logger.info(" A new offer was saved (id): {} associated to request (id): {}",
                createdOffer.getId(), createdOffer.getRequest().getId());
        return "request_list"; //Potential HTTP 200 status code
    }
}
