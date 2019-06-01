package pl.lodz.uni.math.loyalitycardsbarcodewalletapi.dao.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
public final class User {
    private Long id;
    private String login;
    private String password;
    private Timestamp dateTimeOfLastPasswordChange;
    private Boolean active;

    public User() {
    }

    public User(String login, String password, Timestamp dateTimeOfLastPasswordChange, Boolean active) {
        this.login = login;
        this.password = password;
        this.dateTimeOfLastPasswordChange = dateTimeOfLastPasswordChange;
        this.active = active;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @NotBlank
    @NotNull
    @Size(max = 35)
    @Column(name = "login", length = 35, nullable = false, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @NotBlank
    @NotNull
    @Size(max = 64)
    @Column(name = "password", length = 64, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @NotNull
    @Column(name = "date_time_of_last_password_change", nullable = false)
    public Timestamp getDateTimeOfLastPasswordChange() {
        return dateTimeOfLastPasswordChange;
    }

    public void setDateTimeOfLastPasswordChange(Timestamp dateTimeOfLastPasswordChange) {
        this.dateTimeOfLastPasswordChange = dateTimeOfLastPasswordChange;
    }

    @Basic
    @NotNull
    @Column(name = "active", nullable = false)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", dateTimeOfLastPasswordChange=" + dateTimeOfLastPasswordChange +
                ", active=" + active +
                ", cards=" + cards +
                '}';
    }
}