package me.siyoon.noticeboard.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Notice")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of="id")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String title;

    @Column
    private Date registerDate;

    @Column
    private User user;

    @Column
    private Date modifyDate;

    @Column
    private NoticeContent noticeContent;
}
