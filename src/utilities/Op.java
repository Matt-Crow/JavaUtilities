package utilities;

import static java.lang.System.out;

public class Op {
	public static void log(String data){
		out.println(data);
	}
	public static void log(int[] items){
		for(int i = 0; i < items.length; i++){
			log(Integer.toString(items[i]));
		}
	}
}
