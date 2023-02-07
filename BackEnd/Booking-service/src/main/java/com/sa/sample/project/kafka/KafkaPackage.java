package com.sa.sample.project.kafka;

import com.sa.sample.project.dto.Room;
import com.sa.sample.project.model.Booking;
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
