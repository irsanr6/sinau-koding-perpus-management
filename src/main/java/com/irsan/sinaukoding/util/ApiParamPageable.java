package com.irsan.sinaukoding.util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(
                name = "Authorization",
                dataType = "string",
                dataTypeClass = String.class,
                paramType = "header",
                defaultValue = "Bearer ",
                value = "Bearer Token"),
        @ApiImplicitParam(
                name = "page",
                dataType = "int",
                dataTypeClass = String.class,
                paramType = "query",
                defaultValue = "0",
                value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(
                name = "size",
                dataType = "int",
                dataTypeClass = String.class,
                paramType = "query",
                defaultValue = "20",
                value = "Number of records per page."),
        @ApiImplicitParam(
                name = "sort",
                allowMultiple = true,
                dataType = "string",
                dataTypeClass = String.class,
                paramType = "query",
                value = "Sorting criteria in the format: property(,asc|desc). "
                        + "Default sort order is ascending. " + "Multiple sort criteria are supported.")
})
public @interface ApiParamPageable {
}
