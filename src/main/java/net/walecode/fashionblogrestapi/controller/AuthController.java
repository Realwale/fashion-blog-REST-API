package net.walecode.fashionblogrestapi.controller;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.walecode.fashionblogrestapi.dto.JWTAuthResponse;
import net.walecode.fashionblogrestapi.dto.LoginDto;
import net.walecode.fashionblogrestapi.dto.RegisterDto;
import net.walecode.fashionblogrestapi.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "CRUD REST APIs for Auth Resource"
)
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Build Register REST API
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Build Login REST API
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }

}



















//==========================================================

//Basic login and register REST API
//package net.walecode.fashionblogrestapi.controller;
//
//
//import net.walecode.fashionblogrestapi.dto.LoginDto;
//import net.walecode.fashionblogrestapi.dto.RegisterDto;
//import net.walecode.fashionblogrestapi.service.AuthService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/auth")
//public class AuthController {
//
//    private AuthService authService;
//
//    public AuthController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @PostMapping(value ={"/login", "/signin"})
//    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
//       String response = authService.login(loginDto);
//
//       return ResponseEntity.ok(response);
//    }
//
//    @PostMapping(value ={ "/register", "/signup"})
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
//        String response = authService.register(registerDto);
//
//        return new ResponseEntity<>(response, HttpStatus.CREATED);
//
//    }
//}
//
