package com.irsan.sinaukoding.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.irsan.sinaukoding.util.Constant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPerpus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    private String password;
    @Column(name = "password_encode")
    private String passwordEncode;
    private String email;
    private String role;
    @JsonFormat(pattern = Constant.DATETIME_PATTERN, timezone = Constant.TIMEZONE_INDONESIA)
    @Column(name = "created_at")
    private Date createdAt;
    @JsonFormat(pattern = Constant.DATETIME_PATTERN, timezone = Constant.TIMEZONE_INDONESIA)
    @Column(name = "updated_at")
    private Date updatedAt;

}
