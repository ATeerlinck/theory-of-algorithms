

import java.util.Hashtable;

public class HuffmanDecoder {

	public static String decode(String encodedMsg, Hashtable<String, Character> encodingToCharMapping) { // complete this method
		String encode = "", decodedMsg = "";
		int n = encodedMsg.length();
		for (int i = 0; i < n; i++) {
			encode += encodedMsg.charAt(i);
			if(encodingToCharMapping.containsKey(encode)){
				char c = encodingToCharMapping.get(encode);
				decodedMsg += c;
				encode = "";  
			}
		}
		return decodedMsg;
	}
}
