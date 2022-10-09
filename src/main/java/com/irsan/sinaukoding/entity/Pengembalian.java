package com.irsan.sinaukoding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.irsan.sinaukoding.util.Constant;
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
    @JsonFormat(pattern = Constant.DATETIME_PATTERN, timezone = Constant.TIMEZONE_INDONESIA)
    @Column(name = "tgl_pengembalian")
    private Date tglPengembalian;
    @JsonFormat(pattern = Constant.DATETIME_PATTERN, timezone = Constant.TIMEZONE_INDONESIA)
    @Column(name = "updated_at")
    private Date updatedAt;
    private Double denda;
    @Column(name = "peminjaman_id")
    private Long peminjamanId;
    @Column(name = "anggota_id")
    private Long anggotaId;
    @Column(name = "petugas_id")
    private Long petugasId;
    @Transient
    private String status;

}
