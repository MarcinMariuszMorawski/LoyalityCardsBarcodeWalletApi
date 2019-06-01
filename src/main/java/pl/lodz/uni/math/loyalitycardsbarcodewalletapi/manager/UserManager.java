package pl.lodz.uni.math.loyalitycardsbarcodewalletapi.manager;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.lodz.uni.math.loyalitycardsbarcodewalletapi.dao.UserRepo;
import pl.lodz.uni.math.loyalitycardsbarcodewalletapi.dao.entity.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public final class UserManager {
    private UserRepo userRepo;

    @Autowired
    public UserManager(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepo.findByLogin(login);
    }

    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    public User save(User user) {
        user.setLogin(StringUtils.uncapitalize(user.getLogin()));
        return userRepo.save(user);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fill() {
        save(new User("1", "1", Timestamp.valueOf(LocalDateTime.now()), true));
    }


}