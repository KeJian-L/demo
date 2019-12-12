package com.example.controller;

import com.example.entity.Column;
import com.example.entity.Table;
import com.example.mapper.TableMapper;
import com.example.service.ITableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
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
    private ITableService tableService;

    @GetMapping("/tableColumnInfo")
    public List<Column> tableColumnInfo(@RequestParam("database") String database,
                                        @RequestParam("tableName") String tableName, HttpServletResponse response) {
        if (database == null || database.length()==0 || tableName == null || tableName.length()==0) {
            response.setStatus(400);
            response.setHeader("x-message", "请求参数缺失");
            return null;
        } else {
            return tableService.tableColumnInfo(database, tableName);
        }
    }

    @GetMapping("showDatabase")
    public List<String> showDatabase() {
        return tableService.showDatabase();
    }

    @GetMapping("/tableInfo")
    public List<Table> tableInfo(@RequestParam("database") String database, HttpServletResponse response) {
        if (database == null || database.length()==0) {
            response.setStatus(400);
            response.setHeader("x-message", "请求参数缺失");
            return null;
        } else {
            return tableService.tableInfo(database);
        }

    }

    @PostMapping("/createTable")
    public void createTable(@RequestBody Table table, HttpServletResponse response) {
        if (table == null || table.getDatabaseName() == null || table.getName() == null) {
            response.setStatus(400);
            response.setHeader("x-message", "请求参数缺失");
            return;
        } else {
            tableService.createTable(table);
        }
    }

    @PostMapping("/deleteTable")
    public void deleteTable(@RequestBody Map<String, String> body, HttpServletResponse response) {
        if (body == null || body.size() == 0 || !body.containsKey("databaseName") || !body.containsKey("tableName")) {
            response.setStatus(400);
            response.setHeader("x-message", "请求参数缺失");
            return;
        } else {
            tableService.deleteTable(body.get("databaseName"), body.get("tableName"));
        }
    }

}
