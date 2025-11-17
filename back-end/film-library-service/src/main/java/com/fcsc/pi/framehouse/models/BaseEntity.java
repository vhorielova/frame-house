package com.fcsc.pi.framehouse.models;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
}
