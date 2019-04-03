package me.siyoon.noticeboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickName;
}