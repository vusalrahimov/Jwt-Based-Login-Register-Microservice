package io.desofme.cityservice.entity;

import io.desofme.cityservice.enums.EnumStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 40, nullable = false)
    private String cityName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Enumerated
    private EnumStatus enable = EnumStatus.ENABLE;

}
