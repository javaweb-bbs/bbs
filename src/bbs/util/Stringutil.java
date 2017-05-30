package bbs.util;

public class Stringutil {
	
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean isNotEmpty(String str){
		if(str!=null&&!"".equals(str.trim())){
			return true;
		}
		else{
			return false;
		}
	}
	public static String arrToString(String[] arr) {
		String result = "";
		if (arr.length != 0) {
			for (int i = 0; i < arr.length; i++) {
				result += arr[i];
			}
		}
		return result;
	}
}
