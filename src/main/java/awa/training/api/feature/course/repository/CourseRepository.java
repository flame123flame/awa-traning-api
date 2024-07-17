package awa.training.api.feature.course.repository;

import awa.training.api.feature.course.entity.CourseEntity;
import awa.training.api.feature.teacher.entity.TeacherEntity;
import awa.training.api.feature.university.entity.UniversityEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
//    Optional<CourseEntity> findByCourseName(String courseName);
//    boolean existsByCourseName(String courseName);

    @Transactional
    @Query(value = "select * from course WHERE courseNameEn = :courseNameEn" , nativeQuery = true)
    Optional<CourseEntity> findByCourseNameEn(@Param("courseNameEn") Long courseNameEn);

    @Transactional
    @Query(value = "select * from course" , nativeQuery = true)
    List<CourseEntity> getAll();

    @Transactional
    @Query(value = "select * from course WHERE id = :id" , nativeQuery = true)
    Optional<CourseEntity> update(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CourseEntity u WHERE u.id = :id")
    void delete(@Param("id") Long id);
}
