package awa.training.api.feature.teacher.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class TeacherDTO {

    @Getter
    @Setter
    public static class CreateTeacherReq {
        private Long universityId;
        private String teacherCode;
        private String teacherName;
        private String teacherNameEn;
        private String createBy;
    }

    @Getter
    @Setter
    public static class UpdateTeacherRes {
        private Long id;
        private Long universityId;
        private String teacherCode;
        private String teacherName;
        private String teacherNameEn;
        private boolean isDelete;
        private LocalDateTime updateDate;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class FindAllTeacherRes {
        private Long id;
        private Long universityId;
        private String teacherCode;
        private String teacherName;
        private String teacherNameEn;
        private boolean isDelete;
    }

    @Getter
    @Setter
    public static class DeleteTeacherReq {
        private Long id;
    }
}
