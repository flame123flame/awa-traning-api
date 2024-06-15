package awa.training.api.feature.university.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "university")
public class UniversityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "university_code")
    private String universityCode;

    @Column(name = "university_name")
    private String universityName;

    @Column(name = "university_name_en")
    private String universityNameEn;

    @Column(name = "university_short_name")
    private String universityShortName;

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
