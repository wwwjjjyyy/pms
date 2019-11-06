package com.ujiuye.archives.service;

import com.ujiuye.archives.bean.Archives;
import com.ujiuye.archives.mapper.ArchivesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchivesServiceImpt implements ArchivesService {
    @Autowired
    private ArchivesMapper archivesMapper;
    public List<Archives> jsonList() {

        return archivesMapper.jsonList();
    }

    /*public Archives info() {
        return archivesMapper.info();
    }*/
}
