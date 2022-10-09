package com.irsan.sinaukoding.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = -5204344839394378992L;
    private int status;
    private boolean success;
    private String message;
    private T data;
    private Pageable pageable;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Pageable {

        private boolean sort;
        private boolean first;
        private boolean last;
        private int totalPages;
        private long totalElements;
        private int size;
        private int number;

    }

    public static <T> BaseResponse<T> ok(T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .message("OK")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> ok(String message, T data) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> okPage(T data, Page<?> page) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .message("OK")
                .data(data)
                .pageable(Pageable.builder()
                        .sort(page.getSort().isSorted())
                        .first(page.isFirst())
                        .last(page.isLast())
                        .totalPages(page.getTotalPages())
                        .totalElements(page.getTotalElements())
                        .size(page.getSize())
                        .number(page.getNumber() + 1)
                        .build())
                .build();
    }

    public static <T> BaseResponse<T> okPage(String message, T data, Page<?> page) {
        return BaseResponse.<T>builder()
                .success(true)
                .status(200)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .pageable(Pageable.builder()
                        .sort(page.getSort().isSorted())
                        .first(page.isFirst())
                        .last(page.isLast())
                        .totalPages(page.getTotalPages())
                        .totalElements(page.getTotalElements())
                        .size(page.getSize())
                        .number(page.getNumber() + 1)
                        .build())
                .build();
    }

    public static <T> BaseResponse<T> error(String message) {
        return BaseResponse.<T>builder()
                .status(500)
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> error200(String message) {
        return BaseResponse.<T>builder()
                .status(200)
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(null)
                .build();
    }

    public static <T> BaseResponse<T> error(String message, T data) {
        return BaseResponse.<T>builder()
                .status(500)
                .success(false)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }

    public static <T> BaseResponse<T> create(int status, boolean success, String message, T data) {
        return BaseResponse.<T>builder()
                .status(status)
                .success(success)
                .message(StringUtils.isNotBlank(message) ? message : "")
                .data(data)
                .build();
    }

}
