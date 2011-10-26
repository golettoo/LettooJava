package cn.lettoo.spring.jdbc;

import java.util.List;

public interface IDao<T> {

    int insert(T object);
    
    int delete(T object);
    
    int update(T object);    
       
    T select(Object id);
    
    List<T> selectList(String condition);
    
}
