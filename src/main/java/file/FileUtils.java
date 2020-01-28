package file;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 *
 * @author chenlw
 * @create 2018-10-20 17:40
 */
public class FileUtils {

    final static String CHARSET_UTF_8 = "UTF-8";

    public static void main(String[] args) {
        try {
            test1();
            //test2();
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void test1() throws IOException {
        //D:\IDEA201702WorkSpace\GitProject\JavaWebProjects\MyJavaWebUtils
        String dirPath = "D:\\IDEA201702WorkSpace\\GitProject\\JavaWebProjects\\MyJavaWebUtils\\testFile";
        String fileName = "test.txt";
        //String content = readFileContent(dirPath + File.separator + fileName);
        String content = "{id:'',category:'教务管理系统数据库（MySql）',dataset_name:'学生信息数据集',data_json:'111'}";
        System.out.println(content);
        writerContentToFile(dirPath, "test.txt", content);
    }


    public static void test2() throws IOException {
        String dirPath = "D:\\IDEA201702WorkSpace\\GitProject\\JavaWebProjects\\MyJavaWebUtils\\testFile";
        String fileName = "test1.txt";
        System.out.println(isFileExists(dirPath + File.separator + fileName));
    }


    /**
     * 判断文件是否存在
     *
     * @param filePath
     * @return     
     */
    public static boolean isFileExists(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 判断文件是否是目录
     *
     * @param filePath
     * @return     
     */
    public static boolean isDirectoty(String filePath) {
        return new File(filePath).isDirectory();
    }


    /**
     * 按行读取文件的内容
     *
     * @param filePath
     * @param charsetName
     * @return
     * @throws IOException
     */
    public static String readFileContentByLine(String filePath, String charsetName) throws IOException {
        StringBuilder sb = new StringBuilder();
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("指定文件不存在：" + filePath);
        }
        if (file.isDirectory()) {
            throw new IOException("指定文件是一个文件夹：" + filePath);
        }
        if (charsetName == null || charsetName.trim().length() == 0) {
            charsetName = CHARSET_UTF_8;
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
        String str;
        while (null != (str = bufferedReader.readLine())) {
            sb.append(str).append("\n");
        }
        bufferedReader.close();
        return sb.toString();
    }


    /**
     * 向指定文件写入内容
     *
     * @param parentDirPath 文件父目录
     * @param fileName      输出文件名
     * @param content       写入内容
     * @throws IOException     
     */
    public static void writerContentToFile(String parentDirPath, String fileName, String content) throws IOException {
        File parentDir = new File(parentDirPath);
        if (!parentDir.exists()) {
            throw new IOException("文件父目录不存在：" + parentDirPath);
        }
        String filePath = parentDirPath + File.separator + fileName;
        if (!isFileExists(filePath)) {
            throw new IOException("文件不存在：" + filePath);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), Charset.forName(CHARSET_UTF_8)));
        bufferedWriter.write(content);
        bufferedWriter.close();
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



}
