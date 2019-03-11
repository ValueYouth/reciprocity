package algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformNumber {
	/**大写数字数组*/
    private final static String[] UPPER_NUMBER = {"零","一","二","三","四","五","六","七","八","九"};

    /**单位*/
    private final static String[] UNIT_NUMBER = {"", "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"}; 

    
    public static void main(String[] args) {
        TransformNumber transformNumber = new TransformNumber();
        
        String number = "100001000"; // 输入的数字字符串
        
        System.out.println(transformNumber.transformToUpperNumber(number));
    }
    
    /**
     * 方法说明：将小写数字转换成大写数字。
     * @param number 源小写数字字符串
     * @return 目标输出大写数字
     */
    private String transformToUpperNumber(String number) {
    	StringBuffer outNumber = new StringBuffer(); // 定义输出数字字符串
    	boolean prePositionZero = true; // 前一个字符是否是零
    	number = preHandleToNumber(number); // 预处理数字字符串
    	int length = number.length();
    	
    	for (int i = length - 1; i >= 0; i--) { // 从各位开始处理
    		int properIndex = length - i - 1; // 从个位开始
    		
    		if (0 == properIndex % 4) { // 个位（或万位、亿位）为零
    			if (i - 4 >= 0 && "0000".equals(number.substring(i - 3, i + 1))){
    				/*如果个位（万位或亿位）到千位（千万或千亿）都为零*/
    				if (!prePositionZero){
    					outNumber.insert(0, UPPER_NUMBER[0]); // 设置为零
    					prePositionZero = true;
    				}
    				
    				i -= 3; // 索引向下减4个单位（还有i--）
    				continue;
    			}
    			
    			prePositionZero = true;
    		}
    		
    		int singleNum = getSingleNum(number, i); // 当前索引对应的单个数字
    		if (0 == singleNum) {
    			if (!prePositionZero) { // 当前为零且前一位不为零，则设置为零
    				outNumber.insert(0, UPPER_NUMBER[0]); // 设置为零
    				prePositionZero = true; // 该位置已设置零（下一字符的前一位）
    			}
    			
    			if (0 == (properIndex) % 4) { // 当前位为零，且在万位（或亿）上，设置单位
    				outNumber.insert(0, UNIT_NUMBER[properIndex]);
    			}
    		}
    		else {
    			outNumber.insert(0, UPPER_NUMBER[singleNum] + UNIT_NUMBER[properIndex]);
    			prePositionZero = false; // 下一字符前一位不为零
    		}
    	}
    	
    	return outNumber.toString();
    }
    
    /**
     * 方法说明：对输入的字符串进行预处理
     * @param number 输入的数字字符串
     * @return 处理后的数字字符串
     * @throws Exception 超出处理范围，抛出异常
     */
    private String preHandleToNumber(String number) {
    	number = number.replaceAll("^(0+)", ""); // 处理前面多余的零
    	if (!isNumber(number) || number.length() > 12) { // 处理范围的情况
    	
    		try {
				throw new Exception("超出处理范围，请重新输入！");
			} 
    		catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	return number;
    }
    
    /**
     * 方法说明：检查输入的字符串是否是数字。
     * @param number 源字符串
     * @return true，输入的是数字字符串；否则，返回false
     */
    private boolean isNumber(String number) {
    	Pattern pattern = Pattern.compile("[0-9]*");
    	Matcher matcher = pattern.matcher(number);
    	
    	if (matcher.matches()) {
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * 方法说明：得到指定索引的数字字符，并转成整型返回
     * @param number 源数字字符串
     * @param index 字符串索引
     * @return 指定索引处的数字
     */
    private int getSingleNum(String number, int index) {
    	return Integer.parseInt(number.substring(index, index + 1));
    }
}
