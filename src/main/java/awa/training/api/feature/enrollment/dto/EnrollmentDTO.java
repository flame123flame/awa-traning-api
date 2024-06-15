package awa.training.api.feature.enrollment.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EnrollmentDTO {

    @Getter
    @Setter
    public static class CreateEnrollmentReq {
        private Long studentId;
        private Long courseId;
        private String createBy;
    }

    @Getter
    @Setter
    public static class FindAllEnrollmentRes {
        private Long id;
        private Long studentId;
        private Long courseId;
        private LocalDateTime createDate;
        private String createBy;
        private LocalDateTime updateDate;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class UpdateEnrollmentRes {
        private Long id;
        private Long studentId;
        private Long courseId;
        private LocalDateTime updateDate;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class DeleteEnrollmentReq {
        private Long id;
    }
}
