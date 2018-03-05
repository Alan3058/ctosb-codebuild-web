/*
 * @Project Name: ctosb-codebuild-web
 * @File Name: TemplateImport.java
 * @Package Name: template
 * @Date: 2017年10月10日下午7:14:39
 * @Creator: liliangang-1163
 * @line------------------------------
 * @修改人:
 * @修改时间:
 * @修改内容:
 */

package template;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import com.ctosb.codebuild.util.DbUtil;

/**
 * TODO
 * @author liliangang-1163
 * @date 2017年10月10日下午7:14:39
 * @see
 */
public class TemplateImport {

	String url = "jdbc:h2:file:~/.h2/test";
	String driver = "org.h2.Driver";
	String username = "root";
	String password = "";

	@Test
	public void testInsert() throws IOException, SQLException {
		String sql = "insert into template(template_id,template_name,template_content,generate_package_path,generate_source_folder) values(?,?,?,?,?)";
		Connection connection = DbUtil.getConnection(driver, url, username, password);
		PreparedStatement statement = connection.prepareStatement(sql);
		File bySql = new File("src/test/java/template/templates/bySql");
		File byTable = new File("src/test/java/template/templates/byTable");
		File[] files = (File[]) ArrayUtils.addAll(bySql.listFiles(), byTable.listFiles());
		for (File file : files) {
			String content = FileUtils.readFileToString(file, "utf-8");
			statement.setString(1, UUID.randomUUID().toString());
			statement.setString(2, file.getName());
			statement.setString(3, content);
			statement.setString(4, "com/ctosb/test");
			statement.setString(5, "src/main/java");
			statement.execute();
		}
	}
}
