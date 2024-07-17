package awa.training.api.feature.enrollment.repository;

import awa.training.api.feature.enrollment.entity.EnrollmentEntity;
import awa.training.api.feature.teacher.entity.TeacherEntity;
import awa.training.api.feature.users.entity.UsersEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {

//    @Transactional
//    Optional<EnrollmentEntity> findByEnrollmentName(String enrollmentName);
//    boolean existsByEnrollmentName(String enrollmentName);
    @Transactional
    @Query(value = "select * from enrollment WHERE student_id = :student_id" , nativeQuery = true)
    Optional<EnrollmentEntity> findByStudentId(@Param("student_id") Long studentId);

    @Transactional
    @Query(value = "select * from enrollment" , nativeQuery = true)
    List<EnrollmentEntity> getAll();

    @Transactional
    @Query(value = "select * from enrollment WHERE id = :id" , nativeQuery = true)
    Optional<EnrollmentEntity> findById(@Param("id") Long req);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM EnrollmentEntity u WHERE u.id = :id")
    void delete(@Param("id") Long id);

}
