package com.example.mapper;

import com.example.entity.Column;
import com.example.entity.Table;
import javafx.scene.control.Tab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/11/21
 * @Modified By:
 */
@Mapper
@Repository
public interface TableMapper {

    @Select("select column_name as name," +
            "data_type as type," +
            "character_maximum_length as length," +
            "column_comment as comment," +
            "column_default as default_value," +
            "is_nullable as is_null " +
            "from information_schema.columns " +
            "where table_name=#{tableName} and table_schema=#{database}")
    List<Column> tableColumnInfo(String database, String tableName);

    @Select("select table_name as name," +
            "table_rows as count," +
            "data_length," +
            "create_time," +
            "update_time," +
            "table_comment as comment " +
            "from information_schema.tables " +
            "where table_schema=#{database}")
    List<Table> tableInfo(String database);

    @Select("show databases")
    List<String> showDatabase();

    @Update("create table ")
    void createTable(Table table);
}
