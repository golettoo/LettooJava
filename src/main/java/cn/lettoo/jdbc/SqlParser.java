package cn.lettoo.jdbc;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;

public final class SqlParser {

	// the email template file name
	private static String tplXmlFile = "sql.xml";
	// save the parsed email entries
	private Map<String, String> mapSqls = new HashMap<String, String>();

	private static SqlParser instance = new SqlParser();

	private static boolean initialized = false;

	private static synchronized boolean isInitialized() {
		return initialized;
	}

	public static SqlParser getInstance() {
		return getInstance(tplXmlFile);
	}

	public static SqlParser getInstance(String xmlFile) {
		if (xmlFile == null) {
			return null;
		} else {
			setTplXmlFile(xmlFile);
		}

		if (!isInitialized()) {
			try {
				instance.initialize();
			} catch (Exception e) {
				return null;
			}
		}
		return instance;
	}

	/**
	 * @param tplFile
	 */
	private SqlParser() {
	}

	public String getSql(String name) {
		return mapSqls.get(name);
	}

	private synchronized void initialize() throws Exception {
		if (initialized) {
			return;
		}
		mapSqls.clear();
		Namespace ns = Namespace.getNamespace("cn.lettoo");

		FileInputStream fi = null;
		try {
			fi = new FileInputStream(tplXmlFile);

			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(fi);
			Element root = doc.getRootElement();

			List tpls = root.getChildren();
			if (tpls.size() == 0) {
				throw new Exception("Not found any sql template item");
			}

			Element tpl = null;
			for (int i = 0; i < tpls.size(); i++) {
				tpl = (Element) tpls.get(i);
				if (tpl.getName().equalsIgnoreCase("sql")) {
					String sqlName = tpl.getAttributeValue("name");
					String strSql = tpl.getChild("content", ns).getText();
					if (sqlName != null && strSql != null) {
						mapSqls.put(sqlName.trim(), strSql.trim());
					}
				}
			}
		} finally {
			try {
				fi.close();
			} catch (Exception e) {

			}
		}
		initialized = true;
	}

	public static void setTplXmlFile(String tplXmlFile) {
		SqlParser.tplXmlFile = tplXmlFile;
	}

}