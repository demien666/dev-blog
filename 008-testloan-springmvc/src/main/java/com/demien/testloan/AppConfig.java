package com.demien.testloan;

import java.util.Arrays;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.demien.testloan.dao.BaseDAOImpl;
import com.demien.testloan.dao.IBaseDAO;
import com.demien.testloan.domain.Loan;
import com.demien.testloan.domain.User;
import com.demien.testloan.rest.LoanResource;
import com.demien.testloan.rest.UserResource;
import com.demien.testloan.service.ILoanService;
import com.demien.testloan.service.IUserService;
import com.demien.testloan.service.LoanServiceImpl;
import com.demien.testloan.service.UserServiceImpl;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.demien", excludeFilters = { @ComponentScan.Filter(Configuration.class) })
public class AppConfig {

    // ---------- DAO ------------------
    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Properties p = new Properties();
        dataSource.setConnectionProperties(p);
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/test");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");
        return dataSource;
    }

    @SuppressWarnings("rawtypes")
    @Bean
    public LocalSessionFactoryBean sessionFactoryBean() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        Class[] annotatedClasses = new Class[] { User.class, Loan.class };
        sessionFactoryBean.setAnnotatedClasses(annotatedClasses);
        Properties p = new Properties();
        p.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        p.put("hibernate.show_sql", "true");
        p.put("hibernate.hbm2ddl.auto", "create");
        sessionFactoryBean.setHibernateProperties(p);
        return sessionFactoryBean;
    }

    @Bean
    public SessionFactory sessionFactory() {
        return sessionFactoryBean().getObject();
    }

    @Bean
    public HibernateTransactionManager txManager() {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory());
        return txManager;
    }

    /*
     * @Bean public TransactionAttributeSource annotationTransactionAttributeSource() { return new
     * AnnotationTransactionAttributeSource(); }
     * @Bean public TransactionInterceptor transactionInterceptor() { return new TransactionInterceptor(txManager(),
     * annotationTransactionAttributeSource()); }
     */
    @Bean
    public IBaseDAO<Loan> loanDAOImpl() {
        IBaseDAO<Loan> loanDAOImpl = new BaseDAOImpl<Loan>(Loan.class, sessionFactory());
        return loanDAOImpl;
    }

    @Bean
    public IBaseDAO<User> userDAOImpl() {
        IBaseDAO<User> userDAOImpl = new BaseDAOImpl<User>(User.class, sessionFactory());
        return userDAOImpl;
    }


    @Bean
    public ILoanService loanService() {
        ILoanService loanService = new LoanServiceImpl(loanDAOImpl());
        return loanService;
    }

    @Bean
    public IUserService userService() {
        IUserService userService = new UserServiceImpl(userDAOImpl());
        return userService;
    }

    @Bean
    public LoanResource loanResource() {
        LoanResource loanResource = new LoanResource(loanService(), userService());
        return loanResource;
    }

    @Bean
    public UserResource userResource() {
        UserResource userResource = new UserResource(userService());
        return userResource;
    }

}
