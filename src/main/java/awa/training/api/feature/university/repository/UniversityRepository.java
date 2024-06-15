
package awa.training.api.feature.university.repository;

import awa.training.api.feature.university.entity.UniversityEntity;
import awa.training.api.feature.users.entity.UsersEntity;
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
public interface UniversityRepository extends JpaRepository<UniversityEntity, Long> {

    @Transactional
    Optional<UniversityEntity> findByUniversityName(String universityName);
    boolean existsByUniversityName(String universityName);

    @Transactional
    @Query(value = "select * from university" , nativeQuery = true)
    List<UniversityEntity> getAll();

    @Transactional
    @Query(value = "select * from university WHERE id = :id" , nativeQuery = true)
    Optional<UniversityEntity> update(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UniversityEntity u WHERE u.id = :id")
    void delete(@Param("id") Long id);
}