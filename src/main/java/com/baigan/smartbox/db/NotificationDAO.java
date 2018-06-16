package com.baigan.smartbox.db;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Timestamp;
import java.util.UUID;

public interface NotificationDAO {
    // DATABASE FIELDS
    String SCHEMA_NAME = "smartbox";
    String TABLE_NAME = "notification";

    // COLUMN NAMES
    String ID = "id";
    String PRODUCT_ID = "product_id";
    String EVENT = "event";
    String EVENT_TS = "event_ts";
    String INSERT_ID = "insert_id";

    String INSERT_FIELDS = ID
            + ", " + PRODUCT_ID
            + ", " + EVENT
            + ", " + EVENT_TS
            + ", " + INSERT_ID;

    String INSERT_BIND_FIELDS = ":" + ID
            + ", :" + PRODUCT_ID
            + ", :" + EVENT
            + ", :" + EVENT_TS
            + ", :" + INSERT_ID;

    @SqlUpdate("INSERT INTO " + SCHEMA_NAME + "." + TABLE_NAME + " (" + INSERT_FIELDS + ") values (" + INSERT_BIND_FIELDS + ")")
    void insert(@Bind(ID) UUID id,
                @Bind(PRODUCT_ID) String productId,
                @Bind(EVENT) String event,
                @Bind(EVENT_TS) Timestamp eventTimestamp,
                @Bind(INSERT_ID) String insertId);
}
