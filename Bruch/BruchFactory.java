package Bruch;

import Utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

import Exceptions.BruecheRechnerException;


public class BruchFactory {

  public static Bruch bruch_factory_cmd() throws BruecheRechnerException {
    System.out.println("Geben Sie den Zähler ein! ");
    int zaehler = Utils.get_number("> ");
    System.out.println("Geben Sie den Nenner ein! ");
    int nenner = Utils.get_number("> ", new ArrayList<Integer>(Arrays.asList(0)));
    return new Bruch(zaehler, nenner);
  } 
}


