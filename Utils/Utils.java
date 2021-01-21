package Utils;

import java.io.*;
import java.util.List;
import java.util.Random;

public class Utils {
  
  public static void print_int_array(int[] array){
    for(int i=0; i<array.length; i++){
      System.out.print(array[i] + " ;"); 
    } 
    System.out.println();
  }
  
  public static int randInt(int min, int max) {
    Random rand = new Random();
    int randomNum = rand.nextInt((max - min) + 1) + min;
    return randomNum;
  }
  
  public static double[] get_numbers(int c){
    System.out.println("Bitte geben Sie " + c + (c==1 ? " Zahl" : " Zahlen") + " ein: "); 
    double[] zahlen = new double[c];
    for(int i=0; i<c; i++){
      double num = Double.parseDouble(input());
      zahlen[i] = num; 
    }
    return zahlen;
  }
  
  public static int[] convert_to_ints(double[] doubles){
    int[] zahlen = new int[doubles.length];
    for(int i=0; i<doubles.length; i++){
      zahlen[i] = (int) doubles[i];
    } 
    return zahlen;
  }
  

  public static int get_number(String message, int min, int max, List<Integer> exclude){
    while(true){
      int i = get_number(message, min, max);
      if(exclude.contains(i)){
        System.out.println("UNgültige Zahl! Sie darf nicht einer dieser Zahlen beinhalten: " + exclude.toString());
        continue;
      }
      return i;
    }
  }
  
  public static int get_number(String message, int min, int max){
    while(true){
      int i = get_number(message);
      if(i<min || i>max){
        System.out.println("Ungültige Zahl! Sie muss zwischen " + min + " und " + max + " liegen.");
        continue;
      }
      return i; 
    } 
  }

  public static int get_number(String message, List<Integer> exclude){
    while(true){
      int i = get_number(message);
      if(exclude.contains(i)){
        System.out.println("Ungültige Zahl! Sie darf nicht einer dieser Zahlen beinhalten: " + exclude.toString());
        continue;
      }
      return i;
    }
  }
  
  public static int get_number(String message){
    String s = input(message);
    try{
      return Integer.parseInt(s);
    } catch (NumberFormatException e) {
      System.out.println("\""+ s + "\"" + " ist keine Nummer!");
      return get_number(message);
    }
  }

  public static String input(String message, List<String> allowed){
    while(true){
      String s = input(message);
      if(allowed.contains(s)){
        return s;
      }
      System.out.println("Ungültige Eingabe! Sie darf nur einer dieser Eingaben enthalten: " + allowed.toString());
    }
  }
  
  public static void print_menu(String message, String[] options){
    System.out.println(message);
    for(int i=0; i<options.length; i++){
      System.out.println(i+1+". "+options[i]);
    } 
  }
  
  /*   public static int[] get_numbers(int c){
  System.out.println("Bitte geben Sie " + c + (c==1 ? " Zahl" : " Zahlen") + " ein: "); 
  int[] zahlen = new int[c];
  for(int i=0; i<c; i++){
  int num = Integer.parseInt(input());
  zahlen[i] = num; 
  }
  return zahlen;
  }*/ 
  
  
  
  //Methode zur Texteingabe
  public static String input(String message){
    System.out.print(message);
    return input(); 
  }
  public static String input() {
    String s ="";
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    
    try {
      s=input.readLine();       
      
    } catch(IOException e) {
      System.out.println("Ein Fehler ist aufgetreten: \n" + e);
    } 
    return s;
  }
  
}