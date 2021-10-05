package com.mathpassionkasiski;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;



public class Kasiski {
    

	public static void main(String[] args) {
		//double moyenne=0;
		//path = args[0]
		//args[1] est la longueur des sous chaines. On utilise Integer.parseInt(args[1]) pour convertir en entier.
		//KasiskiPrintLength(args[0], Integer.parseInt(args[1]));
		//System.out.println(KasiskiPrintLengthGCD(args[0], Integer.parseInt(args[1])));
//		for (int i =2; i<Integer.parseInt(args[1]) +2; i++) {
//			double start = System.currentTimeMillis();
//			KasiskiPrintLength(args[0], i);
//			double end = System.currentTimeMillis();
//			moyenne+=(TimeInSecondes(end - start));
//		}
//		System.out.println(moyenne/Integer.parseInt(args[1]));
		// Kasiski Version avec PGCD
//		double moyenne1=0;
		//for (int i =2; i<Integer.parseInt(args[1]) +2; i++) {
//			double startime = System.currentTimeMillis();
		    //System.out.println(KasiskiPrintLengthGCD(args[0], i));
//			double endtime = System.currentTimeMillis();
//			moyenne1+=(TimeInSecondes(endtime - startime));
	//}
//		System.out.println(moyenne1/Integer.parseInt(args[1]));
//		
//		
//		
//	
		for (int i = 2; i < 14; i++) {
			KasiskiPrintLength("D:\\MIAGE\\poly.txt", i);
			System.out.println();
		}
		
}
	public static void KasiskiPrintLength(String pathtext, int n){
		String chaine ="";
		try {
			File filename  = new File(pathtext);
			FileInputStream path = new FileInputStream(filename);
			byte [] b = new byte[(int)filename.length()];
			path.read(b);
			chaine = new String(b, "UTF-8").replaceAll(" ","").replaceAll("\n","").replaceAll("\rn","").replaceAll("\r","");
			path.close();
	}catch(IOException e){
		e.printStackTrace();
		
	}
		
		int size =  chaine.length();
		// Creer un hashmap qui contient la sous chaine et la position où elle apparait
		HashMap<String , int[]> hashmap = new HashMap<String,int[]>();
		for(int i = 0; i <=size-n; i++) {
			//ArrayList<Integer> list = new ArrayList<Integer>();
			String souschaine = chaine.substring(i,i+n);
			if (hashmap.containsKey(souschaine)) {
				System.out.println(i-hashmap.get(souschaine)[0]);
			   hashmap.get(souschaine)[0]=i;
		    }
			
			else {
				hashmap.put(souschaine,new int[] {i});
			}
		
			
		}	
} 
	//Kasiski version avec PGCD
	public static int Pgcd(int a, int b) {
		while(b!=0) {
			int t = a;
			a = b;
			b = t%b;
		}
		return a;
	}
	public static double TimeInSecondes(double n) {
		return n/1000;
	}
public static int KasiskiPrintLengthGCD(String pathtext, int n) {
	String chaine ="";
	try {
		File filename  = new File(pathtext);
		FileInputStream path = new FileInputStream(filename);
		byte[] b = new byte[(int)filename.length()];
		path.read(b);
		chaine = new String(b, "UTF-8").replaceAll(" ","").replaceAll("\n","").replaceAll("\rn","").replaceAll("\r","");
		path.close();
	}catch(IOException e){
	e.printStackTrace();
	
}
		
	// Creer un hashmap qui contient la sous chaine et la position où elle apparait
		int l = chaine.length();
		int k=0;
		HashMap<String,ArrayList<Integer>> hashmap = new HashMap<String,ArrayList<Integer>>();
		for (int i = 0; i <= l-n; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			String souschaine = chaine.substring(i,i+n);
			list.add(i);
			if (!hashmap.containsKey(souschaine)) {
				
				hashmap.put(souschaine, list);
			} else {
				hashmap.get(souschaine).add(i);
			}
			
		}
		//Recherche de la taille de la sous chaine
		for (ArrayList<Integer> iterable_element : hashmap.values()) {
			if (iterable_element.size()>1){
				for (int i = 0; i < iterable_element.size()-1; i++) {
					if (k==0){
						k=iterable_element.get(i+1)-iterable_element.get(i);
					} else {
						k=Pgcd(k,iterable_element.get(i+1)-iterable_element.get(i));
					}
				}
			}
		}
		return k;
	}
	
	
	
}
