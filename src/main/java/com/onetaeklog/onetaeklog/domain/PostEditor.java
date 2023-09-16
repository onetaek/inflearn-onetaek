package com.onetaeklog.onetaeklog.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * 수정을 할 필드를 따로 모아둔 클래스를 생성하여 관리
 */
@Getter
public class PostEditor {

    private final String title;
    private final String content;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
