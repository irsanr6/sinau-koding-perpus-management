package com.irsan.sinaukoding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pengarang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pengarang_id")
    private Long pengarangId;
    @Column(name = "nama_pengarang")
    private String namaPengarang;
    private String alamat;
    private String telp;

}
