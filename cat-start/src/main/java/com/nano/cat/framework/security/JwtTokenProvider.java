package com.nano.cat.framework.security;

import com.nano.cat.framework.data.UserRoleEnum;
import com.nano.cat.framework.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.nano.cat.framework.constants.SecurityConstants.JWT_COOKIE_NAME;

@Component
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;  // 1h

    @Autowired
    private NanoUserDetails nanoUserDetails;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 创建Token
     *
     * @param username      用户昵称
     * @param userRoleEnums 用户角色信息
     * @return 生成的Token
     */
    public String createToken(String username, List<UserRoleEnum> userRoleEnums) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("auth", userRoleEnums.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createToken(long userId, List<UserRoleEnum> userRoleEnums) {
        // JWT的主体核心部分
        Claims claims = Jwts.claims().setSubject(String.valueOf(userId));
        claims.put("auth", userRoleEnums.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).collect(Collectors.toList()));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        // 构建security用户信息
        UserDetails userDetails = nanoUserDetails.loadUserByUsername(getUserIdStrFromToken(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserIdStrFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    public long getUserId(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject());
    }

    public String resolveToken(HttpServletRequest req) {
        // 从请求头Authorization中获取Token
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        // 从Cookie获取Token
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (JWT_COOKIE_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
