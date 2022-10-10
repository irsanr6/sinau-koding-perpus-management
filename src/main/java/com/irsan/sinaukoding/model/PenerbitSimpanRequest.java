package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenerbitSimpanRequest {

    private Long penerbitId;
    private String namaPenerbit;
    private String alamat;
    private String telp;

}
