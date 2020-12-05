package com.capim.credit.core.repository;

import com.capim.credit.core.model.GenericModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T extends GenericModel> extends JpaRepository<T, Long> {
}