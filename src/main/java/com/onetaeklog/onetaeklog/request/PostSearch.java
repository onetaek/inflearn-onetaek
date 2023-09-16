package com.onetaeklog.onetaeklog.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    @Builder.Default//defalut 를 사용하려면 생성자가 아니라 클래스 level에 사용해야한다.
    private Integer page = 1;

    @Builder.Default
    private Integer size = 10;

    public long getOffset() {
        return (long) (Math.max(1, page) - 1) * Math.min(size , MAX_SIZE);
    }

}
