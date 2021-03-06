package pl.lodz.uni.math.loyalitycardsbarcodewalletapi.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.lodz.uni.math.loyalitycardsbarcodewalletapi.dao.entity.User;
import pl.lodz.uni.math.loyalitycardsbarcodewalletapi.manager.UserManager;

import java.io.Serializable;

@Component
public final class JwtAuthenticationServer implements Serializable {
    @Autowired
    UserManager userManager;
    @Autowired
    JwtTokenService jwtTokenService;

    public String validateUserCredentials(JwtUser jwtUser) {

        if (userManager.findByEmail(jwtUser.getEmail()).isPresent()) {

            User user = userManager.findByEmail(jwtUser.getEmail()).get();

            if (user.getPassword().equals(jwtUser.getPassword()) && user.getActive()) {
                return jwtTokenService.generateUserToken(jwtUser);
            }
        }
        return null;
    }
}