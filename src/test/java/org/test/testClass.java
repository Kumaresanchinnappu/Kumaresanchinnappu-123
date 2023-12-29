package org.test;

public class testClass {
	
	public static void main(String[] args) {
		
		
		String retString = "Mountain Voyage Minimalist Wallet for Men - Slim RFID Wallet I Scratch Resistant, Credit Card Holder & Money Clip, Easily Removable Money & Cards, Mens Wallets\r\n";
		
		String prodString = "Mountain Voyage Minimalist Wallet for Men - Slim RFID Wallet I Scratch Resistant, Matte Carbon Fiber Credit Card…";
	
		String substring = prodString.substring(0, prodString.indexOf(','));
		
		System.out.println(retString.contains(substring));
	
	}

}
