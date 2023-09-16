package com.onetaeklog.onetaeklog.repository;

import com.onetaeklog.onetaeklog.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom{
}
