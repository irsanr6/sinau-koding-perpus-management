package com.irsan.sinaukoding.repository;

import com.irsan.sinaukoding.entity.Pengarang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PengarangRepository extends JpaRepository<Pengarang, Long>, JpaSpecificationExecutor<Pengarang> {
}
