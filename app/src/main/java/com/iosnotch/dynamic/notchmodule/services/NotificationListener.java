package com.iosnotch.dynamic.notchmodule.services;

import com.iosnotch.dynamic.notchmodule.entity.Notification;

public interface NotificationListener {
    void onItemClicked(Notification notification);

    void onItemClicked(Notification notification, int i);
}
