package com.capim.credit.core.repository;

import com.capim.credit.core.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}
