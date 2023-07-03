package com.joje.postmelon.repository;

import com.joje.postmelon.model.entity.PostmelonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostmelonRepository extends JpaRepository<PostmelonEntity, Long> {
    long countById(Long id);
}
