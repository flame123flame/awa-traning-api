package awa.training.api.feature.student.service;

import awa.training.api.common.ErrorCode;
import awa.training.api.common.model.CommonResponse;
import awa.training.api.exception.HandleException;
import awa.training.api.feature.student.dto.StudentDTO;
import awa.training.api.feature.student.entity.StudentEntity;
import awa.training.api.feature.student.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class StudentService {

    private final StudentRepository studentRepository;

    @Transactional
    public CommonResponse<Object> create(StudentDTO.CreateStudentReq req) {
        CommonResponse<Object> response = new CommonResponse<>();
//        if (studentRepository.existsByUniversityName(req.getUniversityName())) {
//            throw new HandleException(ErrorCode.DUPLICATE_USER);
//        }

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setUniversityId(req.getUniversityId());
        studentEntity.setStudentId(req.getStudentId());
        studentEntity.setStudentName(req.getStudentName());
        studentEntity.setStudentNameEn(req.getStudentNameEn());
        studentEntity.setStudentStatus(req.isStudentStatus());
        studentEntity.setCreateBy("System");
        studentEntity.setCreateDate(LocalDateTime.now());
        studentRepository.save(studentEntity);

        return response;
    }

    @Transactional
    public CommonResponse<StudentDTO.UpdateStudentRes> update(StudentDTO.UpdateStudentRes id) {
        CommonResponse<StudentDTO.UpdateStudentRes> response = new CommonResponse<>();
        Optional<StudentEntity> studentOptional = studentRepository.update(id.getId());
        StudentEntity student = studentOptional.get();

        student.setUniversityId(id.getUniversityId());
        student.setStudentId(id.getStudentId());
        student.setStudentStatus(id.isStudentStatus());
        student.setStudentName(id.getStudentName());
        student.setStudentNameEn(id.getStudentNameEn());
        student.setUpdateBy("System");
        student.setUpdateDate(LocalDateTime.now());

        studentRepository.save(student);

        return response;
    }

    @Transactional
    public CommonResponse<List<StudentDTO.FindAllStudentRes>> findAll() {
        CommonResponse<List<StudentDTO.FindAllStudentRes>> response = new CommonResponse<>();
        List<StudentDTO.FindAllStudentRes> dataReturn = new ArrayList<>();
        List<StudentEntity> getFindAllStudent = studentRepository.getAll();

        for (int i = 0; i < getFindAllStudent.size(); i++) {
            StudentDTO.FindAllStudentRes dataSet = new StudentDTO.FindAllStudentRes();
            dataSet.setId(getFindAllStudent.get(i).getId());
            dataSet.setUniversityId(getFindAllStudent.get(i).getUniversityId());
            dataSet.setStudentId(getFindAllStudent.get(i).getStudentId());
            dataSet.setStudentName(getFindAllStudent.get(i).getStudentName());
            dataSet.setStudentNameEn(getFindAllStudent.get(i).getStudentNameEn());
            dataSet.setStudentStatus(getFindAllStudent.get(i).isStudentStatus());
            dataSet.setDelete(getFindAllStudent.get(i).isDelete());

            dataReturn.add(dataSet);
        }

        response.setData(dataReturn);
        return response;
    }

    @Transactional
    public CommonResponse<StudentDTO.FindAllStudentRes> findById(Long id) {
        CommonResponse<StudentDTO.FindAllStudentRes> response = new CommonResponse<>();
        Optional<StudentEntity> userOptional = studentRepository.update(id);
        StudentEntity studentById = userOptional.get();
        StudentDTO.FindAllStudentRes data = new StudentDTO.FindAllStudentRes();
        data.setId(studentById.getId());
        data.setUniversityId(studentById.getUniversityId());
        data.setStudentId(studentById.getStudentId());
        data.setStudentName(studentById.getStudentName());
        data.setStudentNameEn(studentById.getStudentNameEn());
        data.setStudentStatus(studentById.isStudentStatus());
        data.setDelete(studentById.isDelete());

        response.setData(data);


        return response;
    }

    @Transactional
    public CommonResponse<StudentDTO.DeleteStudentReq> delete(StudentDTO.DeleteStudentReq id) {
        CommonResponse<StudentDTO.DeleteStudentReq> response = new CommonResponse<>();
        studentRepository.delete(id.getId());

        return response;
    }

}