package com.irsan.sinaukoding.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldError implements Serializable {

    private static final long serialVersionUID = -2187889051509704127L;
    private String field;
    private String message;

}
