package com.grishin.mboxparser.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
	
	public static ArrayList<String> parseAddrs(InputStream mbox) throws FileNotFoundException{
		ArrayList<String> result = new ArrayList<String>();
		Scanner sc = new Scanner(mbox);
		while(sc.hasNextLine()){
			String str = sc.nextLine();
			if(str.startsWith("From: ")){
				str = str.substring(str.indexOf(": ")+2);				
				if(str.contains("@")){
					int at_pos = str.lastIndexOf('@');
					String login = str.substring(0, at_pos);
					String domen = str.substring(at_pos+1);
					int ws_pos = login.lastIndexOf(' ');
					if(ws_pos>-1){
						login = login.substring(ws_pos+1);
					}
					String addr = login+"@"+domen;
					addr = addr.replaceAll("[<|>]", "");
					result.add(addr);
				}
			}
		}
		sc.close();
		return result;
	}
	
	public static ArrayList<String> replaceAliases(File aliases, ArrayList<String> froms) throws FileNotFoundException{
		Scanner sc = new Scanner(aliases);
		ArrayList<String[]> als = new ArrayList<String[]>();
		while(sc.hasNextLine()){
			String str = sc.nextLine();
			String[] addrs = str.split(" ");
			als.add(addrs);
		}
		sc.close();		
		
		for(int m=0;m<als.size();m++){
			String[] addrs = als.get(m);
			for(int n=0; n<addrs.length;n++){
				for(int i=0;i< froms.size();i++){
					String from = froms.get(i);							
					if(from.toLowerCase().equals(addrs[n].toLowerCase())){
						String alias=addrs[0];
						froms.set(i, alias);
					}
				}
			}
		}
		return froms;
	}

}
