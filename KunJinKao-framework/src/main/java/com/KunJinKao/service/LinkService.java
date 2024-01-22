package com.KunJinKao.service;
import com.KunJinKao.domain.ResponseResult;
import com.KunJinKao.domain.entity.Link;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2024-01-22 10:02:06
 */
public interface LinkService extends IService<Link> {
    ResponseResult getAllLink();
}

