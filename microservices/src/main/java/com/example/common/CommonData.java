package com.example.common;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface CommonData {
	public SessionFactory getSessionFactory();

	public <T> T get(Class dataModel, Serializable id);

	public Session getSession();
	public String getTableName(Class dataModel);

	public String getNameId(Class dataModel);

	public String columnName(String input);

	public void saveOrUpdate(Object dataObject);

	public void save(Object dataObject);

	public void delete(Object entity);
	public void deleteByIds(List<Long> arrId, Class className, String idColumn);

	public List<String> getLstUserSequence() throws Exception;

	public void deleteByIds(Long[] arrId, Class className, String idColumn);
	public void deleteById(Long id, Class className, String idColumn);

	public <T> List<T> getAll(Class<T> tableName, String orderColumn);
	public <T> Page<T> listAll(Class dataModel, Pageable pageable);
	public <T> List<T> findByProperty(Class<T> tableName, String propertyName, Object value, String orderClause);
	public <T> List<T> findByProperties(Class<T> tableName, Object... pairs);

	public <T> List<T> findByIds(Class<T> tableName, String idColumn, String ids, String orderColumn);

	public void flushSession();

	public void clear();
	public Query createQuery(String hql);

	public SQLQuery createSQLQuery(String sql);
	public CallableStatement prepareCall(String sql);

	public Long getSequence(String sequenceName);

	public Long getAutoIncrement(String tableName);

	public boolean duplicate(Class className, String idColumn, Long idValue, String codeColumn, String codeValue);
	public boolean hasConstraint(Class className, String idColumn, Long idValue);
	public String getOrderByClause(HttpServletRequest req, String defaultColumn, String... whitelist);

	public void setResultTransformer(SQLQuery query, Class obj);

	public List<String> getReturnAliasColumns(SQLQuery query);

	public void commitTransaction();


    public <T> List<T> list(String nativeQuery, List<Object> paramList, Class obj);
    /**
     * 
     * @param list
     * @param obj
     * @return
     */
    public <T> List<T> list(String nativeQuery, Map<String, Object> mapParams, Class obj);
    /**
     * 
     * @param list
     * @param obj
     * @return
     */
    public <T> T get(String nativeQuery, List<Object> paramList, Class obj);
    /**
     * 
     * @param list
     * @param obj
     * @return
     */
    public <T> T get(String nativeQuery, Map<String, Object> mapParams, Class obj);
    
}
