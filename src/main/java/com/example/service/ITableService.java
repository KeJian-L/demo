package com.example.service;

import java.util.List;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/12/10
 * @Modified By:
 */
public interface ITableService {

    void databaseBackup(String ip, String port, String username, String password, List<String> databaseList);

    void tableBackup(String ip,String port,String username,String password,String databaseName,List<String> tableName);
}
