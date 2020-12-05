package com.capim.credit.web.controller;

import com.capim.credit.core.model.Offer;
import com.capim.credit.core.model.Request;
import com.capim.credit.core.model.Status;
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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import java.util.List;

@Controller
public class OfferController {
    
    private final OfferService offerService;
    private final RequestService requestService;
    
    private static final Logger logger = LoggerFactory.getLogger(OfferController.class);
    
    public OfferController(OfferService offerService, RequestService requestService) {
        this.requestService = requestService;
        this.offerService = offerService;
    }

    @GetMapping({"/offers/{cpf}"})
    public String listOfOffers(Model model, @PathVariable String cpf) {
        List<Offer> offers = offerService.findAllByRequestCpfOrderByCreatedAt(cpf);
        model.addAttribute("offers", offers);

        return "offers_dashboard";
    }

    @PostMapping("/offer/{id}/accept")
    public String acceptOffer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Offer offer = offerService.findById(id).orElse(null);
        if (offer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No offer for this CPF");

        offer.setStatus(Status.ACCEPTED);
        offerService.save(offer);

        redirectAttributes.addAttribute("cpf", offer.getRequest().getCpf());
        return "redirect:/offers/{cpf}";
    }

    @PostMapping("/offer/{id}/refuse")
    public String refuseOffer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Offer offer = offerService.findById(id).orElse(null);
        if (offer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No offer for this CPF");

        offer.setStatus(Status.REFUSED);
        offerService.save(offer);

        redirectAttributes.addAttribute("cpf", offer.getRequest().getCpf());
        return "redirect:/offers/{cpf}";
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
