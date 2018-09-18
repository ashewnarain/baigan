package com.baigan.smartbox.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.sql.Timestamp;

import static com.baigan.smartbox.db.CoreDAO.*;

@Data
public class NotificationDO {

    @ColumnName("id")
    @JsonProperty(ID)
    private String id;

    @ColumnName("product_id")
    @JsonProperty(PRODUCT_ID)
    private String productId;

    @ColumnName("event")
    @JsonProperty(EVENT)
    private String event;

    @ColumnName("event_ts")
    @JsonProperty(EVENT_TS)
    private Timestamp eventTs;

    @ColumnName("insert_ts")
    @JsonProperty(INSERT_TS)
    private Timestamp insertTs;

    @ColumnName("insert_id")
    @JsonProperty(INSERT_ID)
    private String insertId;

}
