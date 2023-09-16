package com.onetaeklog.onetaeklog.repository;

import com.onetaeklog.onetaeklog.domain.Post;
import com.onetaeklog.onetaeklog.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> getList(PostSearch postSearch);
}
