package awa.training.api.feature.student.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class StudentDTO {

    @Getter
    @Setter
    public static class CreateStudentReq {
        private Long universityId;
        private Long studentId;
        private String studentName;
        private String studentNameEn;
        //private boolean studentStatus;
        private String createBy;
    }

    @Getter
    @Setter
    public static class FindAllStudentRes {
        private Long id;
        private Long universityId;
        private Long studentId;
        private String studentName;
        private String studentNameEn;
        private boolean studentStatus;
        private boolean isDelete;
    }

    @Getter
    @Setter
    public static class UpdateStudentRes {
        private Long id;
        private Long universityId;
        private Long studentId;
        private String studentName;
        private String studentNameEn;
        private boolean studentStatus;
        private boolean isDelete;
        private LocalDateTime updateDate;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class DeleteStudentReq {
        private Long id;
    }
}
