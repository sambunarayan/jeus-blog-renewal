package jp.co.jeus_blog.repository;

import jp.co.jeus_blog.repository.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Board repository
 */
public interface BoardRepository extends JpaRepository<Board, Long> {
}
