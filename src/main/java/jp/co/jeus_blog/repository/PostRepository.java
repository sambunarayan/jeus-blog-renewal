package jp.co.jeus_blog.repository;

import jp.co.jeus_blog.repository.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Returns a list of Posts sorted in descending order.
     *
     * @return Returns a list of Posts sorted in descending order.
     */
    @Query("SELECT p FROM Post p ORDER BY p.id DESC")
    List<Post> findAllDesc();

    /**
     * Returns a list of posts sorted in descending order by the specified board name.
     *
     * @param boardName the specified board name
     * @return Returns a list of posts sorted in descending order by the specified board name.
     */
    @Query("SELECT p FROM Post p WHERE board_name = ?1 ORDER BY p.id DESC")
    List<Post> findByBoardNameDesc(String boardName);


    @Query("SELECT p FROM Post p WHERE p.id < ?1 ORDER BY p.id DESC")
    List<Post> findLatestPosts(Long id);

}