package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.bean.PositionExample;
import com.ujiuye.sys.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpt implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    public List<Position> getList() {
        PositionExample example = new PositionExample();
        return positionMapper.selectByExample(example);
    }
}
