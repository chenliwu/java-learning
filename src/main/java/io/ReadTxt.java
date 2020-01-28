package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlw on 2019/07/11  9:17.
 */
public class ReadTxt {


    public static void main(String args[]) {
        // readFile();
        // writeFile();

        try {
            // readTxtFile();
            readFileByLines("input.txt");
        } catch (Exception e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
    }

    /**
     * 读入TXT文件
     */
    public static void readFile() {
        // 绝对路径或相对路径都可以，写入文件时演示相对路径,读取以上路径的input.txt文件
        String pathname = "input.txt";
        // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw;
        // 不关闭文件会导致资源的泄露，读写文件都同理
        // Java7的try-with-resources可以优雅关闭文件，异常时自动关闭文件；详细解读https://stackoverflow.com/a/12665271
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            //网友推荐更加简洁的写法
            while ((line = br.readLine()) != null) {
                // 一次读入一行数据
                System.out.println(line);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取txt文件
     * 将文件内容按行读取到数组列表返回
     *
     * @throws Exception
     */
    public static List<String> readTxtFile() throws Exception {
        InputStreamReader reader = null;
        BufferedReader br = null;
        List<String> contentList = new ArrayList<>();
        try {
            // 绝对路径或相对路径都可以
            String pathname = "input.txt";

            // 要读取以上路径的input.txt文件
            File filename = new File(pathname);

            // 建立一个输入流对象reader
            reader = new InputStreamReader(new FileInputStream(filename));
            // 建立一个对象，它把文件内容转成计算机能读懂的语言
            br = new BufferedReader(reader);
            String line;
            // 一次读入一行数据
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                contentList.add(line);
            }
        } catch (IOException e) {
            throw new IOException("读取文件失败：" + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (br != null) {
                br.close();
            }
        }
        return contentList;
    }


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param fileName 文件名
     */
    public static void readFileByLines(String fileName) throws IOException {
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            throw e;
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }


    /**
     * 写入TXT文件
     */
    public static void writeFile() {
        try {
            // 相对路径，如果没有则要建立一个新的output.txt文件
            File writeName = new File("output.txt");
            // 创建新文件,有同名的文件的话直接覆盖
            writeName.createNewFile();
            try (FileWriter writer = new FileWriter(writeName);
                 BufferedWriter out = new BufferedWriter(writer)
            ) {
                // \r\n即为换行
                out.write("我会写入文件啦1\r\n");
                out.write("我会写入文件啦2\r\n");
                // 把缓存区内容压入文件
                out.flush();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
