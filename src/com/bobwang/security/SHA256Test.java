package com.bobwang.security;

import com.mstr.security.Hasher;
import com.mstr.security.impl.MD5Hasher;
import com.mstr.security.impl.SHA256Hasher;

public class SHA256Test {

	public static void main(String[] args) {
//		SHA256Hasher hasher = new SHA256Hasher();
		Hasher<String, String> hasher = new MD5Hasher();
		System.out.println(hasher.hash("hello"));
		hasher = new SHA256Hasher();
		System.out.println(hasher.hash("$changemeFSLFU)OUfjklfajlfa8i03242+*()_&_"));
	}

}
