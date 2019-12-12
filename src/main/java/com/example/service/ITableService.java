package com.example.service;

import com.example.entity.Column;
import com.example.entity.Table;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/12/10
 * @Modified By:
 */
public interface ITableService {

    void databaseBackup(String ip, String port, String username, String password, List<String> databaseList);

    void tableBackup(String ip, String port, String username, String password, String databaseName, List<String> tableName);

    List<Column> tableColumnInfo(String database, String tableName);

    List<Table> tableInfo(String database);

    List<String> showDatabase();

    void createTable(@Param("table") Table table);

    void deleteDatabase(String databaseName);

    void deleteTable(String databaseName,String tableName);
}
