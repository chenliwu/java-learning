package base.test20191110;

/**
 * @author chenlw
 * @since 2019/11/10
 */
public class InitClassAttributeTester {


    public static void main(String[]args){
        InitClassAttributeTester tester = new InitClassAttributeTester();
        System.out.println("int value:"+tester.getIntValue());
        System.out.println("char value:"+tester.getCharValue());
    }

    /**
     * 默认值为0
     */
    private int intValue;

    /**
     * 默认值为(null)
     */
    private char charValue;


    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public char getCharValue() {
        return charValue;
    }

    public void setCharValue(char charValue) {
        this.charValue = charValue;
    }
}
