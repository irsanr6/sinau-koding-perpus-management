package com.irsan.sinaukoding.repository;

import com.irsan.sinaukoding.entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BukuRepository extends JpaRepository<Buku, Long>, JpaSpecificationExecutor<Buku> {
}
