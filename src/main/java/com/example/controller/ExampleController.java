package com.example.controller;

import com.example.entity.Column;
import com.example.entity.Table;
import com.example.mapper.TableMapper;
import com.example.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/11/21
 * @Modified By:
 */
@RestController
public class ExampleController {

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private ITableService tableService;

    @GetMapping("/tableColumnInfo")
    public List<Column> tableColumnInfo(@RequestParam("database") String database,
                                  @RequestParam("tableName") String tableName) {
        return tableMapper.tableColumnInfo(database, tableName);
    }

    @GetMapping("showDatabase")
    public List<String> showDatabase() {
        return tableMapper.showDatabase();
    }

    @GetMapping("/tableInfo")
    public List<Table> tableInfo(@RequestParam("database") String database){
        return tableMapper.tableInfo(database);
    }

    @GetMapping("/createTable")
    public void createTable(@RequestBody Table table) {
//        Table table = new Table();
//        table.setName("test2");
//        table.setComment("生成测试");
//        table.setDatabaseName("test");
//
//        Column column1 = new Column();
//        column1.setName("name");
//        column1.setLength("16");
//        column1.setDefaultValue("张三");
//        column1.setType("varchar");
//
//        Column column2 = new Column();
//        column2.setName("age");
//        column2.setLength("3");
//        column2.setType("int");
//        column2.setNotNull(true);
//
//        List columns = new ArrayList();
//        columns.add(column1);
//        columns.add(column2);
//
//        table.setColumns(columns);
        tableMapper.createTable(table);
    }

    @GetMapping("/deleteTable")
    public void deleteTable(){
        tableService.databaseBackup("test");
    }

}
