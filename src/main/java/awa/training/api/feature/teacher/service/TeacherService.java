package awa.training.api.feature.teacher.service;

import awa.training.api.common.ErrorCode;
import awa.training.api.common.model.CommonResponse;
import awa.training.api.exception.HandleException;
import awa.training.api.feature.teacher.dto.TeacherDTO;
import awa.training.api.feature.teacher.entity.TeacherEntity;
import awa.training.api.feature.teacher.repository.TeacherRepository;

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
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Transactional
    public CommonResponse<Object> create(TeacherDTO.CreateTeacherReq req) {
        CommonResponse<Object> response = new CommonResponse<>();
//        if (TeacherRepository.existsByTeacherName(req.getTeacherName())) {
//            throw new HandleException(ErrorCode.DUPLICATE_USER);
//        }

        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setUniversityId(req.getUniversityId());
        teacherEntity.setTeacherName(req.getTeacherName());
        teacherEntity.setTeacherNameEn(req.getTeacherNameEn());
        teacherEntity.setTeacherCode(req.getTeacherCode());
        teacherEntity.setCreateBy("System");
        teacherEntity.setCreateDate(LocalDateTime.now());
        teacherRepository.save(teacherEntity);

        response.setMessage("Teacher created successfully");
        return response;
    }

    @Transactional
    public CommonResponse<TeacherDTO.UpdateTeacherRes> update(TeacherDTO.UpdateTeacherRes id) {
        CommonResponse<TeacherDTO.UpdateTeacherRes> response = new CommonResponse<>();
        Optional<TeacherEntity> teacherOptional = teacherRepository.findById(id.getId());
        TeacherEntity teacher = teacherOptional.get();

        teacher.setUniversityId(id.getUniversityId());
        teacher.setTeacherCode(id.getTeacherCode());
        teacher.setTeacherName(id.getTeacherName());
        teacher.setTeacherNameEn(id.getTeacherNameEn());
        teacher.setUpdateBy("System");
        teacher.setUpdateDate(LocalDateTime.now());

        teacherRepository.save(teacher);

        return response;
    }

    @Transactional
    public CommonResponse<List<TeacherDTO.FindAllTeacherRes>> findAll() {
        CommonResponse<List<TeacherDTO.FindAllTeacherRes>> response = new CommonResponse<>();
        List<TeacherDTO.FindAllTeacherRes> dataReturn = new ArrayList<>();
        List<TeacherEntity> getFindAllTeacher = teacherRepository.getAll();

        for (int i = 0; i < getFindAllTeacher.size(); i++) {
            TeacherDTO.FindAllTeacherRes dataSet = new TeacherDTO.FindAllTeacherRes();
            dataSet.setId(getFindAllTeacher.get(i).getId());
            dataSet.setUniversityId(getFindAllTeacher.get(i).getUniversityId());
            dataSet.setTeacherCode(getFindAllTeacher.get(i).getTeacherCode());
            dataSet.setTeacherName(getFindAllTeacher.get(i).getTeacherName());
            dataSet.setTeacherName(getFindAllTeacher.get(i).getTeacherNameEn());
            dataSet.setDelete(getFindAllTeacher.get(i).isDelete());

            dataReturn.add(dataSet);
        }

        response.setData(dataReturn);
        return response;
    }

    @Transactional
    public CommonResponse<TeacherDTO.FindAllTeacherRes> findById(Long id) {
        CommonResponse<TeacherDTO.FindAllTeacherRes> response = new CommonResponse<>();
        Optional<TeacherEntity> userOptional = teacherRepository.findById(id);
        TeacherEntity TeacherById = userOptional.get();
        TeacherDTO.FindAllTeacherRes data = new TeacherDTO.FindAllTeacherRes();
        data.setId(TeacherById.getId());
        data.setUniversityId(TeacherById.getUniversityId());
        data.setTeacherCode(TeacherById.getTeacherCode());
        data.setTeacherName(TeacherById.getTeacherName());
        data.setTeacherName(TeacherById.getTeacherNameEn());
        data.setDelete(TeacherById.isDelete());

        response.setData(data);


        return response;
    }

    @Transactional
    public CommonResponse<TeacherDTO.DeleteTeacherReq> delete(TeacherDTO.DeleteTeacherReq req) {
        CommonResponse<TeacherDTO.DeleteTeacherReq> response = new CommonResponse<>();
        TeacherEntity teacherEntity = teacherRepository.findById(req.getId()).orElseThrow(() -> new HandleException(ErrorCode.DATA_NOT_FOUND_IN_ID));
        teacherEntity.setDelete(true);
        teacherRepository.save(teacherEntity);
        return response;
    }

}