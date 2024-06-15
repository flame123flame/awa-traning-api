package awa.training.api.feature.teacher.repository;

import awa.training.api.feature.teacher.entity.TeacherEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {

    boolean existsByTeacherName(String teacherName);

    @Transactional
    Optional<TeacherEntity> findByTeacherNameEn(String teacherName);

    @Transactional
    @Query(value = "select * from teacher WHERE teacher_name_en = :teacher_name_en" , nativeQuery = true)
    Optional<TeacherEntity> findByTeacherNameEn(@Param("teacher_name_en") Long teacherNameEn);

    @Transactional
    @Query(value = "select * from teacher" , nativeQuery = true)
    List<TeacherEntity> getAll();

    @Transactional
    @Query(value = "select * from teacher WHERE id = :id" , nativeQuery = true)
    Optional<TeacherEntity> findById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM TeacherEntity u WHERE u.id = :id")
    void delete(@Param("id") Long id);
}