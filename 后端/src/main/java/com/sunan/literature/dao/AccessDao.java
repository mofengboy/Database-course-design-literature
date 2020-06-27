package com.sunan.literature.dao;

import com.sunan.literature.entity.Access;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccessDao {

    List<Access> selectAccessList();

    Integer updateAccessById(Access access);

    Integer deleteAccessById(Integer id);

    Integer insertAccess(Access access);

}
