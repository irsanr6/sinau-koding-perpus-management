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
public class Penerbit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penerbit_id")
    private Long penerbitId;
    @Column(name = "nama_penerbit")
    private String namaPenerbit;
    private String alamat;
    private String telp;

}
