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
public class Anggota {

    @Id
    @Column(name = "user_id")
    private Long userId;
    private String nama;
    @Column(name = "jenis_kelamin")
    private String jenisKelamin;
    private String alamat;
    private String telp;

}
