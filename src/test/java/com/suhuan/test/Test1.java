package com.suhuan.test;
import com.suhuan.pojo.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test1 {
	private SqlSession sqlSession = null;
    @Before
    public void init(){
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();//������
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory factory = ssfb.build(resourceAsStream);//�����߽����������,�����ֵ��IO������ָ��sqlMapConfig.xml(�൱��ͼֽ)
        sqlSession = factory.openSession();//�����������ĳ�
    }
    @Test//������pom.xml�е�Junit��Ԫ���Թ���
    public void testFindAll(){
        //����SQL��䣺
        List<Emp> list = sqlSession.selectList("findEmp");//���صĶ���Dept����װ�뵽list��������
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
    @After
    public void release(){
        //�ر�SQLSession
        sqlSession.close();
    }
}
