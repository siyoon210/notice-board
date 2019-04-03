package me.siyoon.noticeboard.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="notice_content")
@Getter
@Setter
@Builder @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class NoticeContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;
}
