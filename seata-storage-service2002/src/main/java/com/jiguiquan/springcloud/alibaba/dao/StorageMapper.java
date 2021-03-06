package com.jiguiquan.springcloud.alibaba.dao;

import com.jiguiquan.springcloud.alibaba.domain.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}