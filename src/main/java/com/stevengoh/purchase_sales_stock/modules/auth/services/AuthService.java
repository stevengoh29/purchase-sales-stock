package com.stevengoh.purchase_sales_stock.modules.auth.services;

import com.stevengoh.purchase_sales_stock.base.BaseService;
import com.stevengoh.purchase_sales_stock.modules.auth.dtos.ReqLoginDto;
import com.stevengoh.purchase_sales_stock.modules.auth.dtos.ReqRegisterDto;
import com.stevengoh.purchase_sales_stock.modules.auth.dtos.ResLoginDto;
import com.stevengoh.purchase_sales_stock.modules.auth.dtos.ResRegisterDto;
import com.stevengoh.purchase_sales_stock.modules.users.dtos.UserDto;
import com.stevengoh.purchase_sales_stock.modules.users.models.User;
import com.stevengoh.purchase_sales_stock.modules.users.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthService extends BaseService<UserRepository, User, UserDto> {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository repository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        super(repository, modelMapper);
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResRegisterDto signup(ReqRegisterDto input) {
        User user = getModelMapper().map(input, User.class);
        User createdUser = getRepository().save(user);

        String token = jwtService.generateToken(createdUser);
        ResRegisterDto response = new ResRegisterDto();
        response.setAuthToken(token);

        return response;
    }

    public ResLoginDto login(ReqLoginDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(input.getUsername(),input.getPassword())
        );

        User user = getRepository().findByUsername(input.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        ResLoginDto response = new ResLoginDto();
        response.setAuthToken(token);

        return response;
    }
}
