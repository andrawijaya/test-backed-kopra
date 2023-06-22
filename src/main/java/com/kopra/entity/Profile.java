package com.kopra.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Getter @Setter
@ToString
@Entity
@Table(name = "m_profile")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FIRST_NAME", length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private  String lastName;

    @Column(name = "ADDRESS")
    private Float address;

    @Column(name = "BIRTHDAY")
    @JsonFormat(pattern="yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "EMAIL", length = 100)
    private String email;

    @Column(name = "CITY", length = 45)
    private String city;

    @Column(name = "COUNTRY", length = 45)
    private String country;


}
