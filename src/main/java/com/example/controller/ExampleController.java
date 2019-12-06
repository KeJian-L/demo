package com.example.controller;

import com.example.entity.Column;
import com.example.entity.Table;
import com.example.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/11/21
 * @Modified By:
 */
@RestController
public class ExampleController {

    @Autowired
    TableMapper tableMapper;

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

    @PostMapping("/createTable")
    public void createTable() {

    }
}
