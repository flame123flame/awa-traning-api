package awa.training.api.feature.enrollment.service;

import awa.training.api.common.model.CommonResponse;
import awa.training.api.feature.enrollment.dto.EnrollmentDTO;
import awa.training.api.feature.enrollment.entity.EnrollmentEntity;
import awa.training.api.feature.enrollment.repository.EnrollmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public CommonResponse<Object> create(EnrollmentDTO.CreateEnrollmentReq req) {
        CommonResponse<Object> response = new CommonResponse<>();

        EnrollmentEntity enrollmentEntity = new EnrollmentEntity();
        enrollmentEntity.setStudentId(req.getStudentId());
        enrollmentEntity.setCourseId(req.getCourseId());
        enrollmentEntity.setCreateBy("System");
        enrollmentEntity.setCreateDate(LocalDateTime.now());
        enrollmentRepository.save(enrollmentEntity);

        response.setMessage("Enrollment created successfully");
        return response;
    }

    @Transactional
    public CommonResponse<EnrollmentDTO.UpdateEnrollmentRes> update(EnrollmentDTO.UpdateEnrollmentRes req) {
        CommonResponse<EnrollmentDTO.UpdateEnrollmentRes> response = new CommonResponse<>();
        Optional<EnrollmentEntity> enrollmentOptional = enrollmentRepository.findById(req.getId());
        EnrollmentEntity enrollment = enrollmentOptional.get();

        enrollment.setCourseId(req.getCourseId());
        enrollment.setStudentId(req.getStudentId());
        enrollment.setUpdateBy("System");
        enrollment.setUpdateDate(LocalDateTime.now());

        enrollmentRepository.save(enrollment);


        return response;
    }

    @Transactional
    public CommonResponse<List<EnrollmentDTO.FindAllEnrollmentRes>> findAll() {
        CommonResponse<List<EnrollmentDTO.FindAllEnrollmentRes>> response = new CommonResponse<>();
        List<EnrollmentDTO.FindAllEnrollmentRes> dataReturn = new ArrayList<>();
        List<EnrollmentEntity> enrollment = enrollmentRepository.findAll();

        for (int i = 0; i < enrollment.size(); i++) {
            EnrollmentDTO.FindAllEnrollmentRes data = new EnrollmentDTO.FindAllEnrollmentRes();
            data.setId(enrollment.get(i).getId());
            data.setStudentId(enrollment.get(i).getStudentId());
            data.setCourseId(enrollment.get(i).getCourseId());
            data.setCreateDate(enrollment.get(i).getCreateDate());
            data.setCreateBy(enrollment.get(i).getCreateBy());
            data.setUpdateDate(enrollment.get(i).getUpdateDate());
            data.setUpdateBy(enrollment.get(i).getUpdateBy());

            dataReturn.add(data);
        }

        response.setData(dataReturn);
        return response;
    }

    @Transactional
    public CommonResponse<EnrollmentDTO.FindAllEnrollmentRes> findById(Long id) {
        CommonResponse<EnrollmentDTO.FindAllEnrollmentRes> response = new CommonResponse<>();
        Optional<EnrollmentEntity> enrollmentOptional = enrollmentRepository.findById(id);

            EnrollmentEntity enrollment = enrollmentOptional.get();
            EnrollmentDTO.FindAllEnrollmentRes data = new EnrollmentDTO.FindAllEnrollmentRes();
            data.setId(enrollment.getId());
            data.setStudentId(enrollment.getStudentId());
            data.setCourseId(enrollment.getCourseId());
            data.setCreateDate(enrollment.getCreateDate());
            data.setCreateBy(enrollment.getCreateBy());
            data.setUpdateDate(enrollment.getUpdateDate());
            data.setUpdateBy(enrollment.getUpdateBy());

            response.setData(data);


        return response;
    }

    @Transactional
    public CommonResponse<EnrollmentDTO.DeleteEnrollmentReq> delete(EnrollmentDTO.DeleteEnrollmentReq id) {
        CommonResponse<EnrollmentDTO.DeleteEnrollmentReq> response = new CommonResponse<>();
        enrollmentRepository.deleteById(id.getId());

        return response;
    }
}
