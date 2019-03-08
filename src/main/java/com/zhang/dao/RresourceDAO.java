package com.zhang.dao;

import com.zhang.model.Rresource;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RresourceDAO extends BesaDAO{

    Rresource getResourceByUrl(String url);
}
