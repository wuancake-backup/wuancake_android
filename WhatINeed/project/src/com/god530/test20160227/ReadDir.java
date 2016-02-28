package com.god530.test20160227;


public class ReadDir {

	@SuppressWarnings("null")
	public static void main(String[] args) {
//		File file = null;
//		String[] paths;
//		
//		file = new File("c:\\");
//		paths = file.list();
//		
//		for (String string : paths) {
//			System.out.println(string);
//		}
		
		try {
			
			File2 dir = new File2();
			dir.list(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class File2 {
	
	public File2() {
	}
	

	public String[] list(String dirname) {
		if(dirname == null){
			throw new NullPointerException();
		}
		System.out.println("------------");
		return new String[]{"",""};
	}
	
}
