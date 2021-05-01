package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import stockanalyzer.ctrl.Controller;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){

		try {
			for (String s : ctrl.process("ABC")){
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("Connection Problems... pls try again later");
		}

	}

	public void getDataFromCtrl2(){
		try {
			for (String s : ctrl.process("GME")){
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("Connection Problems... pls try again later");
		}
	}

	public void getDataFromCtrl3(){
		try {
			for (String s : ctrl.process("GOOG")){
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("Connection Problems... pls try again later");
		}

	}
	public void getDataFromCtrl4(){
		try {
			for (String s : ctrl.process("AAPL")){
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("Connection Problems... pls try again later");
		}
	}
	
	public void getDataForCustomInput() {

		Scanner scanner = new Scanner(System.in);
		System.out.println("pls enter Ticker");
		String a = scanner.nextLine();

		try {
			for (String s : ctrl.process(a)){
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("Connection Problems... pls try again later");
		}
	}



	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "ABC Data", this::getDataFromCtrl1);
		menu.insert("b", "GME Data", this::getDataFromCtrl2);
		menu.insert("c", "GOOG Data", this::getDataFromCtrl3);
		menu.insert("d", "AAPL Data",this::getDataFromCtrl4);
		menu.insert("z", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine() 
	{
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException e) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 
	{
		Double number = null;
		while(number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			}catch(NumberFormatException e) {
				number=null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if(number<lowerlimit) {
				System.out.println("Please enter a higher number:");
				number=null;
			}else if(number>upperlimit) {
				System.out.println("Please enter a lower number:");
				number=null;
			}
		}
		return number;
	}
}
