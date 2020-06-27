package com.sunan.literature.dao;

import com.sunan.literature.entity.SectionUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SectionUserDao {
    List<SectionUser> selectSectionUserList();

    SectionUser selectSectionUserByUserId(Integer userId);

    SectionUser selectSectionUserBySectionId(Integer sectionId);

    Integer insertSectionUser(SectionUser sectionUser);

    Integer deleteSectionUserByUserId(Integer userId);
}
