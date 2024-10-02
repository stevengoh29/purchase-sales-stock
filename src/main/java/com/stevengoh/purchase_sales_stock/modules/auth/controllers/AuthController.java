package com.stevengoh.purchase_sales_stock.modules.auth.controllers;

import com.stevengoh.purchase_sales_stock.base.BaseResponse;
import com.stevengoh.purchase_sales_stock.modules.auth.dtos.ResLoginDto;
import com.stevengoh.purchase_sales_stock.modules.auth.dtos.ResRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    @PostMapping(name = "login")
    public ResponseEntity<BaseResponse<ResRegisterDto>> loginController(
            @RequestBody final ResLoginDto loginDto
    ) {

    }

    @PostMapping(name = "register")
    public ResponseEntity<BaseResponse<ResRegisterDto>> registerController(
            @RequestBody final ResRegisterDto registerDto
    ) {

    }
}
