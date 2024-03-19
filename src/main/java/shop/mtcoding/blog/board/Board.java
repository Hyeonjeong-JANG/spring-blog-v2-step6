package shop.mtcoding.blog.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    //@JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // db -> user_id

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;
    
    // 조회를 두 번 해서 여기에 담으면 되는데 얘가 필드가 될 수는 없다. 빌더에도 들어갈 수 없음
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // Entity 객체의 변수명 -> board라는 필드명이 포린키의 주인이다.
    private List<Reply> replies = new ArrayList<>(); // 컬랙션이 테이블의 빌드가 될 수 없어서 오류가 남.
    // 초기화를 미리 해놓지 않으면 댓글 포문 돌릴 때 오류가 나서 초기화를 해놓았음. null이 아니라 []이어야함.
    /**
     * ManyToOne은 EAGER, OneToMany는 LAZY가 디폴트 전략
     */
    @Transient // 테이블 생성이 안됨
    private boolean isOwner;


    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}
