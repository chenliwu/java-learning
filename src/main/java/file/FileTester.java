package file;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlw 2019/08/21
 */
public class FileTester {

    public static void main(String[] args) {
        try {
            // createDirectoryUsingMakeDir();
            // createDirectoryUsingMakeDirs();
            forEachFile();
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }


    public static void forEachFile() throws Exception {
        String path = "D:\\BytterProject\\T2\\sql\\patch";        //要遍历的路径
        File directory = new File(path);        //获取其file对象
        File[] listFiles = directory.listFiles();    //遍历path下的文件和目录，放在File数组中
        String fileName;
        List<String> dataList = new ArrayList<>();
        for (File file : listFiles) {                    //遍历File[]数组
            //若非目录(即文件)，则打印
            if (!file.isDirectory()) {
                fileName = file.getName();
                if (StringUtils.contains(fileName, "ORCL")) {
                    // 遍历特定名称
                    System.out.println(fileName);
                    //System.out.println(file.getAbsolutePath());
                    dataList.addAll(FileUtils.readFileContentByLineToList(file.getAbsolutePath()));
                }
            }
        }
        StringBuilder dataString = new StringBuilder();
        for (String data : dataList) {
            dataString.append(data).append("\r\n");
        }
        FileUtils.saveDataToFile("D:\\BytterProject\\T2\\sql\\t2UpdateSql.sql", new String(dataString.toString().getBytes("UTF-8")));
    }



    /**
     * 使用mkdir()方法创建目录E:/AAA/BBB/CCC，其结果为false，即创建失败。
     * 原因：mkdir()，创建此抽象路径名指定的目录。如果父目录不存在则创建不成功。因为AAA和BBB目录都不存在，所以目录创建失败。
     */
    public static void createDirectoryUsingMakeDir() {
        String directoryPath = "E:/AAA/BBB/CCC";
        File directory = new File(directoryPath);
        System.out.println("文件是否存在：" + directory.exists());
        if (!directory.exists()) {
            boolean createdResult = directory.mkdir();
            System.out.println("使用mkdir()方法创建目录结果：" + createdResult);
        }

    }

    /**
     * mkdirs() 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
     */
    public static void createDirectoryUsingMakeDirs() {
        String directoryPath = "E:/AAA/BBB/CCC";
        File directory = new File(directoryPath);
        System.out.println("文件是否存在：" + directory.exists());
        if (!directory.exists()) {
            boolean createdResult = directory.mkdirs();
            System.out.println("使用mkdirs()方法创建目录结果：" + createdResult);
        }

    }


}
