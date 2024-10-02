package com.stevengoh.purchase_sales_stock.modules.users.dtos;

import com.stevengoh.purchase_sales_stock.base.BaseDto;
import com.stevengoh.purchase_sales_stock.modules.users.enums.Status;

public class UserDto implements BaseDto {
    private String username;
    private String firstName;
    private String lastName;
    private Status status;
}
