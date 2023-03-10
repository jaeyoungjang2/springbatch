//package io.springbatch.springbatch.backup;
//
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
//import org.springframework.boot.autoconfigure.batch.BatchProperties;
//import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//// jobRepository 설정
//
//@Configuration
//public class CustomBatchConfigurer extends BasicBatchConfigurer {
//
//    private final DataSource dataSource;
//
//    protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
//        super(properties, dataSource, transactionManagerCustomizers);
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    protected JobRepository createJobRepository() throws Exception {
//
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        // datasource는 기본적으로 설정이 되지만, 별도의 datasource를 설정하고 싶다면 아래와 같이 하면된다.
//        factory.setDataSource(dataSource);
//        factory.setTransactionManager(getTransactionManager());
//        factory.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
//        // 기본값은 batch로 시작함
//        // 테이블 명 자체가 변하는 것은 아님, table명 변경은 따로 해줘야 함.
//        // factory.setTablePrefix("SYSTEM_");
//
//        return factory.getObject();
//    }
//}
