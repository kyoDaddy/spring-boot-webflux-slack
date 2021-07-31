package com.demo.process.user.domain;

import lombok.*;
import org.springframework.util.Assert;

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

    @Column(nullable = false, length = 50)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date regDtt;

    @Builder
    public User(String username, String email, String password, Date regDtt, UUID id) {
        /* // 안전한 객채 생성 패턴
        Assert.hasText(username, "userName must not be empty");
        Assert.hasText(email, "email must not be empty");
        Assert.hasText(password, "password must not be empty");
        */
        this.username = username;
        this.email = email;
        this.password = password;
        this.regDtt = (regDtt == null) ? Timestamp.valueOf(LocalDateTime.now()) : regDtt;
        this.id = id;
    }


}
