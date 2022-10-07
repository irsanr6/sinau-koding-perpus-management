package com.irsan.sinaukoding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pengembalian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pengembalian_id")
    private Long pengembalianId;
    @Column(name = "buku_id")
    private Long bukuId;
    @Column(name = "tgl_pengembalian")
    private Date tglPengembalian;
    private Double denda;
    @Column(name = "peminjaman_id")
    private Long peminjamanId;
    @Column(name = "anggota_id")
    private Long anggotaId;
    @Column(name = "petugas_id")
    private Long petugasId;

}
