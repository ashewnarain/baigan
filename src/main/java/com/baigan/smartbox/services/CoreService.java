package com.baigan.smartbox.services;

import com.baigan.smartbox.db.CoreDAO;
import com.baigan.smartbox.db.PassCodeDO;
import com.baigan.smartbox.domain.Notification;
import com.baigan.smartbox.domain.PassCode;
import org.jdbi.v3.core.Jdbi;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class CoreService {
    private String appId;
    private CoreDAO dao;

    public CoreService(String appId, Jdbi jdbi) {
        this.appId = appId;
        dao = jdbi.onDemand(CoreDAO.class);
    }

    public String saveNotification(Notification notification) {
        dao.insertNotification(UUID.fromString(notification.getNotificationId()),
                notification.getProductId(),
                notification.getEvent(),
                Timestamp.valueOf(notification.getEventTimestamp()),
                appId);
        return notification.getNotificationId();
    }

    public String savePassCode(PassCode passCode) {
        dao.insertPassCode(passCode.getProductId(),
                passCode.getPassCode(),
                Timestamp.valueOf(passCode.getCreatedTimestamp()),
                appId);
        return passCode.getPassCode();
    }

    public PassCodeDO getPassCode(String productId) {
        return dao.selectPassCode(productId);
    }
}
