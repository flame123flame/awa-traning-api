package awa.training.api.feature.users.repository;

import awa.training.api.feature.users.entity.UsersEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Transactional
    Optional<UsersEntity> findByUsername(String username);

    boolean existsByUsername(String username);

}
