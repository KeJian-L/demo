package com.example.service;

import com.example.util.LocalConfig;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/12/10
 * @Modified By:
 */
@Service
public class TableService implements ITableService {

    public void databaseBackup(String ip, String port, String username, String password, List<String> databaseList) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String sqlFileName = databaseList.get(0) + "-" + df.format(new Date()) + ".sql";
        String mysqldumpFilePath = LocalConfig.getValue("file.path.mysqldump");
        String databaseName = "";
        for (String database:databaseList) {
            databaseName+=" "+database;
        }
        String instruct = mysqldumpFilePath + "mysqldump -h" + ip + " -p" + port +
                " -u" + username + " -p" + password + " --set-charset=UTF8 --column-statistics=0 --databases" + databaseName;
        backup(instruct,sqlFileName);
    }

    public void tableBackup(String ip, String port, String username, String password, String databaseName, List<String> tableList) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String sqlFileName = databaseName + "-" + tableList.get(0) + "-" + df.format(new Date()) + ".sql";
        String mysqldumpFilePath = LocalConfig.getValue("file.path.mysqldump");
        String tableName = "";
        for (String table:tableList) {
            tableName+=" "+table;
        }
        String instruct = mysqldumpFilePath + "mysqldump -h" + ip + " -p" + port +
                " -u" + username + " -p" + password + " --set-charset=UTF8 --column-statistics=0 " + databaseName+" "+tableName;
        backup(instruct,sqlFileName);
    }

    private void backup(String instruct,String sqlFileName){
        String sqlFilePath = LocalConfig.getValue("file.path.detabasebackup");
        File saveFile = new File(sqlFilePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!sqlFilePath.endsWith(File.separator)) {
            sqlFilePath = sqlFilePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(sqlFilePath + sqlFileName), "utf8"));
            Process process = Runtime.getRuntime().exec(instruct);
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                printWriter.println(line);
            }
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            printWriter.close();
        }
    }
}
