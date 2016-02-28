package com.god530.test20160227;

import java.io.File;

public class CreateDir {
	
	public static void main(String[] args) {
		String dirname = "c:\\test";
		File d = new File(dirname);
		d.mkdirs();
	}
	
	
}
