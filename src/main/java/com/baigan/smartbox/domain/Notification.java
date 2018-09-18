package com.baigan.smartbox.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {

    @JsonProperty("notification_id")
    private String notificationId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("event")
    private String event;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @JsonProperty("event_ts")
    private LocalDateTime eventTimestamp;

}
