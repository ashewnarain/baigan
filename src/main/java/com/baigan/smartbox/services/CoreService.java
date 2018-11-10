package com.baigan.smartbox.services;

import com.baigan.smartbox.db.CoreDAO;
import com.baigan.smartbox.db.NotificationDO;
import com.baigan.smartbox.db.PassCodeDO;
import com.baigan.smartbox.domain.Notification;
import com.baigan.smartbox.domain.PassCode;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
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

    public Notification getNotification(String requestId, String notificationId) throws Exception {
        NotificationDO notificationDO = dao.getNotification(UUID.fromString(notificationId));
        if (null == notificationDO) {
            String message = MessageFormat.format("No record found in the database! - requestId={0} notificationId={1}", requestId, notificationId);
            log.warn(message);
            throw new Exception(message);
        }
        Notification notification = new Notification();
        notification.setNotificationId(notificationDO.getId());
        notification.setEvent(notificationDO.getEvent());
        notification.setProductId(notification.getProductId());
        notification.setEventTimestamp(notificationDO.getEventTs().toLocalDateTime());
        return notification;
    }

    public List<Notification> getNotificationList(String requestId) throws Exception {
        List<NotificationDO> notificationDOList = dao.getAllNotifications();
        if (null == notificationDOList) {
            String message = MessageFormat.format("No record found in the database! - requestId={0}", requestId);
            log.warn(message);
            throw new Exception(message);
        }
        List<Notification> notificationList = new ArrayList<>();
        for (NotificationDO notificationDO : notificationDOList) {
            Notification notification = new Notification();
            notification.setNotificationId(notificationDO.getId());
            notification.setEvent(notificationDO.getEvent());
            notification.setProductId(notificationDO.getProductId());
            notification.setEventTimestamp(notificationDO.getEventTs().toLocalDateTime());
            notificationList.add(notification);
        }
        return notificationList;
    }


    public String savePassCode(PassCode passCode) {
        dao.insertPassCode(passCode.getProductId(),
                passCode.getPassCode(),
                Timestamp.valueOf(passCode.getCreatedTs()),
                appId);
        return passCode.getPassCode();
    }

    public String updatePassCode(PassCode passCode) {
        dao.updatePassCode(passCode.getProductId(),
                passCode.getPassCode(),
                Timestamp.valueOf(passCode.getCreatedTs()),
                appId);
        return passCode.getPassCode();
    }

    public List<PassCode> getPassCodeList(String requestId) throws Exception {
        List<PassCodeDO> passCodeDOList = dao.selectAllPassCodes();
        if (null == passCodeDOList) {
            String message = MessageFormat.format("No record found in the database! - requestId={0}", requestId);
            log.warn(message);
            throw new Exception(message);
        }
        List<PassCode> passCodeList = new ArrayList<>();
        for (PassCodeDO passCodeDO: passCodeDOList) {
            PassCode passCode = new PassCode();
            passCode.setProductId(passCodeDO.getProductId());
            passCode.setPassCode(passCodeDO.getPassCode());
            passCode.setCreatedTs(passCodeDO.getCreatedTs().toLocalDateTime());
            passCodeList.add(passCode);
        }
        return passCodeList;
    }

    public PassCode getPassCode(String requestId, String productId) throws Exception {
        PassCodeDO passCodeDO = dao.selectPassCode(productId);
        if (null == passCodeDO) {
            String message = MessageFormat.format("No record found in the database! - requestId={0}", requestId);
            log.warn(message);
            throw new Exception(message);
        }
        PassCode passCode = new PassCode();
        passCode.setProductId(passCodeDO.getProductId());
        passCode.setPassCode(passCodeDO.getPassCode());
        passCode.setCreatedTs(passCodeDO.getCreatedTs().toLocalDateTime());
        return passCode;
    }

}
