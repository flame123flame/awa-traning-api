package awa.training.api.feature.teacher.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "teacher")
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "teacher_code")
    private String teacherCode;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "teacher_name_en")
    private String teacherNameEn;

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
