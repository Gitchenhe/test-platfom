package com.chenhe.shiro;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhe
 * @Date 2018-04-16 17:00
 * @desc
 **/
public class ParseFile {
    @Test
    public void test() throws IOException {
        String path = "C:\\Users\\chenhe\\Desktop\\新建文件夹";
        File file = new File(path);
        String[] fileNames = file.list();
        BufferedReader bufferedReader;
        char[] charBuffer= new char[1024];
        Map<String,Integer> tableCount = new HashMap<>();
        String sql;
        for (String name : fileNames){
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path+File.separator+name)));
            while((sql = bufferedReader.readLine()) != null){
                if (sql.contains("create table")){
                    sql = sql.replace("create table","").trim();
                    if (tableCount.containsKey(sql)){
                        Integer integer = tableCount.get(sql)==null?0:tableCount.get(sql);
                        tableCount.put(sql,integer);
                    }else{
                        tableCount.put(sql,1);
                    }
                }else{

                }
            }
            bufferedReader.close();
        }
        tableCount.forEach((key,value) ->{
            System.out.println(value+","+key);
        });
    }
}
