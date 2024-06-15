package awa.training.api.feature.course.service;

import awa.training.api.common.ErrorCode;
import awa.training.api.common.model.CommonResponse;
import awa.training.api.exception.HandleException;
import awa.training.api.feature.course.dto.CourseDTO;
import awa.training.api.feature.course.entity.CourseEntity;
import awa.training.api.feature.course.repository.CourseRepository;
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
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public CommonResponse<Object> create(CourseDTO.CreateCourseReq req) {
        CommonResponse<Object> response = new CommonResponse<>();
        if (courseRepository.existsByCourseName(req.getCourseName())) {
            throw new HandleException(ErrorCode.DUPLICATE_USER);
        }

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setTeacherId(req.getTeacherId());
        courseEntity.setCourseName(req.getCourseName());
        courseEntity.setCourseNameEn(req.getCourseNameEn());
        courseEntity.setCourseShortName(req.getCourseShortName());
        courseEntity.setCreateBy(req.getCreateBy());
        courseEntity.setCreateDate(LocalDateTime.now());
        courseRepository.save(courseEntity);

        response.setMessage("Course created successfully");
        return response;
    }

    @Transactional
    public CommonResponse<CourseDTO.UpdateCourseRes> update(CourseDTO.UpdateCourseRes req) {
        CommonResponse<CourseDTO.UpdateCourseRes> response = new CommonResponse<>();
        Optional<CourseEntity> courseOptional = courseRepository.findById(req.getId());
        CourseEntity courseEntity = courseOptional.get();

        courseEntity.setTeacherId(req.getTeacherId());
        courseEntity.setCourseName(req.getCourseName());
        courseEntity.setCourseNameEn(req.getCourseNameEn());
        courseEntity.setCourseShortName(req.getCourseShortName());
        courseEntity.setUpdateBy("System");
        courseEntity.setUpdateDate(LocalDateTime.now());

        courseRepository.save(courseEntity);

        return response;
    }

    @Transactional
    public CommonResponse<List<CourseDTO.FindAllCourseRes>> findAll() {
        CommonResponse<List<CourseDTO.FindAllCourseRes>> response = new CommonResponse<>();
        List<CourseDTO.FindAllCourseRes> dataReturn = new ArrayList<>();
        List<CourseEntity> getFindAllCourses = courseRepository.findAll();

        for (int i = 0; i < getFindAllCourses.size(); i++) {
            CourseDTO.FindAllCourseRes dataSet = new CourseDTO.FindAllCourseRes();
            dataSet.setId(getFindAllCourses.get(i).getId());
            dataSet.setTeacherId(getFindAllCourses.get(i).getTeacherId());
            dataSet.setCourseName(getFindAllCourses.get(i).getCourseName());
            dataSet.setCourseNameEn(getFindAllCourses.get(i).getCourseNameEn());
            dataSet.setCourseShortName(getFindAllCourses.get(i).getCourseShortName());
            dataSet.setDelete(getFindAllCourses.get(i).isDelete());

            dataReturn.add(dataSet);
        }

        response.setData(dataReturn);
        return response;
    }

    @Transactional
    public CommonResponse<CourseDTO.FindAllCourseRes> findById(Long id) {
        CommonResponse<CourseDTO.FindAllCourseRes> response = new CommonResponse<>();
        Optional<CourseEntity> courseOptional = courseRepository.findById(id);
        CourseEntity courseEntity = courseOptional.get();

        CourseDTO.FindAllCourseRes data = new CourseDTO.FindAllCourseRes();
        data.setId(courseEntity.getId());
        data.setTeacherId(courseEntity.getTeacherId());
        data.setCourseName(courseEntity.getCourseName());
        data.setCourseNameEn(courseEntity.getCourseNameEn());
        data.setCourseShortName(courseEntity.getCourseShortName());
        data.setDelete(courseEntity.isDelete());

        response.setData(data);

        return response;
    }

    @Transactional
    public CommonResponse<CourseDTO.DeleteCourseReq> delete(CourseDTO.DeleteCourseReq req) {
        CommonResponse<CourseDTO.DeleteCourseReq> response = new CommonResponse<>();
        courseRepository.delete(req.getId());

        return response;
    }
}
