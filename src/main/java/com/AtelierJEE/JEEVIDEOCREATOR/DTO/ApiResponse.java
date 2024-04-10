package com.AtelierJEE.JEEVIDEOCREATOR.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T>{
    private List<T> items;
    private Integer totalItems;
}
