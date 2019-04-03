package me.siyoon.noticeboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of="id")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String name;
}
