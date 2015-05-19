package zhang.fan.vod.dao.daoimpl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import zhang.fan.vod.dao.BaseDao;

public class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {
	private Class<T> theClass;

	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		theClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T get(PK id) {
		Session sess = sessionFactory.getCurrentSession();

		return (T) sess.get(theClass, id);
	}

	@Override
	public boolean add(T object) {
		System.out.println("before try add");
		try {
			System.out.println("add in dao");
			Session sess = sessionFactory.getCurrentSession();
			sess.save(object);
			System.out.println("try add over!");
			return true;
		} catch (Exception e) {
			System.out.println("exception occur!");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void update(T object) {
		Session sess = sessionFactory.getCurrentSession();
		// Transaction tx = sess.beginTransaction();
		sess.update(object);

	}

	@Override
	public void delete(PK id) {
		Session sess = sessionFactory.getCurrentSession();
		// Transaction tx = sess.beginTransaction();
		T object = (T) sess.get(theClass, id);
		sess.delete(object);
	}

	@Override
	public List<T> findAll() {
		Session sess = sessionFactory.getCurrentSession();
		// Transaction tx = sess.beginTransaction();
		System.out.println("from " + theClass.getSimpleName());
		Query q = sess.createQuery("from " + theClass.getName());
		// tx.commit();
		return q.list();
	}

	@Override
	public List<T> findByProperty(String propertyName, Object value) {
		Session sess = sessionFactory.getCurrentSession();
		String hql = "from"+theClass.getSimpleName()+"as c where c."+propertyName+"=:value";
	
		Query query = sess.createQuery(hql);
		query.setParameter("value", value);
		return query.list();
	}

	@Override
	public List<T> findByPropertyWithMulitValue(String PropertyName,
			List<Object> values) {
		Session sess = sessionFactory.getCurrentSession();
		StringBuffer hql = new StringBuffer("from"+theClass.getSimpleName()+"as c where 1=2 ");
		if(values!=null&&values.size()>0){
			for(Object o :values){
				hql.append(" or c.").append(PropertyName).append("='").append(o.toString()).append("'");
			}
			Query query = sess.createQuery(hql.toString());
			
			return query.list();
		}
		else{
			return null;
		}
		
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
