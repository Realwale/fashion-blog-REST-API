package net.walecode.fashionblogrestapi.service;


import net.walecode.fashionblogrestapi.dto.LoginDto;
import net.walecode.fashionblogrestapi.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
