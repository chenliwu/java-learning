package file;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 *
 * @author chenlw 2019/08/06
 */
public class AbcFileUtils {

    private static String resourceParentPath = System.getProperty("user.dir") + "/testFiles";

    private static String outputFilePath = resourceParentPath + File.separator + "outputFiles";

    public static void main(String[] args) {
        System.out.println("当前JVM的默认字符集：" + Charset.defaultCharset());
        // testReadCharsetFile();

        testSaveContentToFileCharset();
    }

    /************************************ 测试保存内容到文件的编码格式   ***************************************/

    static void testSaveContentToFileCharset() {
        testSaveContentToFileUsingJVMDefaultCharset();
        testSaveContentToFileUsingUTF8Charset();
        testSaveContentToFileUsingGBKCharset();
    }

    /**
     * 使用JVM默认字符编码格式保存内容
     */
    static void testSaveContentToFileUsingJVMDefaultCharset() {
        try {
            String saveContent = "测试保存内容到文件的编码格式-JVM默认字符编码格式";
            saveDataToFileUsingCharset(outputFilePath, "testSaveContentToFileUsing_JVMDefaultCharset.txt", saveContent, null);
            System.out.println();
            System.out.println("使用JVM默认字符编码格式保存内容 成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 测试保存内容到文件的编码格式，UTF-8
     */
    static void testSaveContentToFileUsingUTF8Charset() {
        try {
            String saveContent = "测试保存内容到文件的编码格式-UTF8";
            saveDataToFileUsingCharset(outputFilePath, "testSaveContentToFile_UTF8Charset.txt", saveContent, StandardCharsets.UTF_8.name());
            System.out.println();
            System.out.println("使用UTF-8字符编码格式保存内容 成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 测试保存内容到文件的编码格式，GBK
     */
    static void testSaveContentToFileUsingGBKCharset() {
        try {
            String saveContent = "测试保存内容到文件的编码格式-GBK";
            saveDataToFileUsingCharset(outputFilePath, "testSaveContentToFile_GBKCharset.txt", saveContent, "GBK");
            System.out.println();
            System.out.println("使用GBK字符编码格式保存内容 成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常：" + e.getMessage());
        }
    }


    /************************************ 测试保存内容到文件的编码格式   ***************************************/


    /************************************ 测试读取不同编码格式的文件内容   ***************************************/

    /**
     * 测试读取不同编码格式的文件内容，JVM默认字符集的表现
     * <p>
     * 总结：
     * 1、JVM默认字符编码格式为：
     * window：GBK； macOS：UTF-8；
     * 2、由于JVM字符集编码的不同，在不同平台上运行程序就可能得不到正确的结果，因此在进行IO操作的时候要特别注意字符编码问题，要统一字符编码格式。
     */
    static void testReadCharsetFile() {
        testReadUTF8File();
        testReadGBKFile();
        testReadGB2312File();
    }

    /**
     * 测试UTF-8编码格式文件内容
     * - JVM默认字符集编码格式为UTF8时，文件内容能够正常显示
     */
    private static void testReadUTF8File() {
        try {
            // 文件编码格式为UTF-8
            String fileName = "testCharsetFile_UTF8.txt";
            // 如果运行平台-JVM字符集默认GBK，中文会显示乱码。
            String defaultCharsetFileContent = readMfsBatchFileContent(resourceParentPath, fileName, null);
            System.out.println();
            System.out.println("测试-文件编码格式为UTF-8");
            System.out.println("按JVM字符默认编码显示文件内容：" + defaultCharsetFileContent);

            // UTF-8编码显示，中文是正常的
            String utf8CharsetFileContent = readMfsBatchFileContent(resourceParentPath, fileName, StandardCharsets.UTF_8.name());
            System.out.println("UTF_8编码显示文件内容：" + utf8CharsetFileContent);

        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 测试GBK编码格式文件内容
     * - JVM默认字符集编码格式为UTF8时，文件内容不能够正常显示； 需要指定编码格式为GBK时，文件内容才能正常显示。
     */
    private static void testReadGBKFile() {
        try {
            // 文件编码格式为GBK
            String fileName = "testCharsetFile_GBK.txt";
            // 如果运行平台-JVM字符集默认GBK，中文会显示乱码。
            String defaultCharsetFileContent = readMfsBatchFileContent(resourceParentPath, fileName, null);
            System.out.println();
            System.out.println("测试-文件编码格式为GBK");
            System.out.println("按JVM字符默认编码显示文件内容：" + defaultCharsetFileContent);

            String utf8CharsetFileContent = readMfsBatchFileContent(resourceParentPath, fileName, "GBK");
            // String utf8CharsetFileContent = new String(defaultCharsetFileContent.getBytes(), "GBK");
            System.out.println("GBK编码显示文件内容：" + utf8CharsetFileContent);

        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }

    /**
     * 测试GBK编码格式文件内容
     * - JVM默认字符集编码格式为UTF8时，文件内容不能够正常显示； 需要指定编码格式为GB2312时，文件内容才能正常显示。
     */
    private static void testReadGB2312File() {
        try {
            // 文件编码格式为GB2312
            String fileName = "testCharsetFile_GB2312.txt";

            // 如果运行平台-JVM字符集默认GBK，中文会显示乱码。
            String defaultCharsetFileContent = readMfsBatchFileContent(resourceParentPath, fileName, null);
            System.out.println();
            System.out.println("测试-文件编码格式为GB2312");
            System.out.println("按JVM字符默认编码显示文件内容：" + defaultCharsetFileContent);

            String utf8CharsetFileContent = readMfsBatchFileContent(resourceParentPath, fileName, "GB2312");
            // String utf8CharsetFileContent = new String(defaultCharsetFileContent.getBytes(), "GBK");
            System.out.println("GB2312编码显示文件内容：" + utf8CharsetFileContent);

        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
        }
    }
    /************************************ 测试读取不同编码格式的文件内容   ***************************************/


    /**
     * 保存数据到文件
     * 会覆盖文件里面的内容
     *
     * @param filePath 文件路径
     * @param data     要保存到文件的内容
     * @throws Exception 异常
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
     * 保存字符串到文件，并指定编码格式
     *
     * @param directoryPath 文件目录
     * @param fileName      文件名称
     * @param data          要保存到文件的内容
     * @param charsetName   文件编码格式，若传null值，则使用JVM默认字符编码格式
     * @throws Exception
     */
    public static void saveDataToFileUsingCharset(String directoryPath, String fileName, String data, String charsetName) throws Exception {
        String filePath = directoryPath + File.separator + fileName;
        checkDirectory(directoryPath);

        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(filePath);
            file.createNewFile();
            if (charsetName != null) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), charsetName));
            } else {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            }

            bufferedWriter.write(data);
        } catch (Exception e) {
            throw new Exception("保存数据到文件失败：" + e.getMessage());
        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }


    /**
     * 保存数据到文件
     * 会覆盖文件里面的内容
     *
     * @param directoryPath 文件目录
     * @param fileName      文件名称
     * @param data          要保存到文件的内容
     * @throws Exception 异常
     */
    public static void saveDataToFile(String directoryPath, String fileName, String data) throws Exception {
        checkDirectory(directoryPath);
        String filePath = directoryPath + File.separator + fileName;
        saveDataToFile(filePath, data);
    }

    /**
     * 检查目录是否存在
     * 若目录不存在，则创建目录
     *
     * @param directoryPath 目录路径
     */
    public static void checkDirectory(String directoryPath) throws Exception {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean createDirectoryResult = directory.mkdirs();
            if (!createDirectoryResult) {
                throw new Exception("创建" + directoryPath + "目录失败");
            }
        }
    }


    /**
     * 检查文件是否存在：若文件不存在，则抛出异常
     *
     * @param directoryPath 文件目录
     * @param fileName      文件名称
     * @throws FileNotFoundException 异常
     */
    private static void checkFile(String directoryPath, String fileName) throws FileNotFoundException {
        String filePath = directoryPath + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException(fileName + "文件不存在！读取目录为：" + directoryPath);
        }
    }


    /**
     * 读取批处理文件
     * 按行读取文件里面的内容，存放到字符串当中返回
     *
     * @param directoryPath 文件父目录
     * @param fileName      文件名称
     * @return 文件内容
     * @throws Exception 异常
     */
    public static String readMfsBatchFileContent(String directoryPath, String fileName) throws Exception {
        checkFile(directoryPath, fileName);
        String filePath = directoryPath + File.separator + fileName;
        return readFileContentToString(filePath, null);
    }

    /**
     * 读取批处理文件
     * 按行读取文件里面的内容，存放到字符串当中返回
     *
     * @param directoryPath 文件父目录
     * @param fileName      文件名称
     * @param charsetName   编码格式
     * @return 文件内容
     * @throws Exception 异常
     */
    public static String readMfsBatchFileContent(String directoryPath, String fileName, String charsetName) throws Exception {
        checkFile(directoryPath, fileName);
        String filePath = directoryPath + File.separator + fileName;
        return readFileContentToString(filePath, charsetName);
    }

    /**
     * 按行读取文件里面的内容，存放到字符串当中返回
     *
     * @param batchFilePath 批处理文件目录
     * @param charsetName   编码格式。传空值则使用JVM默认字符集
     * @return 返回MFS多域串
     * @throws Exception 异常
     */
    private static String readFileContentToString(String batchFilePath, String charsetName) throws Exception {
        StringBuilder data = new StringBuilder();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(batchFilePath);
            // bufferedReader = new BufferedReader(fileReader);
            if (charsetName != null) {
                // Java读取数据流的时候，若不指定数据流的编码方式，否则将使用本地环境中的默认字符集。
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(batchFilePath), charsetName));
            } else {
                bufferedReader = new BufferedReader(fileReader);
            }

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                data.append(line);
            }
        } catch (Exception e) {
            throw new Exception("读取批处理文件内容错误：" + e.getMessage());
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
     * @param directoryPath 文件父目录
     * @param fileName      文件名称
     * @return 文件内容
     * @throws Exception 异常
     */
    public static List<String> readFileContentByLineToList(String directoryPath, String fileName) throws Exception {
        checkFile(directoryPath, fileName);
        String filePath = directoryPath + File.separator + fileName;
        return readFileContentByLineToListUsingCharset(filePath, null);
    }

    /**
     * 按行读取文件内容，将其内容转化成字符串返回
     *
     * @param directoryPath 文件父目录
     * @param fileName      文件名称
     * @param charsetName   编码格式。传空值则使用JVM默认字符集
     * @return 文件内容
     * @throws Exception 异常
     */
    public static List<String> readFileContentByLineToList(String directoryPath, String fileName, String charsetName) throws Exception {
        checkFile(directoryPath, fileName);
        String filePath = directoryPath + File.separator + fileName;
        return readFileContentByLineToListUsingCharset(filePath, charsetName);
    }

    /**
     * 按行读取文件内容，将其内容转化成字符串返回
     *
     * @param filePath    文件路径
     * @param charsetName 编码格式。传空值则使用JVM默认字符集
     * @return 文件行内容数组
     * @throws Exception 异常
     */
    private static List<String> readFileContentByLineToListUsingCharset(String filePath, String charsetName) throws Exception {
        List<String> stringList = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(filePath);
            if (charsetName != null) {
                // Java读取数据流的时候，若不指定数据流的编码方式，否则将使用本地环境中的默认字符集。
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charsetName));
            } else {
                bufferedReader = new BufferedReader(fileReader);
            }
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
