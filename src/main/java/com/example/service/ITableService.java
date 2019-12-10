package com.example.service;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/12/10
 * @Modified By:
 */
public interface ITableService {

    void databaseBackup(String databaseName);

    void tableBackup();
}
