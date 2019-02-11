package com.inther.entities;

import com.inther.dto.AuthenticationDto;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User
{
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue
    private UUID id;

    private String email;
    private String password;
    private String role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Presentation> presentations;

    public User(AuthenticationDto authenticationDto) {
        this.email = authenticationDto.getEmail();
        this.password = authenticationDto.getPassword();
        this.role=authenticationDto.getRole();
    }

    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User updateBy(User user) {
        this.email = user.email;
        this.password = user.password;
        this.role = user.role;
        return this;
    }
}