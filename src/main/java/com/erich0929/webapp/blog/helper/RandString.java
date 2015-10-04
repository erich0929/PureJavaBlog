package com.erich0929.webapp.blog.helper;

//import java.util.*;

public class RandString {
	public static final String chars = "abcdefghijkmnlopqrstuvwxyz0123456789ABCDEFGHIJKMNLOPQRSTUVWXYZ";

	public static String getRandString(int length) {
		StringBuffer ret = new StringBuffer(length);
		for (int i = 0; i < length; i++) {
			ret.append(chars.charAt(((int) (Math.random() * 100000))
					% chars.length()));
		}
		return ret.toString();
	}

}
