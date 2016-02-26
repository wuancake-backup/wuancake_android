package com.zyp;
	class FreshJuice{
		enum FreshJuiceSize {SMALL,MEDIUM,LAUGE}
		FreshJuiceSize size;
	}
public class FreshJuiceTest {

	public static void main(String[] args) {
		FreshJuice juice = new FreshJuice();
		juice.size =FreshJuice.FreshJuiceSize.MEDIUM;
		System.out.println("Size:" +juice.size);

	}

}
