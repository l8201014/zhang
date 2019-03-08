package com.zhang.service.Impl;

import com.zhang.dao.RresourceDAO;
import com.zhang.model.Rresource;
import com.zhang.service.RresourceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ：zhangwn
 * @date ：Created in 2019/3/7 17:12
 * @modified By：
 */
@Service("rresourceService")
public class RresourceServiceImpl implements RresourceService {

    @Resource
    private RresourceDAO resourceDAO;

    @Override
    public void insert(Rresource resource) {
        resourceDAO.insert(resource);
    }

    @Override
    public Rresource getResourceByUrl(String listUrl) {
        return resourceDAO.getResourceByUrl(listUrl);
    }

    @Override
    public void update(Rresource resource) {
        resourceDAO.update(resource);
    }
}
