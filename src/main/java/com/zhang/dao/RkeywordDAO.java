package com.zhang.dao;

import com.zhang.model.Rkeyword;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RkeywordDAO extends BesaDAO{

    public Rkeyword getRkeywordByKey(String keyword);

    public List<Rkeyword> getHotKeyword();
}
