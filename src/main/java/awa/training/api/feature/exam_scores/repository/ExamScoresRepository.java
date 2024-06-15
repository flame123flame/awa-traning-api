package awa.training.api.feature.exam_scores.repository;

import awa.training.api.feature.exam_scores.entity.ExamScoresEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamScoresRepository extends JpaRepository<ExamScoresEntity, Long> {
    @Transactional
    Optional<ExamScoresEntity> findByExamScoresName(String examScoresName);

    boolean existsByExamScoresName(String examScoresName);

    @Transactional
    @Query(value = "select * from exam_scores" , nativeQuery = true)
    List<ExamScoresEntity> getAll();

    @Transactional
    @Query(value = "select * from exam_scores WHERE id = :id" , nativeQuery = true)
    Optional<ExamScoresEntity> findById(@Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ExamScoresEntity u WHERE u.id = :id")
    void delete(@Param("id") Long id);
}

