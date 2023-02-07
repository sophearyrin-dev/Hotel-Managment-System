package com.miu.edu.cs590.project.notification.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class NotificationException extends IllegalArgumentException {
    public NotificationException(JsonProcessingException msg) { super(msg); }
}
