package com.colin.util.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * jdbctemplate辅助类。 功能说明：获取jdbctemplate
 *
 */
@Component
public class JdbcTemplateHandler {
    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbctemplate;

    public JdbcTemplate getJdbcTemplate() {
        if (this.jdbctemplate == null) {
            this.jdbctemplate = new JdbcTemplate(this.dataSource);
        }
        return this.jdbctemplate;
    }

    /**
     * 自定义通用分页方法
     *
     * @author wangdongting
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Transactional(readOnly = true)
    public <T> Page<T> pageHandlerBySqlStr(String sql, int pageNo, int pageSize, final Object... args) throws Exception {

        //查询总数sql
        String countSql = "select count(*) from (" + sql + ") cnt";
        final Long[] totalCount = {0L};
        getJdbcTemplate().query(countSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        for (int i = 1; i <= args.length; i++) {
                            int vi = i - 1;
                            ps.setObject(i, args[vi]);
                        }
                    }
                }, new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        totalCount[0] = rs.getLong(1);
                    }
                });
        //查询分页sql（mybatis）
//        String pageSql = sql + " limit " + (pageNo - 1) * pageSize + "," + pageSize;

        //查询分页sql（postgresql）
        String pageSql = sql + " limit " + pageSize + " offset " +(pageNo - 1) * pageSize;

        final List<Map<String, Object>> resultMapList = new ArrayList<Map<String, Object>>();
        getJdbcTemplate().query(pageSql,
                new PreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps) throws SQLException {
                        for (int i = 1; i <= args.length; i++) {
                            int vi = i - 1;
                            ps.setObject(i, args[vi]);
                        }
                    }
                }, new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        ResultSetMetaData rsmd = rs.getMetaData();
                        int count = rsmd.getColumnCount();
                        String[] columnNames = new String[count];
                        for (int i = 0; i < count; i++) {
                            columnNames[i] = rsmd.getColumnLabel((i + 1));
                        }
                        int columnIndex = 1;
                        Map<String, Object> map = new TreeMap();
                        for (int index = 0; index < count; index++) {
                            Object obg = rs.getString(columnIndex);
                            map.put(columnNames[index], obg);
                            ++columnIndex;
                        }
                        resultMapList.add(map);
                    }
                });
        //分页对象
        PageRequest pageRequest = buildPageRequest(pageNo, pageSize);
        Page page = new PageImpl(resultMapList, pageRequest, totalCount[0]);

        System.out.println(sql);

        // this.dataSource.getConnection().commit();
        // this.dataSource.getConnection().close();

        return page;
    }

    /**
     * 创建分页请求.
     */
    @Transactional(readOnly = true)
    private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
        return new PageRequest(pageNumber - 1, pagzSize, null);
    }

    /**
     * 原生SQL查询方法
     *
     * @param sql nativeSQL
     * @return List<Map<String, Object>>
     */
    @Transactional(readOnly = true)
    public List<Map<String, Object>> queryForMapList(String sql) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        System.out.println(sql);
        list = getJdbcTemplate().queryForList(sql);
        return list;
    }

    /**
     * 原生SQL查询一条记录
     *
     * @param sql nativeSQL
     * @return
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public Object queryForObject(String sql) throws Exception {
        Object obj = (Object) getJdbcTemplate().queryForObject(sql, Object.class);
        return obj;
    }

    public int updateForSql(String sql) throws Exception {
        int num = getJdbcTemplate().update(sql);
        return num;
    }

    /**
     * 原生sql查询一条记录
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public Map<String, Object> queryForMap(String sql) throws Exception {
        Map<String, Object> map = getJdbcTemplate().queryForMap(sql);
        return map;
    }

    /**
     * 原生sql查询，返回list<String>
     *
     * @param sql
     * @return
     * @throws Exception
     */
    public List<String> queryForList(String sql) throws Exception {
        List<String> list = getJdbcTemplate().queryForList(sql, String.class);
        return list;
    }

    /**
     * 批量更新方法
     *
     * @param sql
     * @param batchArgs
     * @return
     * @throws Exception
     */
    public int[] batchUpdate(String sql, List<Object[]> batchArgs) throws Exception {
        return getJdbcTemplate().batchUpdate(sql, batchArgs);
    }

    /**
     * 执行sql语句
     *
     * @param sql
     * @throws Exception
     */
    public void execute(String sql) throws Exception {
        getJdbcTemplate().execute(sql);
    }

}
