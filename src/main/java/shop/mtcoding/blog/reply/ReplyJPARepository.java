package shop.mtcoding.blog.reply;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public interface ReplyJPARepository extends JpaRepository<Reply, Integer> {


}
