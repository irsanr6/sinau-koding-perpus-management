package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BukuSimpanRequest {

    private Long bukuId;
    private String judulBuku;
    private String tahunTerbit;
    private String jumlah;
    private String isbn;
    private String pengarangId;
    private String penerbitId;

}
