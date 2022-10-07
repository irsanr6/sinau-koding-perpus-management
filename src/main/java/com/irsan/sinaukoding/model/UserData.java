package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserData {

    private Long userId;
    private String nama;
    private String username;
    private String email;
    private String alamat;
    private String telp;
    private String role;

}
