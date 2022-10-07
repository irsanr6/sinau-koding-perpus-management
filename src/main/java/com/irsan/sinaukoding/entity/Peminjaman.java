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
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "peminjaman_id")
    private Long peminjamanId;
    @Column(name = "buku_id")
    private Long bukuId;
    @Column(name = "tgl_pinjam")
    private Date tglPinjam;
    @Column(name = "tgl_kembali")
    private Date tglKembali;
    @Column(name = "petugas_id")
    private Long petugasId;


}
