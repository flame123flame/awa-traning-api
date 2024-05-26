package awa.training.api.feature.users.repository;

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
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

    @Transactional
    Optional<UsersEntity> findByUsername(String username);

    boolean existsByUsername(String username);
    @Transactional
    @Query(value = "select * from users" , nativeQuery = true)
    List<UsersEntity> getAll();

    @Transactional
    @Query(value = "select id, nick_name, full_name, username, avatar from users" , nativeQuery = true)
    Optional<UsersEntity> findById(Long id);


    @Transactional
    @Query(value = "DELETE FROM public.users WHERE id = :id", nativeQuery = true)
    Optional<UsersEntity> deleteUser(@Param("id") Long id);

    @Transactional
    @Query(value = "UPDATE public.users SET nick_name = :nickName, full_name = :fullName, username = :username, password = :password, created_at = :createdAt, avatar = :avatar WHERE id = :id", nativeQuery = true)
    void updateUser(@Param("id") Long id, @Param("nickName") String nickName, @Param("fullName") String fullName, @Param("username") String username, @Param("password") String password, @Param("createdAt") LocalDateTime createdAt, @Param("avatar") String avatar);

}
