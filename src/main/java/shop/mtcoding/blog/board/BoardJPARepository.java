package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    // 선생님의 추천!! 그냥 레이지로딩해서 써라!! 그럼 통신 한 번에 조회 12번으로 끝남. 아래의 쿼리는 28번 조회해야 함.
    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id);

    @Query("""
            select b 
            from Board b 
            join fetch b.user u 
            left join b.replies r 
            where b.id = :id
            """)
    Optional<Board> findByIdJoinUserAndReplies(@Param("id") int id);

}
