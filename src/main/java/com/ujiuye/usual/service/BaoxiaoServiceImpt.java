package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.Baoxiao;
import com.ujiuye.usual.bean.BaoxiaoExample;
import com.ujiuye.usual.mapper.BaoxiaoMapper;
import org.openxmlformats.schemas.drawingml.x2006.main.STAdjCoordinate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaoxiaoServiceImpt implements BaoxiaoService {
    @Autowired
    private BaoxiaoMapper baoxiaoMapper;
    public void saveInfo(Baoxiao baoxiao) {
        String bxid = UUID.randomUUID().toString().replaceAll("-","");
        baoxiao.setBxid(bxid);
        baoxiao.setBxstatus(0);
        baoxiaoMapper.insert(baoxiao);

    }


    public PageInfo<Baoxiao> getList(Integer eid, Integer pageNum, Map<String, Object> map) {
        BaoxiaoExample example = new BaoxiaoExample();
        BaoxiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);
        Map<String,String> mybatisMap = mybatisMap(map);
        String status = mybatisMap.get("status");

        String keyword = mybatisMap.get("keyword");
        if (status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
        if (keyword != null && keyword != ""){
            criteria.andBxremarkLike(keyword);
        }


        PageHelper.startPage(pageNum,3);
        List<Baoxiao> baoxiaos = baoxiaoMapper.selectByExample(example);
        PageInfo<Baoxiao> page = new PageInfo<Baoxiao>(baoxiaos,5);
        return page;
    }

    private Map<String, String> mybatisMap(Map<String, Object> map) {
        Map<String,String> map1 = new HashMap<String, String>();
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        for (Map.Entry<String, Object> entry : entrySet) {
            String key = entry.getKey();
            String value = (String) entry.getValue();
            if (key.contains("like")){
                key=key.substring(key.indexOf("_")+1);
                value="%"+value+"%";
            }
            map1.put(key,value);
        }
        return map1;
    }
}
