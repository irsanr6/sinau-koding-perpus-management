package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PengarangLihatRequest {

    private String pengarangId;
    private String namaPengarang;
    private String alamat;
    private int limit;
    private int pageIn;

}
