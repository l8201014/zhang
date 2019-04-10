package com.zhang.dao;

import com.zhang.model.Rresource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RresourceDAO extends BesaDAO{

    Rresource getResourceByUrl(String url);

    Rresource getResourceById(Integer id);

    List<Rresource> getNoUrlList();

    List<Rresource> getListByKeyWord(Map<String,Object> map);
}
