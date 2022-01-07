package com.javashitang.transactional;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author lilimin
 * @since 2022-01-02
 */
public class TransactionalTest {

//    @Test
//    public void v1() {
//        DataSource dataSource = new DruidDataSource();
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
//            // 这里是业务逻辑
//            connection.commit();
//        } catch (Exception e) {
//            connection.rollback();
//        }
//    }
//
    @Test
    public void v2() {
        SessionFactory sessionFactory = new SessionFactoryImpl(null, null, null);
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // 这里是业务逻辑
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }
}
