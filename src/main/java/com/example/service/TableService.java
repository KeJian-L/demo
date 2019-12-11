package com.example.service;

import com.example.util.LocalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @Author: KjLi
 * @Description:
 * @Data: Create in 2019/12/10
 * @Modified By:
 */
@Service
public class TableService implements ITableService {

    public void databaseBackup(String databaseName) {
        String savePath = "C:\\Users\\L\\Desktop";
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if (!savePath.endsWith(File.separator)) {
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        String instruct = LocalConfig.getValue("file.path.mysqldump")+"mysqldump -h101.200.132.222 -p3306 " +
                "-uroot -pmymxdxy!@# --set-charset=UTF8 --column-statistics=0 --databases " + databaseName;
//        String[] instruct = new String[]{"cmd","/C","mysqldump -h101.200.132.222 -p3306 -uroot -pmymxdxy!@# " +
//                "--set-charset=UTF8 --column-statistics=0 --databases " + databaseName};
//        String[] instruct = new String[]{"cmd", "/C", instruct1};
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + "test.sql"), "utf8"));
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

    public void tableBackup() {

    }
}
