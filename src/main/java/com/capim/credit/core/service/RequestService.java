package com.capim.credit.core.service;

import com.capim.credit.core.model.Request;
import com.capim.credit.core.repository.RequestRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RequestService extends GenericService<Request>{

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> findAllSortedByCreationDate() {
        return requestRepository.findAllOrderByCreatedAt();
    }
}
