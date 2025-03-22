package com.nano.cat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nano.cat.data.po.Chat;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeng
 * @version 0.0.1
 * @date 2025/3/20 00:11
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

}