package com.example.mapper;

import com.example.entity.Column;
import com.example.entity.Table;
import org.apache.ibatis.annotations.*;
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
            "table_schema as database_name," +
            "table_comment as comment " +
            "from information_schema.tables " +
            "where table_schema=#{database}")
    List<Table> tableInfo(String database);

    @Select("show databases")
    List<String> showDatabase();

    @Update("<script>" +
            "create table ${table.databaseName}.${table.name} (" +
            "id int NOT NULL AUTO_INCREMENT PRIMARY KEY," +
            "<foreach item=\"column\" collection=\"table.columns\" index=\"index\" separator=\",\">" +
            "${column.name} ${column.type}(${column.length}) " +
            "<if test=\"column.defaultValue!=null and column.defaultValue.length>0\">" +
            " default #{column.defaultValue,jdbcType=VARCHAR}" +
            "</if>" +
            "<if test=\"column.notNull\">" +
            " not null" +
            "</if>" +
            "<if test=\"column.comment!=null and column.comment.length>0\">"+
            " comment #{column.comment,jdbcType=VARCHAR}" +
            "</if>" +
            "</foreach>" +
            ")" +
            "</script>")
    void createTable(@Param("table") Table table);

    @Update("create database if not exists ${databaseName} default character set utf8 collate utf8_bin")
    void createDatabase(String databaseName);

    @Update("drop database ${databaseName}")
    void deleteDatabase(String databaseName);

    @Update("drop table ${databaseName}.${tableName}")
    void deleteTable(String databaseName,String tableName);
}
