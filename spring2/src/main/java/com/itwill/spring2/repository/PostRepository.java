package com.itwill.spring2.repository; // controller

import java.util.List;

import com.itwill.spring2.domain.Post;

// application-context.xml에서 scan하는 패키지에 있기 때문에
// 인터페이스를 구현하는 클래스가 MyBatis에 의해서 자동으로 만들어짐.
// post-mapper.xml 파일에서 설정된 id와 메서드 이름이 같으면,
// 해당 id의 SQL 문장을 실행하는 구현 메서드를 만들어줌.

// 위치: src/main/resources/mappers/post-mapper.xml
public interface PostRepository {
    
    // 메서드의 nameSpace, 메서드 전체 이름: com.itwill.spring2.repository.PostRepository.insert
    int insert(Post post); //메서드를 선언할 때 메서드의 이름은 post-mapper.xml에서의 id를 그대로 사용하기.

    List<Post> selectOrderByIdDesc();
    
    Post selectById(long id);
    
    int updateTitleAndContent(Post post);
    
    int deleteById(long id);
}
