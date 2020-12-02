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

    @PostMapping({"/request" })
    public String createRequest(@ModelAttribute("request") @Valid Request request,
                                BindingResult result) {

        if (result.hasErrors()) {
            logger.info(" A request attempt has failed (errors): {}", result);
            return "request";
        }

        requestService.save(request);
        return "request_validation";
    }
}