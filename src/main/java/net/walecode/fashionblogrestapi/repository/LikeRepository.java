package net.walecode.fashionblogrestapi.repository;

import net.walecode.fashionblogrestapi.entity.Comment;
import net.walecode.fashionblogrestapi.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {

    List<Like> findByPostId(long postId);
}
