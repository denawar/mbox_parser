package com.grishin.mboxparser.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {	
	
	public static void main(String[] args) {
		File aliases = null;
		File mbox = null;
		ArrayList<String> froms = new ArrayList<String>();
		if (args.length==1){
			mbox = new File(args[0]);
		}
		if (args.length==2){
			if(!args[0].equals("-a")){
				mbox = new File(args[0]);
			}			
			aliases = new File(args[1]);
		}
		try {
			if(mbox!=null){
				froms = Parser.parseAddrs(new FileInputStream(mbox));
			}
			else{
				froms = Parser.parseAddrs(System.in);
			}
			System.out.println();
			
			if(aliases!=null){
				froms = Parser.replaceAliases(aliases, froms);
			}				
			
			ArrayList<String> used = new ArrayList<String>();
			ArrayList<Integer> counts = new ArrayList<Integer>();
			
			for(int i=0;i< froms.size();i++){
				String from = froms.get(i);
				if(used.contains(from)){
					/*
					nothing to do here
					*/
				}
				else{
					used.add(from);
					int count = Collections.frequency(froms, from);
					counts.add(count);
				}
			}
			QuickSort sorter = new QuickSort();
			sorter.sort(counts, used);
			
			for(int i=0;i< used.size();i++){
				String str = used.get(i)+": "+counts.get(i);
				System.out.println(str);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
}
