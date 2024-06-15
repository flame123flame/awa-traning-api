package awa.training.api.feature.course.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class CourseDTO {

    @Getter
    @Setter
    public static class CreateCourseReq {
        private Long teacherId;
        private String courseName;
        private String courseNameEn;
        private String courseShortName;
        private String createBy;
    }

    @Getter
    @Setter
    public static class FindAllCourseRes {
        private Long id;
        private Long teacherId;
        private String courseName;
        private String courseNameEn;
        private String courseShortName;
        private boolean isDelete;
    }

    @Getter
    @Setter
    public static class UpdateCourseRes {
        private Long id;
        private Long teacherId;
        private String courseName;
        private String courseNameEn;
        private String courseShortName;
        private boolean isDelete;
        private LocalDateTime updateDate;
        private String updateBy;
    }

    @Getter
    @Setter
    public static class DeleteCourseReq {
        private Long id;
    }
}
