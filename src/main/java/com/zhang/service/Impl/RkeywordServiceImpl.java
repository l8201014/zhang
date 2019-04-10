package com.zhang.service.Impl;

import com.zhang.dao.RkeywordDAO;
import com.zhang.model.Rkeyword;
import com.zhang.service.RkeywordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/11 13:59
 * @modified By：
 */
@Service
public class RkeywordServiceImpl implements RkeywordService {

    @Resource
    private RkeywordDAO rkeywordDAO;

    @Override
    public void statisticsKeyword(String keyword) {
        Rkeyword rkeyword = rkeywordDAO.getRkeywordByKey(keyword);
        if(null != rkeyword){
            rkeyword.setSearchNum(rkeyword.getSearchNum() + 1);
            rkeywordDAO.update(rkeyword);
        }else{
            Rkeyword inrkeyword = new Rkeyword();
            inrkeyword.setKeyword(keyword);
            inrkeyword.setSearchNum(1);
            rkeywordDAO.insert(inrkeyword);
        }
    }

    @Override
    public List<Rkeyword> getHotKeyword() {
        return rkeywordDAO.getHotKeyword();
    }

    @Override
    public List<Rkeyword> getKeyWordByDate(Map<String, Object> map) {
        return rkeywordDAO.getKeyWordByDate(map);
    }
}
