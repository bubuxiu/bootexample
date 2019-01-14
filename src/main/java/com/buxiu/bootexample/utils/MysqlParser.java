package com.buxiu.bootexample.utils;
 

import java.util.List;
 
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.FromItem;
import net.sf.jsqlparser.statement.select.Join;
import net.sf.jsqlparser.statement.select.LateralSubSelect;
import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.SubJoin;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.select.ValuesList;
import net.sf.jsqlparser.statement.select.WithItem;
 
public class MysqlParser {
	
	public String removeOrderBy(String sql) throws JSQLParserException {
		Statement stmt = CCJSqlParserUtil.parse(sql);
		Select select = (Select) stmt;
		SelectBody selectBody = select.getSelectBody();
		processSelectBody(selectBody);
		return select.toString();
	}
 
	public void processSelectBody(SelectBody selectBody) {
		if (selectBody instanceof PlainSelect) {
			processPlainSelect((PlainSelect) selectBody);
		} else if (selectBody instanceof WithItem) {
			WithItem withItem = (WithItem) selectBody;
			if (withItem.getSelectBody() != null) {
				processSelectBody(withItem.getSelectBody());
			}
		} else {
			SetOperationList operationList = (SetOperationList) selectBody;
			if (operationList.getPlainSelects() != null && operationList.getPlainSelects().size() > 0) {
				List<PlainSelect> plainSelects = operationList.getPlainSelects();
				for (PlainSelect plainSelect : plainSelects) {
					processPlainSelect(plainSelect);
				}
			}
			if (!orderByHashParameters(operationList.getOrderByElements())) {
				operationList.setOrderByElements(null);
			}
		}
	}
 
	public void processPlainSelect(PlainSelect plainSelect) {
		if (!orderByHashParameters(plainSelect.getOrderByElements())) {
			plainSelect.setOrderByElements(null);
		}
		if (plainSelect.getFromItem() != null) {
			processFromItem(plainSelect.getFromItem());
		}
		if (plainSelect.getJoins() != null && plainSelect.getJoins().size() > 0) {
			List<Join> joins = plainSelect.getJoins();
			for (Join join : joins) {
				if (join.getRightItem() != null) {
					processFromItem(join.getRightItem());
				}
			}
		}
	}
 
	public void processFromItem(FromItem fromItem) {
		if (fromItem instanceof SubJoin) {
			SubJoin subJoin = (SubJoin) fromItem;
			if (subJoin.getJoin() != null) {
				if (subJoin.getJoin().getRightItem() != null) {
					processFromItem(subJoin.getJoin().getRightItem());
				}
			}
			if (subJoin.getLeft() != null) {
				processFromItem(subJoin.getLeft());
			}
		} else if (fromItem instanceof SubSelect) {
			SubSelect subSelect = (SubSelect) fromItem;
			if (subSelect.getSelectBody() != null) {
				processSelectBody(subSelect.getSelectBody());
			}
		} else if (fromItem instanceof ValuesList) {
 
		} else if (fromItem instanceof LateralSubSelect) {
			LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
			if (lateralSubSelect.getSubSelect() != null) {
				SubSelect subSelect = (SubSelect) (lateralSubSelect.getSubSelect());
				if (subSelect.getSelectBody() != null) {
					processSelectBody(subSelect.getSelectBody());
				}
			}
		}
	}
 
	public boolean orderByHashParameters(List<OrderByElement> orderByElements) {
		if (orderByElements == null) {
			return false;
		}
		for (OrderByElement orderByElement : orderByElements) {
			if (orderByElement.toString().toUpperCase().contains("?")) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String oldSql = "select g.* ,\n" + 
				"			b.username as handleusername, c.name as productname , e.versionname as versionname\n" + 
				"		from (select a.bugid, a.status, a.rejectreason, a.sequence, a.handleuser, a.bugtype, a.productid, \n" + 
				"			a.projectid, a.versionid, a.title, a.description, \n" + 
				"		        a.images, a.files, a.priority, a.preuserid, a.begintime, a.endtime, a.adduser, a.addusername, a.addtime, \n" + 
				"		        a.updateuser, a.updateusername, a.updatetime\n" + 
				"			from  bug_bug a \n" + 
				"			where a.x = 1\n" + 
				"order by a.xxx ) g    \n" + 
				"		inner join hellodev_user b on g.handleuser = b.userid\n" + 
				"		inner join bug_product c on g.productid = c.id \n" + 
				"		inner join bug_productversion e on g.versionid = e.versionid \n" + 
				"order by g.gg";
		String newSql = "";
		try {
			newSql = MysqlParser.class.newInstance().removeOrderBy(oldSql);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSQLParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //去除ORDER BY

		System.out.println(newSql); 
	}
}
