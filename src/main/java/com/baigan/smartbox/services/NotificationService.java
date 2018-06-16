package com.baigan.smartbox.services;

import com.baigan.smartbox.db.NotificationDAO;
import com.baigan.smartbox.domain.Notification;
import org.jdbi.v3.core.Jdbi;

import java.sql.Timestamp;
import java.util.UUID;

public class NotificationService {
    private String appId;
    private NotificationDAO dao;

    public NotificationService(String appId, Jdbi jdbi) {
        this.appId = appId;
        dao = jdbi.onDemand(NotificationDAO.class);
    }

    public String saveNotification(Notification notification) {
        dao.insert(UUID.fromString(notification.getNotificationId()),
                notification.getProductId(),
                notification.getEvent(),
                Timestamp.valueOf(notification.getEventTimestamp()),
                appId);
        return notification.getNotificationId();
    }
}
