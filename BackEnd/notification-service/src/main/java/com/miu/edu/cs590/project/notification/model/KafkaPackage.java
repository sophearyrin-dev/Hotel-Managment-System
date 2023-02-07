package com.miu.edu.cs590.project.notification.model;

import com.miu.edu.cs590.project.notification.common.Booking;
import com.miu.edu.cs590.project.notification.common.CookiesInfo;
import com.miu.edu.cs590.project.notification.common.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPackage {
    private Booking booking;
    private Room room;
    private CookiesInfo cookiesInfo;
}
