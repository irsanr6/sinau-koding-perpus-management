package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccessResponse {

    private UserAccess userAccess;
    private String token;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserAccess {

        private String fullName;
        private String username;
        private String email;
        private String role;

    }

}
