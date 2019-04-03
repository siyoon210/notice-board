package me.siyoon.noticeboard.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notice")
@Getter
@Setter
@Builder @NoArgsConstructor
@AllArgsConstructor
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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @UpdateTimestamp
    private LocalDateTime modifyDate;

    @OneToOne
    @JoinColumn(name = "notice_content_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private NoticeContent noticeContent;
}
