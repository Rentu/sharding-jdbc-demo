package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfig {

    /**
     * 配置数据源
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource getDataSource() throws SQLException {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // 添加一张表的规则，如有多张表需添加多个，我们这里只有一张表
        shardingRuleConfig.getTableRuleConfigs().add(getStudentTableRuleConfiguration());
        // 定义分库策略，这里采用的是表达式的写法，意思为，根据user_id 分库，当sql语句中带有user_id字段的时候，会采用user_id 对2取模的算法得出结果，然后test拼接上运算后的结果，这里的结果也就是test0或者test1
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "test${user_id % 2}"));
        // 定义分表策略，为了演示更多的写法，这里采用另一种策略，更为灵活，这里也是根据user_id分表，具体策略是ModuloShardingTableAlgorithm实现的
        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new ModuloShardingTableAlgorithm()));
        // 生成Sharding jdbc的数据源
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }

    /**
     * 这里写每个数据库中真实存在的节点，这里的意思是有test0和test1库，每个库里存在3张student表
     * @return
     */
    TableRuleConfiguration getStudentTableRuleConfiguration() {
        return new TableRuleConfiguration("student", "test${0..1}.student${0..3}");
    }

    /**
     * 自定义的分表策略，preciseShardingValue就是分库分表字段的值，这里也就是user_id的值
     */
    static class ModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Integer>{
        @Override
        public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {
            System.out.println(preciseShardingValue.getValue());
            // 返回表名
            return "student_0"+preciseShardingValue.getValue() % 3;
        }
    }

    /**
     * 配置Druid 数据源0
     * @return
     */
    private DataSource getDruidDataSource0(){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test0?useSSL=false");
        return  dataSource;
    }

    /**
     * 配置Druid 数据源1
     * @return
     */
    private DataSource getDruidDataSource1(){
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test1?useSSL=false");
        return  dataSource;
    }

    /**
     * 组成一个Map
     * @return
     */
    Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> result = new HashMap<>();
        result.put("test0", getDruidDataSource0());
        result.put("test1", getDruidDataSource1());
        return result;
    }

}
