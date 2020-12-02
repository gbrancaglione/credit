package com.capim.credit.core.repository;

import com.capim.credit.core.model.Request;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends GenericRepository<Request> {

    @Query("SELECT DISTINCT r FROM Request r ORDER BY r.createdAt DESC")
    List<Request> findAllOrderByCreatedAt();
}
