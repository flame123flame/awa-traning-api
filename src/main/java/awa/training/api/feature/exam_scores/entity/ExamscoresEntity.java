package awa.training.api.feature.exam_scores.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "exam_scores")
public class ExamScoresEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @Column(name = "midterm_score")
    private float midtermScore;

    @Column(name = "final_score")
    private float finalScore;

    @Column(name = "sum_score")
    private float sumScore;

    @Column(name = "Semester")
    private String semester;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "update_by")
    private String updateBy;
    
}
