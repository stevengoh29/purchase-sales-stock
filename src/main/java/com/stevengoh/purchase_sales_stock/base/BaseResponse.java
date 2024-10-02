package com.stevengoh.purchase_sales_stock.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String responseCode;
    private String message;
    private T data;
}
