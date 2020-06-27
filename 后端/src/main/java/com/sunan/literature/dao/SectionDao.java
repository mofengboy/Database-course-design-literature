package com.sunan.literature.dao;

import com.sunan.literature.entity.Section;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SectionDao {
    List<Section> selectSectionList();

    Integer insertSection(Section section);

    Integer deleteSectionById(Integer id);

    Integer updateSectionById(Section section);
}
