package com.baigan.smartbox.db;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.sql.Timestamp;

public class NotificationDO {

    @ColumnName("id")
    private String id;

    @ColumnName("product_id")
    private String productId;

    @ColumnName("event")
    private String event;

    @ColumnName("event_ts")
    private Timestamp eventTimestamp;

    @ColumnName("insert_ts")
    private Timestamp insertTimestamp;

    @ColumnName("insert_id")
    private String insertId;

    public NotificationDO() {}
}
