package com.KunJinKao.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotArticleVo {
    //实际返回的文章信息,只需要有这几个信息就行了
    private Long id;
    private String title;

    private Long viewCount;
}
