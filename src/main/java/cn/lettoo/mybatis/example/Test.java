package cn.lettoo.mybatis.example;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.lettoo.mybatis.domain.Department;

public class Test {

	public static void main(String[] args) throws IOException {
		String resource = "Configuration.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession session = ssf.openSession();
		List<Department> deps = session.selectList("select-all-departments");
		
		for (Department dept: deps) {
			System.out.println(dept.toString());
		}
		
		session.close();
		
	}
}
