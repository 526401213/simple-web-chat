package com.kevinblandy.simple.webchat.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static String[] NUMBERS = new String[10];
	public static void main(String[] args) throws IOException{
		System.out.println(getLed(747692844));
	}
	
	public static String getLed(int num) throws IOException{
		StringBuilder sb = new StringBuilder(); 
		char[] chars = (num+"").toCharArray();
		List<String> arrays = new ArrayList<>();
		for(char ch : chars){
			String ss = NUMBERS[Integer.parseInt(String.valueOf(ch))];
			arrays.add(ss);
		}
		for(String str : arrays){
			BufferedReader reader = new BufferedReader(new StringReader(str));
			String line = reader.readLine();
			sb.append(line);
			sb.append(" ");
		}
		sb.append("\n");
		for(String str : arrays){
			BufferedReader reader = new BufferedReader(new StringReader(str));
			String line = reader.readLine();
			line = reader.readLine();
			sb.append(line);
			sb.append(" ");
		}
		sb.append("\n");
		for(String str : arrays){
			BufferedReader reader = new BufferedReader(new StringReader(str));
			String line = reader.readLine();
		 	line = reader.readLine();
			line = reader.readLine();
			sb.append(line);
			sb.append(" ");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	static{
		NUMBERS[0] = "|¯|\n"
					+"| |\n"
					+"|_|\n";
		
		NUMBERS[1] = "  |\n"
					+"  |\n"
					+"  |\n";
		
		NUMBERS[2] = " ¯|\n"
					+" - \n"
					+"|_ \n";
		
		NUMBERS[3] = " ¯|\n"
					+" -|\n"
					+" _|\n";
		
		NUMBERS[4] = "| |\n"
					+" -|\n"
					+"  |\n";
		
		NUMBERS[5] = "|¯\n"
					+" -\n"
					+" _|\n";
		
		NUMBERS[6] = "|¯\n"
					+"|- \n"
					+"|_|\n";
		
		NUMBERS[7] = "|¯|\n"
					+"  |\n"
					+"  |\n";
		
		NUMBERS[8] = "|¯|\n"
					+"|-|\n"
					+"|_|\n";
		
		NUMBERS[9] = "|¯|\n"
					+" -|\n"
					+" _|\n";
	}
}

