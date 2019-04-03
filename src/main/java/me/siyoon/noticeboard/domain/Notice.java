package me.siyoon.noticeboard.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private Long id;

    @Column
    private String title;

    @CreationTimestamp
    private LocalDateTime registerDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @OneToOne
    @JoinColumn(name = "notice_content_id")
    private NoticeContent noticeContent;
}
