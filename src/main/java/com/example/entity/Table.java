package com.example.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/11/21
 * @Modified By:
 */
public class Table {

    private String name;
    private String comment;
    private Integer count;
    private Long dataLength;
    private Timestamp createTime;
    private Timestamp updateTime;
    private List<Column> columns;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getDataLength() {
        return dataLength;
    }

    public void setDataLength(Long dataLength) {
        this.dataLength = dataLength;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
