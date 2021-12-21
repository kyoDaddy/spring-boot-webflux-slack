package com.demo.process.user.repository.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
@Entity
@Table(name = "user", schema = "public") // 테이블명, 인덱스 등에 관한 설정
public class User implements Serializable {
    private static final long serialVersionUID = -563329217866858622L;

    @Id // PK 키임을 명시
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Builder
    public User(String username, String email, String password, Date createdAt, UUID id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = (createdAt == null) ? Timestamp.valueOf(LocalDateTime.now()) : createdAt;
        this.id = id;
    }


}
