package com.example.demo.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

/*@Component*/
@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class, Integer.class})})
public class PageInterceptor implements Interceptor {

	private final static String PAGE_SIGN = "page";

	private final static String LIMIT_SIGN = "limit";

	private final static String TOTAL_SIGN = "total";

	private static String databasetype;

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

		MetaObject metaStatement = MetaObject.forObject(statementHandler, new DefaultObjectFactory(),
				new DefaultObjectWrapperFactory(), new DefaultReflectorFactory());
		while (metaStatement.hasGetter("h")) {
			Object object = metaStatement.getValue("h");
			metaStatement = MetaObject.forObject(object, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(),  new DefaultReflectorFactory());
		}
		while (metaStatement.hasGetter("target")) {
			Object object = metaStatement.getValue("target");
			metaStatement = MetaObject.forObject(object, new DefaultObjectFactory(), new DefaultObjectWrapperFactory(),  new DefaultReflectorFactory());
		}

		/*
		 * // 如果不是mysql 执行原始SQL if (!"mysql".equals(databasetype)) { return
		 * invocation.proceed(); }
		 */

		BoundSql boundSql = (BoundSql) metaStatement.getValue("delegate.boundSql");
		String oldSql = boundSql.getSql();
		String newSql = null;
		Object parameterObject = boundSql.getParameterObject();
		if ((parameterObject == null) || (!metaStatement.hasGetter("delegate.boundSql.parameterObject." + PAGE_SIGN))
				|| (!metaStatement.hasGetter("delegate.boundSql.parameterObject." + LIMIT_SIGN))) {
			return invocation.proceed();
		}
		Integer page = (Integer) metaStatement.getValue("delegate.boundSql.parameterObject." + PAGE_SIGN);
		Integer limit = (Integer) metaStatement.getValue("delegate.boundSql.parameterObject." + LIMIT_SIGN);

		// 如果pageInfo对象为空 执行原始SQL
		if (page == null || limit == null || limit == 0) {
			return invocation.proceed();
		}

		// 设置结果集总数
		if (!setTotal(metaStatement, boundSql)) {
			return invocation.proceed();
		}

		RowBounds rowBounds = new RowBounds((page - 1) * limit, limit);
		if (rowBounds.getLimit() > 0)
			newSql = oldSql + " limit " + rowBounds.getOffset() + ", " + rowBounds.getLimit();
		else
			newSql = oldSql;
		metaStatement.setValue("delegate.boundSql.sql", newSql);
		metaStatement.setValue("delegate.rowBounds.offset", 0);
		metaStatement.setValue("delegate.rowBounds.limit", Integer.MAX_VALUE);
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		if ((target instanceof StatementHandler)) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 */
	private String getCountSql(String sql) {
		StringBuffer _sql = new StringBuffer("SELECT COUNT(*) FROM (");
		_sql.append(sql);
		_sql.append(") aliasForPage");
		return _sql.toString();
	}

	public void setProperties(Properties properties) {
		databasetype = properties.getProperty("databasetype");
	}

	private boolean setTotal(MetaObject metaStatement, BoundSql boundSql) throws SQLException {
		MappedStatement mappedStatement = (MappedStatement) metaStatement.getValue("delegate.mappedStatement");
		Configuration config = (Configuration) metaStatement.getValue("delegate.configuration");
		Connection connection = config.getEnvironment().getDataSource().getConnection();
		String cntSql = getCountSql(boundSql.getSql());
		BoundSql cntBndSql = new BoundSql(mappedStatement.getConfiguration(), cntSql, boundSql.getParameterMappings(),
				boundSql.getParameterObject());
		boolean ok = true;
		PreparedStatement cntStmt = null;
		ResultSet rs = null;
		try {
			cntStmt = connection.prepareStatement(cntSql);
			ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
					boundSql.getParameterObject(), cntBndSql);
			parameterHandler.setParameters(cntStmt);

			rs = cntStmt.executeQuery();
			int total = 0;
			if (rs.next()) {
				total = rs.getInt(1);
			}
			metaStatement.setValue("delegate.boundSql.parameterObject." + TOTAL_SIGN, total);
		} catch (SQLException e) {
			ok = false;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (cntStmt != null) {
					cntStmt.close();
				}
				connection.close();
			} catch (SQLException e) {
			}
		}

		return ok;
	}

}