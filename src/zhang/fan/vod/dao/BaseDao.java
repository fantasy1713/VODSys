package zhang.fan.vod.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, PK extends Serializable> {
	public T get(PK id);//

	public boolean add(T object);//

	public void update(T object);//

	public void delete(PK id);//

	public List<T> findAll();//
	
	public List<T> findByProperty(String propertyName, Object value);//根据属性查找
	
	public List<T> findByPropertyWithMulitValue(String PropertyName, List<Object> values);

}