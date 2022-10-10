package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenerbitLihatRequest {

    private String penerbitId;
    private String namaPenerbit;
    private String alamat;
    private int limit;
    private int pageIn;

}
