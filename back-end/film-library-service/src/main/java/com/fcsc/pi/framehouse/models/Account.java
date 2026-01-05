package com.fcsc.pi.framehouse.models;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "account")
public class Account extends BaseEntity {
    @Column (name = "name")
    private String name;

    @Column (name = "login")
    private String login;

    @Column (name = "password")
    private String password;

    @Column (name = "role")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Role role;

    public enum Role {admin, cachier, root}
}
