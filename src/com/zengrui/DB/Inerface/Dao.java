package com.zengrui.DB.Inerface;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Zeng Rui on 2017/8/21.
 */
public interface Dao {
    public boolean insert(Model model)throws Exception;
    public boolean update(Model model,String attribute,String condition)throws Exception;
    public boolean delete(String attribute,String condition)throws Exception;
    public Model queryById(Model model) throws Exception;
    public List queryAll() throws Exception;
    public void closeConn() throws Exception;
}
