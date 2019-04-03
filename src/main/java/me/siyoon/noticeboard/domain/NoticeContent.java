package me.siyoon.noticeboard.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="notice_content")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of="id")
public class NoticeContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;
}
