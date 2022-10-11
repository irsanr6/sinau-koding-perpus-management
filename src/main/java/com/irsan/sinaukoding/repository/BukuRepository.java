package com.irsan.sinaukoding.repository;

import com.irsan.sinaukoding.entity.Buku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BukuRepository extends JpaRepository<Buku, Long>, JpaSpecificationExecutor<Buku> {
    @Query(value = "select jumlah from buku where buku_id = ?1", nativeQuery = true)
    Long getJumlahBuku(Long bukuId);
}
