package com.capim.credit.web.controller;

import com.capim.credit.core.model.Request;
import com.capim.credit.core.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RequestController {

    private final RequestService requestService;

    private static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping({ "/", "/request" })
    public String index(Model model) {
        Request request = new Request();
        model.addAttribute("request", request);

        return "request";
    }

    @GetMapping({ "/request_list" })
    public String listRequests(Model model) {
        List<Request> requests = requestService.findAllSortedByCreationDate();
        model.addAttribute("requests", requests);

        return "request_list";
    }

    @PostMapping({"/request" })
    public String createRequest(@ModelAttribute("request") @Valid Request request,
                                BindingResult result) {

        if (result.hasErrors()) {
            logger.info(" A request attempt has failed (errors): {}", result);
            return "request";
        }

        Request createdRequest = requestService.save(request);
        logger.info(" A new request was saved (id): {}", createdRequest.getId());
        return "request_validation";
    }
}