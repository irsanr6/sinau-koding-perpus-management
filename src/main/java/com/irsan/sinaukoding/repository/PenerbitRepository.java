package com.irsan.sinaukoding.repository;

import com.irsan.sinaukoding.entity.Penerbit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PenerbitRepository extends JpaRepository<Penerbit, Long>, JpaSpecificationExecutor<Penerbit> {
}
