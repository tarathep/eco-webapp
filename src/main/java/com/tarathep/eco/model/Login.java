package com.tarathep.eco.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NonNull;


@Data
@Entity
@Table(name = "login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer account_id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private String deletepass;

    @NonNull
    private Integer banned;

    @NonNull
    private Integer gmlevel;

    @NonNull
    private Integer bank;

    @NonNull
    private Integer vshop_points;

    @NonNull
    private Integer used_vshop_points;

    @NonNull
    private String lastip;

    public Login(){}


}