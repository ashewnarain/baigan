package com.baigan.smartbox.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.sql.Timestamp;
import java.util.Locale;
import java.util.TimeZone;

import static com.baigan.smartbox.db.CoreDAO.*;

@Data
public class PassCodeDO {

    @ColumnName(PRODUCT_ID)
    @JsonProperty(PRODUCT_ID)
    private String productId;

    @ColumnName(PASS_CODE)
    @JsonProperty(PASS_CODE)
    private String passCode;

    @ColumnName(CREATED_TS)
    @JsonProperty(CREATED_TS)
    private Timestamp createdTs;

    @ColumnName(INSERT_TS)
    @JsonProperty(INSERT_TS)
    private Timestamp insertTs;

    @ColumnName(INSERT_ID)
    @JsonProperty(INSERT_ID)
    private String insertId;

}
