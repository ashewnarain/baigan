package com.baigan.smartbox.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PassCode {

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("pass_code")
    private String passCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonProperty("created_ts")
    private LocalDateTime createdTimestamp;

}
