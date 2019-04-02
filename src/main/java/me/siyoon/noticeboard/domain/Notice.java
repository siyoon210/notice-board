package me.siyoon.noticeboard.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notice")
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date modifyDate;

    @OneToOne
    @JoinColumn(name = "notice_content_id")
    private NoticeContent noticeContent;
}
