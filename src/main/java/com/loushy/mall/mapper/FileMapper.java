package com.loushy.mall.mapper;

import com.loushy.mall.model.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FileMapper {

    List<File> selectFile();

    int addFile(@Param("file") File file);

    File selectFileById(@Param("id") Long id);
}
