package com.capim.credit.core.service;

import com.capim.credit.core.model.Request;
import com.capim.credit.core.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void save(Request request) {
        requestRepository.save(request);
    }
}
