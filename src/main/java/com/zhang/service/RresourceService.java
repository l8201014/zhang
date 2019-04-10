package com.zhang.service;

import com.zhang.model.Rkeyword;
import com.zhang.model.Rresource;

import java.util.List;
import java.util.Map;

public interface RresourceService {
    public void insert(Rresource resource);

    public Rresource getResourceByUrl(String listUrl);

    public void update(Rresource resource);

    public Rresource getResourceById(Integer id);

    List<Rresource> getNoUrlList();

    List<Rresource> getListByKeyWord(Map<String,Object> map);

}
