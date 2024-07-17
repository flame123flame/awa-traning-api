//package awa.training.api.feature.student.repository;
//
//public interface StudentRepository {
//}


package awa.training.api.feature.student.repository;

import awa.training.api.feature.student.entity.StudentEntity;
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
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

//    @Transactional
//    Optional<StudentEntity> findByUsername(String universityName);
//    boolean existsByStudentName(String studentName);
@Transactional
@Query(value = "select * from student WHERE student_id = :student_id" , nativeQuery = true)
Optional<StudentEntity> findByStudentName(@Param("student_id") Long studentId);

    @Transactional
    @Query(value = "select * from student" , nativeQuery = true)
    List<StudentEntity> getAll();

    @Transactional
    @Query(value = "select * from student WHERE id = :id" , nativeQuery = true)
    Optional<StudentEntity> update(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM StudentEntity u WHERE u.id = :id")
    void delete(@Param("id") Long id);
}