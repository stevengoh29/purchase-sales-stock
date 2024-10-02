package com.stevengoh.purchase_sales_stock.modules.auth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResRegisterDto {
    private String authToken;
}

