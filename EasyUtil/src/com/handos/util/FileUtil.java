package com.handos.util;

import java.io.*;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jimmylee on 5/10/14.
 */
public class FileUtil {

    /**
     *
     * @param url
     * @return
     */
    public  StringBuilder ReadFile(String url) {
        File file = new File(url);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;

            String regxPattern="qvod:[^\\s]*%7C";
            Pattern pattern = Pattern.compile(regxPattern);

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {



                Matcher matcher = pattern.matcher(tempString);
                if (matcher.find()) {
                    line++;
                    String result=matcher.group();
                    String str=new String(result.getBytes("GBK"),"UTF-8").trim();

                    System.out.println(URLDecoder.decode(str,"UTF-8"));
                }
            }
            System.out.println(String.format("匹配结束，共匹配:%d行",line));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return null;
    }
    public static StringBuilder ReadFileMethod(String url) {
        File file = new File(url);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line=0;

            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                String[] str=tempString.split("&");
                for(int i=0;i<str.length;i++) {
                    if(i>0)
                    {
                        if(!str[i-1].equals(str[i]))
                        {
                            line++;

                            System.out.println(str[i]);
                        }
                    }else
                    {

                        line++;
                        System.out.println(str[i]);
                    }

                }
            }
            System.out.println("共计:"+line+"行");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return null;
    }
}
