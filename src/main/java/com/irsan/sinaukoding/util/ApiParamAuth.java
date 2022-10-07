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
                value = "Bearer Token")
})
public @interface ApiParamAuth {
}
