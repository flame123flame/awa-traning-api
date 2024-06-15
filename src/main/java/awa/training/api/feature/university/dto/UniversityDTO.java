
package awa.training.api.feature.university.dto;

import lombok.Getter;
import lombok.Setter;


public class UniversityDTO {

    @Getter
    @Setter
     static public class CreateUniversityReq {
        private String universityCode;
        private String universityName;
        private String universityNameEn;
        private String universityShortName;
        private String createdBy;
    }

    @Getter
    @Setter
     static public class FindAllUniversityRes {
        private Long id;
        private String universityCode;
        private String universityName;
        private String universityNameEn;
        private String universityShortName;
        private String createdBy;
        private String createDate;
    }

    @Getter
    @Setter
     static public class UpdateUniversityRes {
        private Long id;
        private String universityCode;
        private String universityName;
        private String universityNameEn;
        private String universityShortName;
        private String updatedBy;
        private String updateDate;
    }

    @Getter
    @Setter
     static public class DeleteUniversityReq {
        private Long id;
    }

}
