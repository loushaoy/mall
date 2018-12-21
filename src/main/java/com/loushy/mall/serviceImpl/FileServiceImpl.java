package com.loushy.mall.serviceImpl;

import com.loushy.mall.mapper.FileMapper;
import com.loushy.mall.model.File;
import com.loushy.mall.service.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileMapper fileMapper;

    @Override
    public int addFile(@Param("file") File file){
        return fileMapper.addFile(file);
    };

    @Override
    public List<File> selectFile(){
        return fileMapper.selectFile();
    }

    @Override
    public File selectFileById(Long id){
        return fileMapper.selectFileById(id);
    }
}
