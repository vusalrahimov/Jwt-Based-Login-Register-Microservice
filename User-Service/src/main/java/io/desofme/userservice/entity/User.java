package io.desofme.userservice.entity;

import io.desofme.userservice.enums.EnumStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 30, nullable = false)
    private String surname;
    @Column(length = 30, nullable = false)
    private String username;
    private Long cityId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Enumerated
    private EnumStatus enable = EnumStatus.ENABLE;
}
