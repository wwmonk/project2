package com.example.project.DTO;

import com.example.project.Entity.StoreEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private Integer id;
    private String name;
    private String username;
    private String num;
    private String time;
    private Long storeId;
    private StoreDTO storeDTO;
}
