package com.example.finalprojectsujin221220.service;


import com.example.finalprojectsujin221220.domain.entity.LikeEntity;
import com.example.finalprojectsujin221220.domain.entity.Post;
import com.example.finalprojectsujin221220.domain.entity.User;
import com.example.finalprojectsujin221220.exception.ApplicationException;
import com.example.finalprojectsujin221220.exception.ErrorCode;
import com.example.finalprojectsujin221220.repository.LikeRepository;
import com.example.finalprojectsujin221220.repository.PostRepository;
import com.example.finalprojectsujin221220.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeService {

    private final UserRepository ur;
    private final PostRepository pr;
    private final LikeRepository lr;
    private final AlarmService as;

    //[중복 로직] user 존재 확인 + 가져오기
    public User validateUser(Authentication authentication) {
        return ur.findByUserName(authentication.getName())
                .orElseThrow(() -> new ApplicationException(ErrorCode.USERNAME_NOT_FOUND, ErrorCode.USERNAME_NOT_FOUND.getMessage()));
    }

    //[중복 로직] post 존재 확인 + 가져오기
    public Post validatePost(Long id) {
        return pr.findById(id)
                .orElseThrow(() -> new ApplicationException(ErrorCode.POST_NOT_FOUND, ErrorCode.POST_NOT_FOUND.getMessage()));
    }

    //좋아요 누르기/취소
    public String pushLike(Long postId, Authentication authentication) {

        User user = validateUser(authentication);
        Post post = validatePost(postId);
        LikeEntity likeEntity = lr.findByUserAndPost(user, post);
        String message;
        if(likeEntity==null) {
            likeEntity = LikeEntity.builder()
                    .user(user)
                    .post(post)
                    .build();
            lr.save(likeEntity);
            if(post.getUser() != user) {
                as.newAlarm(post.getUser(), user.getUserId(), postId, likeEntity.getCreatedAt(), "NEW_LIKE_ON_POST", "new like!");
            }
            message = "좋아요를 눌렀습니다.";
        } else {
            lr.delete(likeEntity);
            message = "좋아요를 취소했습니다.";
        }

        return message;
    }

    //좋아요 갯수 조회
    public int countLike(Long postId) {
        Post post = validatePost(postId);
        return lr.findByPost(post).size();
    }
}
