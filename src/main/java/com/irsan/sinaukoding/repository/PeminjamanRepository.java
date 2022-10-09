package com.irsan.sinaukoding.repository;

import com.irsan.sinaukoding.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {
    @Query(value = "select count(*)  from peminjaman where anggota_id = ?1 and status = ?2", nativeQuery = true)
    int countAnggotaId(Long anggotaId, String status);

    @Query(value = "select count(*)  from peminjaman where anggota_id = ?1 and buku_id = ?2 and status = ?3", nativeQuery = true)
    int countBukuId(Long anggotaId, Long bukuId, String status);

    @Transactional
    @Modifying
    @Query(value = "update peminjaman set status = ?1 where peminjaman_id = ?2", nativeQuery = true)
    void updateStatus(String status, Long peminjamanId);
}
