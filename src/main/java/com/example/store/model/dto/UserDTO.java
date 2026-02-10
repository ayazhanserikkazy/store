package com.example.store.model.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO{
    private Long id;
    private String name;
    private String category;
    private int price;
    private int quantity;
    private String description;
}