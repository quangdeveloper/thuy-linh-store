package vn.free.register.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.free.register.constant.ResponseCode;
import vn.free.register.jwt.JWTTokenProvider;
import vn.free.register.request.user.UserLogin;
import vn.free.register.response.ResponseDTO;
import vn.free.register.response.UserLoginRP;
import vn.free.register.security.UserPrincipal;

/**
 * @author quangnv
 */
@Slf4j
@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLogin userLogin) {

        /** lấy authentication ra để tạo theo kiểu token*/
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLogin.getUsername(),
                        userLogin.getPassword()
                )
        );

        /** thiết lập authentication */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        String jwt = jwtTokenProvider.generateToken(userPrincipal);

        UserLoginRP userDTO = UserLoginRP.builder()
                .id(userPrincipal.getId())
                .username(userPrincipal.getUsername())
                .groupRoleId(userPrincipal.getGroupRoleId())
                .fullName(userPrincipal.getFullName())
                .email(userPrincipal.getEmail())
                .mobile(userPrincipal.getMobile())
                .address(userPrincipal.getAddress())
                .dateBorn(userPrincipal.getDateBorn())
                .status(userPrincipal.getActive())
                .token(jwt)
                .build();

        return ResponseEntity.ok().body(
                ResponseDTO.builder()
                        .data(userDTO)
                        .code(ResponseCode.SUCCESS.getCode())
                        .message(ResponseCode.SUCCESS.getDesc())
                        .build()
        );
    }
}
