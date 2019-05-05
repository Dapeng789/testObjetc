package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import my.dao.po.BasePo;
import my.dao.pool.DBManager;
import my.dao.utils.OraclePOGen;
import my.dao.utils.OraclePOGen.Column;

/**
 * @author 作者：wlm
 *
 * @version 创建时间：2019年4月15日 上午10:01:27
 * 
 * @descriptions 类说明：生成数据模型 GETTER SETTER
 */
public class OraclePoGenForm {
	static boolean ann = true;

	public static void main(String[] args) throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		String[] tableName = "ZY_BRXX,ZY_HS_FZHZ,ZY_HS_SMTZ,ZY_HS_SMTZ_JS,ZY_YZZX,XT_XTSZ,ZY_HS_QJJL_ZJL,XT_GZTX,ZY_HS_QJJL_MX,XT_GZBQ_MX,ZY_HS_JKXJ,XT_GZBQ_ZJL,ZY_HS_TWJL,ZY_WJZD,ZY_jdc_ZJL,ZY_YJJ,ZY_JYD_MX,ZY_SSJL_MX,ZY_SSJL_ZJL"
				.split(",");

		String[] viewname = "view_Ukom_his_patient_info,ZY_HS_FZHZ,ZY_HS_SMTZ,ZY_HS_SMTZ_JS,view_Ukom_his_advice_list,XT_XTSZ,ZY_HS_QJJL_ZJL,XT_GZTX,ZY_HS_QJJL_MX,XT_GZBQ_MX,ZY_HS_JKXJ,XT_GZBQ_ZJL,ZY_HS_TWJL,ZY_WJZD,ZY_jdc_ZJL,ZY_YJJ,ZY_JYD_MX,ZY_SSJL_MX,ZY_SSJL_ZJL"
				.split(",");

		tableName = "p_user,p_user_role,p_role,p_role_ap,p_menu,p_action,p_menu_action,p_org".split(",");
		viewname = "p_user_v,p_user_role,p_role,p_role_ap_v,p_menu,p_action,p_menu_action_v,p_org_v".split(",");

		tableName = "P_TABLEDESC,P_METADATA".split(",");
		viewname = "P_TABLEDESC,P_METADATA".split(",");

		tableName = "P_DICT".split(",");
		viewname = "P_DICT".split(",");

		for (int i = 0; i < tableName.length; i++) {
			System.out.println(tableName[i] + ":" + viewname[i]);
			gen(tableName[i].toUpperCase(), viewname[i].toUpperCase());
		}
	}

	public static void gen(String tableName, String viewName)
			throws SQLException, FileNotFoundException, UnsupportedEncodingException {
		String sql = "select * from " + tableName + " where 1<0";
		String viewsql = "select * from " + viewName + " where 1<0";

		Connection con = DBManager.getConnection();

		Set<String> pks = getPks(tableName, con);

		Map<String, Column> tableColumns = getColumns(sql, con, pks, false);

		Map<String, Column> viewColumns = getColumns(viewsql, con, pks, true);

		List<Column> list = new ArrayList<Column>();

		for (Column c : tableColumns.values()) {
			list.add(c);

			viewColumns.remove(c.name);
		}

		for (Column c : viewColumns.values()) {
			list.add(c);
			c.isReadOnly = true;
		}

		String className = genClassName(tableName);

		System.out.println(className);

		StringBuilder sb = new StringBuilder();
		for (String s : pks) {
			if (sb.length() > 0) {
				sb.append(",");
			}
			sb.append(String.format("\"%s\"", new Object[] { s }));
		}
		String pk = sb.toString();

		System.out.println(pk);

		for (Column c : list) {
			System.out.println(c.getAnn());
			System.out.println(String.format("private %s %s;", new Object[] { c.getCtype(), c.name }));
		}

		File f = new File("d:/gensource/model/my/sys/" + className + ".java");
		f.getParentFile().mkdirs();
		PrintWriter pw = new PrintWriter(f, "UTF-8");
		Velocity.init("velocity.properties");

		VelocityContext context = new VelocityContext();
		context.put("tableName", tableName.toUpperCase());
		context.put("viewName", viewName.toUpperCase());
		context.put("classname", className);
		context.put("pk", pk);
		context.put("cols", list);
		Template template = null;
		try {
			template = Velocity.getTemplate("modelForm.vm", "UTF-8");
		} catch (ResourceNotFoundException rnfe) {
			System.out.println("Example : error : cannot find template modelForm.vm");
		} catch (ParseErrorException pee) {
			System.out.println("Example : Syntax error in template modelForm.vm :" + pee);
		}

		if (template != null) {
			template.merge(context, pw);
		}
		pw.close();

		System.out.println(tableColumns.size());
	}

	private static String genClassName(String tableName) {
		String from="Form";
		String[] ss = tableName.toLowerCase().split("_");
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < ss.length; i++) {
			sb.append(StringUtils.capitalize(ss[0]));
			sb.append(StringUtils.capitalize(ss[i]));
			String name=sb.toString();
		System.out.println(	name.toUpperCase());
		}
		return sb.append(from).toString();
	}

	private static Map<String, Column> getColumns(String sql, Connection con, Set<String> pks, boolean isview)
			throws SQLException {
		Map<String, test.OraclePoGenForm.Column> tableColumns = new LinkedHashMap<String, test.OraclePoGenForm.Column>();
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery(sql);

		ResultSetMetaData rsmd = rs.getMetaData();

		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			System.out.println(rsmd.getColumnName(i));
			System.out.println(rsmd.getColumnClassName(i));
			System.out.println(rsmd.getColumnTypeName(i));
			System.out.println(rsmd.getPrecision(i));
			System.out.println(rsmd.getScale(i));

			Column c = new Column();

			c.name = rsmd.getColumnLabel(i).toLowerCase();
			c.type = rsmd.getColumnTypeName(i);

			c.isReadOnly = isview;

			if ((pks.size() == 1) && (pks.contains(c.name.toUpperCase()))) {
				if ((c.type.equalsIgnoreCase("NUMBER")) && (rsmd.getScale(i) == 0))
					c.isId = true;
				else if ((c.type.equalsIgnoreCase("STRING")) || (c.type.equalsIgnoreCase("VARCHAR2"))) {
					c.isName = true;
				}
			}

			c.scale = rsmd.getScale(i);
			c.precision = rsmd.getPrecision(i);

			c.className = rsmd.getColumnClassName(i);

			tableColumns.put(c.name, c);
		}

		rs.close();
		st.close();
		return tableColumns;
	}

	private static Set<String> getPks(String tableName, Connection con) throws SQLException {
		Set<String> pks = new LinkedHashSet<String>();

		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet rsDBMD = dbmd.getPrimaryKeys(null, null, tableName.toUpperCase());
		while (rsDBMD.next()) {
			String pk = (String) rsDBMD.getObject(4);
			pks.add(pk);
		}

		rsDBMD.close();
		return pks;
	}

	public static String getClassName(String tableName) {
		String[] ss = tableName.split("_");
		StringBuilder sb = new StringBuilder();
		for (String s : ss) {
			sb.append(StringUtils.capitalize(s));
		}

		return sb.toString();
	}

	public static class Column extends BasePo {
		private static final long serialVersionUID = 1L;
		String name;
		String type;
		String className;
		int scale;
		int precision;
		boolean isReadOnly;
		boolean isId;
		boolean isName;

		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
		}

		public String getAnn() {
			if (!OraclePoGenForm.ann)
				return "";
			StringWriter sw = new StringWriter();

			PrintWriter pw = new PrintWriter(sw);

			

			//pw.print(String.format("\t@Column(value = \"%s\", type = %s)", new Object[] { this.name, columnType }));
			pw.close();
			return sw.toString();
		}

		public String getCtype() {
			if ("java.lang.String".equalsIgnoreCase(this.className))
				return "String";
			if (this.type.equalsIgnoreCase("DATE"))
				return "String";
			if (this.type.equalsIgnoreCase("BLOB"))
				return "byte[]";
			if (this.type.equalsIgnoreCase("CLOB"))
				return "String";
			if (this.type.equalsIgnoreCase("NUMBER")) {
				if (this.scale == 0) {
					return "Long";
				}
				return "BigDecimal";
			}
			if (this.type.equalsIgnoreCase("TIMESTAMP")) {
				return "String";
			}

			return "-error:" + this.name + ":" + this.type;
		}

		public String getMethodName() {
			return "get" + StringUtils.capitalize(this.name);
		}

		public String getSetMethodName() {
			return "set" + StringUtils.capitalize(this.name);
		}

		public String getName() {
			return this.name;
		}
	}
}