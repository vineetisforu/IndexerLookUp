package com.hs.contact;

import java.io.IOException;
import java.util.Scanner;

public class Invoker {

	public static void main(String[] args) throws IOException {
		
		CharacterMapper cMapper = new CharacterMapper();
		
		cMapper.intializeMap();
		
		Scanner scanner = new Scanner(System.in);
		String s = null;
		
		System.out.println("1) Add contact 2) Search 3) Exit");
		
		while(!(s=scanner.nextLine().trim()).equals("3")) {
			
			try {
				if(Integer.parseInt(s)>3) {
					System.out.println("Not a Valid Choice, please enter --> 1) Add contact 2) Search 3) Exit");
					continue;
				}
			} catch (NumberFormatException e) {

				System.out.println("Not a Valid Choice, please enter --> 1) Add contact 2) Search 3) Exit");
				continue;
			}
			
			System.out.println("Enter Name : ");
			String input = scanner.nextLine().trim();
			
			if(s.equals("1"))
				cMapper.indexName(input);
			else
				if(s.equals("2"))
					System.out.println(cMapper.searchName(input));
			
			System.out.println("1) Add contact 2) Search 3) Exit");
		}
		
		System.out.println("Happy Searching");
	}
}
