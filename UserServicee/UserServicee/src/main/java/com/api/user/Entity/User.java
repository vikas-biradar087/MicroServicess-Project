package com.api.user.Entity;

import com.api.user.Payload.RatingDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private String userId;
    @Column(name="NAME",length = 20,nullable = false)
    private String name;

    @Column(name="EMAIL")
    private String email;

    @Column(name="ABOUT")
    private String about;

    @Transient
    private List<Rating> ratings=new ArrayList<>();

}
