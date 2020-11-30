package com.capim.credit.web.controller;

import com.capim.credit.core.model.Request;
import com.capim.credit.core.repository.RequestRepository;
import com.capim.credit.core.service.RequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping({ "/", "/request" })
    public String index(Model model) {
        model.addAttribute("name", "John");

        Request request = new Request();
        model.addAttribute("request", request);

        return "request";
    }

    @PostMapping({"/request" })
    public void index(@ModelAttribute("request") @Valid Request request) {
        requestService.save(request);
    }
}