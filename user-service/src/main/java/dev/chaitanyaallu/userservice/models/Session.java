package dev.chaitanyaallu.userservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sessions")
public class Session extends BaseModel{
    @Column(name = "token", length = 1000)
    private String token;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "expiring_at")
    private Date expiringAt;
    @Column(name = "session_status")
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
    @ManyToOne
    @JoinColumn(name = "user_fk")
    private User user;
}
