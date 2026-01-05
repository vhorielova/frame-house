package com.fcsc.pi.framehouse.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "rental")
public class Rental extends BaseEntity{
    @Column (name = "rent_date")
    private Date rentDate;

    @Column (name = "return_date")
    private Date returnDate;

    @Column (name = "cost")
    private float cost;

    @Column (name = "customer_id")
    private int customerId;

    @Column (name = "carrier_id")
    private int carrierId;
}
