package dao.impl;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceProvider {

    private static MysqlDataSource dataSource;

    public static DataSource getDataSource(){
        if(dataSource==null){
            dataSource= new MysqlDataSource();
            dataSource.setServerName("dz8959rne9lumkkw.chr7pe7iynqr.eu-west-1.rds.amazonaws.com");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("yh0zc30azywvnt6o");
            dataSource.setUser("fxhztmwrfjsud6fx");
            dataSource.setPassword("lkluxpmh3d9p4t8y");
        }
        return dataSource;
    }
}
