package com.baigan.smartbox.db;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public interface CoreDAO {
    // DATABASE FIELDS
    String SCHEMA_NAME = "smartbox";
    String NOTIFICATION_TABLE_NAME = "notification";
    String PASS_CODE_TABLE_NAME = "pass_code";

    // COLUMN NAMES
    String ID = "id";
    String PRODUCT_ID = "product_id";
    String EVENT = "event";
    String EVENT_TS = "event_ts";
    String INSERT_ID = "insert_id";
    String PASS_CODE = "pass_code";
    String CREATED_TS = "created_ts";
    String INSERT_TS = "insert_ts";

    String NOTIFICATION_INSERT_FIELDS = ID
            + ", " + PRODUCT_ID
            + ", " + EVENT
            + ", " + EVENT_TS
            + ", " + INSERT_ID;

    String NOTIFICATION_INSERT_BIND_FIELDS = ":" + ID
            + ", :" + PRODUCT_ID
            + ", :" + EVENT
            + ", :" + EVENT_TS
            + ", :" + INSERT_ID;

    String PASSCODE_INSERT_FIELDS = PRODUCT_ID
            + ", " + PASS_CODE
            + ", " + CREATED_TS
            + ", " + INSERT_ID;

    String PASSCODE_INSERT_BIND_FIELDS = ":" + PRODUCT_ID
            + ", :" + PASS_CODE
            + ", :" + CREATED_TS
            + ", :" + INSERT_ID;

    @SqlUpdate("INSERT INTO " + SCHEMA_NAME + "." + NOTIFICATION_TABLE_NAME + " (" + NOTIFICATION_INSERT_FIELDS + ") values (" + NOTIFICATION_INSERT_BIND_FIELDS + ")")
    void insertNotification(@Bind(ID) UUID id,
                @Bind(PRODUCT_ID) String productId,
                @Bind(EVENT) String event,
                @Bind(EVENT_TS) Timestamp eventTimestamp,
                @Bind(INSERT_ID) String insertId);


    @SqlUpdate("INSERT INTO " + SCHEMA_NAME + "." + PASS_CODE_TABLE_NAME + " (" + PASSCODE_INSERT_FIELDS + ") values (" + PASSCODE_INSERT_BIND_FIELDS + ")")
    void insertPassCode(@Bind(PRODUCT_ID) String productId,
                        @Bind(PASS_CODE) String passCode,
                        @Bind(CREATED_TS) Timestamp createdTimestamp,
                        @Bind(INSERT_ID) String insertId);


    @SqlQuery("SELECT * FROM " + SCHEMA_NAME + "." + PASS_CODE_TABLE_NAME + " WHERE " + PRODUCT_ID + " = :" + PRODUCT_ID)
    @RegisterBeanMapper(PassCodeDO.class)
    PassCodeDO selectPassCode(@Bind(PRODUCT_ID) String productId);

}
