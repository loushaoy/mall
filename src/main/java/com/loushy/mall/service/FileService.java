package com.loushy.mall.service;

import com.loushy.mall.model.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileService {

    List<File> selectFile();

    int addFile(@Param("file") File file);

    File selectFileById(Long id);
}
