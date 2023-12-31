package com.api.sample.model.response;


import com.api.sample.common.BaseCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.ObjectUtils.Null;

@Data
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class StandardResponse<T> {

  private String statusCode;
  private String message;
  private T data;
  private Instant timestamp;

  public static <T> StandardResponse<T> fromCodeEnum(BaseCodeEnum statusCodeEnum, T data) {
    return StandardResponse.<T>builder()
        .statusCode(statusCodeEnum.getCode())
        .message(statusCodeEnum.getMessage())
        .data(data)
        .timestamp(Instant.now())
        .build();
  }

  public static StandardResponse<Null> fromCodeEnum(BaseCodeEnum baseCodeEnum) {
    return fromCodeEnum(baseCodeEnum, null);
  }
}

