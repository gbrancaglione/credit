package com.capim.credit.core.service;

import com.capim.credit.core.model.Request;
import com.capim.credit.core.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    private static final Logger logger = LoggerFactory.getLogger(RequestService.class);

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public void save(Request request) {
        requestRepository.save(request);
        logger.info(" A new Request was saved (id): {}", request.getId());
    }
}
