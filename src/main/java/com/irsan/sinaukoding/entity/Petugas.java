package com.irsan.sinaukoding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Petugas {

    @Id
    @Column(name = "petugas_id")
    private Long petugasId;
    private String nama;
    private String alamat;
    private String telp;

}
