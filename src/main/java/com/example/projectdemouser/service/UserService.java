package com.example.projectdemouser.service;



import com.example.projectdemouser.dto.LoginRequest;
import com.example.projectdemouser.dto.LoginResponse;
import com.example.projectdemouser.exception.IncorrectOrderRequestException;
import com.example.projectdemouser.exception.OrderNotFoundException;
import com.example.projectdemouser.models.Users;
import com.example.projectdemouser.repository.UsersRepository;
import com.example.projectdemouser.util.EntityDtoConverter;
import com.example.projectdemouser.util.UserValidator;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Value("${jwt.password}")
    private String jwtSecret;

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private EntityDtoConverter entityDtoConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAutheticationServiceImpl userAutheticationService;

    @Transactional
    public Users createUser(Users user) {
        try {
            UserValidator.validate(user);
            Users existUser=userRepository.findByUsername(user.getUsername())
                    .orElse(null);
            if(existUser!=null)
                throw new IncorrectOrderRequestException("El nombre usuario ya existe");

            String encoder=passwordEncoder.encode(user.getPassword());
            user.setPassword(encoder);

            return userRepository.save(user);
        } catch (IncorrectOrderRequestException | OrderNotFoundException e) {
            throw e;
        }
    }


    public LoginResponse login(LoginRequest request){
        try {
            Users user=userRepository.findByEmail(request.getUsername())
                    .orElseThrow(()->new IncorrectOrderRequestException("Usuario o password incorrecto"));

            if(user.isIsactive()==false)
                throw new IncorrectOrderRequestException("El Usuario esta inactivo");

            if(!passwordEncoder.matches(request.getPassword(),user.getPassword()))
                throw new IncorrectOrderRequestException("Usuario o password incorrectos");

            String token =createToken(user);

            user.setToken(token);
            Users po=userRepository.save(user);

            return new LoginResponse(po);

        } catch (IncorrectOrderRequestException | OrderNotFoundException e) {
            throw e;
        }
    }



    public Users savetoken(Users p,String token){
        Users kp= new Users();
        kp.setEmail(p.getEmail());
        kp.setPhones(p.getPhones());
        kp.setPassword(token);
        kp=userRepository.save(kp);
        return kp;
    }



    //Fuente: https://blog.softtek.com/es/autenticando-apis-con-spring-y-jwt
    public String createToken(Users user){
        Date now =new Date();
        Date expiryDate=new Date(now.getTime()+ (1000*60*60));

        return Jwts.builder()
            .setSubject(user.getUsername())
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (UnsupportedJwtException e) {
            log.error("JWT in a particular format/configuration that does not match the format expected");
        }catch (MalformedJwtException e) {
            log.error(" JWT was not correctly constructed and should be rejected");
        }catch (SignatureException e) {
            log.error("Signature or verifying an existing signature of a JWT failed");
        }catch (ExpiredJwtException e) {
            log.error("JWT was accepted after it expired and must be rejected");
        }
        return false;
    }

    public String getUsernameFromToken(String jwt) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
        } catch (Exception e) {
            throw new IncorrectOrderRequestException("Invalid Token");
        }

    }


}