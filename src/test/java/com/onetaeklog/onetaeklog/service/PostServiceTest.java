package com.onetaeklog.onetaeklog.service;

import com.onetaeklog.onetaeklog.domain.Post;
import com.onetaeklog.onetaeklog.domain.PostEditor;
import com.onetaeklog.onetaeklog.exception.PostNotFound;
import com.onetaeklog.onetaeklog.repository.PostRepository;
import com.onetaeklog.onetaeklog.request.PostCreate;
import com.onetaeklog.onetaeklog.request.PostEdit;
import com.onetaeklog.onetaeklog.request.PostSearch;
import com.onetaeklog.onetaeklog.response.PostResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("글 작성")
    void test1() {
        //given
        PostCreate postCreate = PostCreate.builder()
                .title("제목입니다.")
                .content("내용입니다.")
                .build();

        //when
        postService.write(postCreate);

        //then
        assertEquals(1L, postRepository.count());
        Post post = postRepository.findAll().get(0);
        assertEquals("제목입니다.", post.getTitle());
        assertEquals("내용입니다.", post.getContent());
    }

    @Test
    @DisplayName("글 1개 조회")
    void test2() {
        //given
        Post requestPost = Post.builder()
                .title("hansol")
                .content("coever")
                .build();
        postRepository.save(requestPost);

        //when
        PostResponse response = postService.get(requestPost.getId());

        //then
        assertNotNull(response);
        assertEquals(1L, postRepository.count());
        assertEquals("hansol", response.getTitle());
        assertEquals("coever", response.getContent());
    }

    @Test
    @DisplayName("글 여러개 조회")
    void test3() {
        //given
        List<Post> requestPosts = IntStream.range(0, 20)
                .mapToObj(i -> Post.builder()
                        .title("hansol" + i)
                        .content("coever" + i)
                        .build())
                .collect(Collectors.toList());

        postRepository.saveAll(requestPosts);

        PostSearch postSearch = PostSearch.builder()
                .page(1)
                .build();

        //when
        List<PostResponse> posts = postService.getList(postSearch);

        //then
        assertEquals(10L, posts.size());
        assertEquals("hansol19", posts.get(0).getTitle());
        assertEquals("hansol18", posts.get(1).getTitle());
    }


    @Test
    @DisplayName("글 제목 수정")
    void test4() {
        //given
        Post post = Post.builder()
                .title("mes")
                .content("스마트팩토리")
                .build();

        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("erp")
                .content("스마트팩토리")
                .build();

        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글이 존재하지 않습니다. id=" + post.getId()));
        assertEquals("erp", changePost.getTitle());
    }

    @Test
    @DisplayName("글 내용 수정")
    void test5() {
        //given
        Post post = Post.builder()
                .title("mes")
                .content("스마트팩토리")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("mes")
                .content("자동화공장")
                .build();

        //when
        postService.edit(post.getId(), postEdit);

        //then
        Post changePost = postRepository.findById(post.getId())
                .orElseThrow(() -> new RuntimeException("글지 존재하지 않습니다. id=" + post.getId()));
        assertEquals("자동화공장", changePost.getContent());
    }

    @Test
    @DisplayName("게시글 삭제")
    void test6() {
        //given
        Post post = Post.builder()
                .title("mes")
                .content("스마트팩토리")
                .build();
        postRepository.save(post);

        //when
        postService.delete(post.getId());

        //then
        assertEquals(0, postRepository.count());
    }

    @Test
    @DisplayName("글 1개 조회 - 존재하지 않는글")
    void test7() {
        //given
        Post post = Post.builder()
                .title("mes")
                .content("스마트팩토리")
                .build();
        postRepository.save(post);

        //expected
        assertThrows(PostNotFound.class, () -> {
            postService.get(post.getId() + 1L);
        });
    }

    @Test
    @DisplayName("게시글 삭제 - 존재하지 않는 글")
    void test8() {
        //given
        Post post = Post.builder()
                .title("mes")
                .content("스마트팩토리")
                .build();
        postRepository.save(post);

        //expected
        assertThrows(PostNotFound.class, () ->{
            postService.delete(post.getId() + 1L);
        });
    }

    @Test
    @DisplayName("글 내용 수정 - 존재하지 않는 글")
    void test9() {
        //given
        Post post = Post.builder()
                .title("mes")
                .content("스마트팩토리")
                .build();
        postRepository.save(post);

        PostEdit postEdit = PostEdit.builder()
                .title("mes")
                .content("자동화공장")
                .build();

        //expected
        assertThrows(PostNotFound.class,() -> {
            postService.edit(post.getId() + 1L,postEdit);
        });
    }
}