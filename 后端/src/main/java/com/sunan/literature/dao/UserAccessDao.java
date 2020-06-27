package com.sunan.literature.dao;

import com.sunan.literature.entity.UserAccess;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAccessDao {
    List<UserAccess> selectUserAccessList();

    UserAccess selectUserAccessByUserId(Integer userId);

    UserAccess selectUserAccessByAccessId(Integer accessId);

    Integer insertAccessUser(AccessDao userAccess);

    Integer deleteAccessUserByUserId(Integer userId);
}
