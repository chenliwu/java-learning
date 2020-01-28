package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 读写文件测试
 *
 * @author chenlw 2019/08/08
 */
public class ReadFileAndWriteFileTest {

    public static void main(String[] args) {
        try {
            // testSaveDataToFile();
            // testReadFileContent();
            testReadFileContentToList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void testSaveDataToFile() throws Exception {
        // 序号|状态|账号|户名|金额|备注|错误信息|
        StringBuilder data = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            data.append("序号" + i).append("|");
            data.append("状态" + i).append("|");
            data.append("账号" + i).append("|");
            data.append("户名" + i).append("|");
            data.append("金额" + i).append("|");
            data.append("备注" + i).append("|");
            data.append("错误信息" + i).append("|");
            data.append("\r\n");
        }
        String filePath = "mfsfile.txt";
        saveDataToFile(filePath, data.toString());
        System.out.println("保存数据到文件成功");
    }


    /**
     * 测试读取文件
     *
     * @throws Exception
     */
    public static void testReadFileContent() throws Exception {
        // 绝对路径或相对路径都可以
        String filePath = "mfsfile.txt";
        String data = readFileContentByLine(filePath);
        System.out.println(data);
    }


    /**
     * 测试读取文件内容，将每一行转化成List
     * @throws Exception
     */
    public static void testReadFileContentToList() throws Exception {
        System.out.println("测试读取文件内容，将每一行转化成List");
        String filePath = "mfsfile.txt";
        List<String> stringList = readFileContentByLineToList(filePath);
        stringList.stream().forEach((item) -> {
            System.out.println(item);
        });
    }


    /**
     * 保存数据到文件
     *
     * @param filePath
     * @param data
     * @throws Exception
     */
    public static void saveDataToFile(String filePath, String data) throws Exception {
        FileWriter fileWriter = null;
        PrintWriter printWriter = null;
        try {
            File file = new File(filePath);
            file.createNewFile();
            fileWriter = new FileWriter(file);
            printWriter = new PrintWriter(fileWriter);
            printWriter.print(data);
        } catch (Exception e) {
            throw new Exception("保存数据到文件失败：" + e.getMessage());
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
            if (fileWriter != null) {
                fileWriter.close();
            }
        }
    }


    /**
     * 按行读取文件内容，将其内容转化成字符串返回
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static String readFileContentByLine(String filePath) throws Exception {
        StringBuilder data = new StringBuilder();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            throw new Exception("按行读取文件内容错误：" + e.getMessage());
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return data.toString();
    }

    /**
     * 按行读取文件内容，将其内容转化成字符串返回
     *
     * @param filePath
     * @return
     * @throws Exception
     */
    public static List<String> readFileContentByLineToList(String filePath) throws Exception {
        List<String> stringList = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringList.add(line);
            }
        } catch (Exception e) {
            throw new Exception("按行读取文件内容错误：" + e.getMessage());
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        return stringList;
    }


}
