package com.bej.NotificationService.Controller;

import com.bej.NotificationService.Service.INotificationService;
import com.bej.NotificationService.Service.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {
    @Autowired
    private INotificationService iNotificationService;


}
