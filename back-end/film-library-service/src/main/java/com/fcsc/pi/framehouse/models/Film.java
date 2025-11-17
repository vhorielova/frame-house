package com.fcsc.pi.framehouse.models;

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
@Table(name= "film")
public class Film extends BaseEntity{
    @Column (name = "carrier")
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private Carrier type;

    @Column (name = "amount")
    private int amount;

    @Column (name = "film_id")
    private int filmId;

    enum Carrier{dvd, flash_card, online, other}
}
