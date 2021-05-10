package stockanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import stockanalyzer.ctrl.Controller;
import stockanalyzer.downlader.Downloader;
import stockanalyzer.downlader.ParallelDownloader;
import stockanalyzer.downlader.SequentialDownloader;

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

	public void getDataForDownloadSeq(){
		ArrayList<String> tickerList = new ArrayList<>();
		tickerList.add("AAPL");
		tickerList.add("GME");
		tickerList.add("GOOG");
		tickerList.add("ABC");
		tickerList.add("TTD");
		tickerList.add("DOGE-USD");
		tickerList.add("BABA");
		tickerList.add("QCOM");
		tickerList.add("AMZN");
		tickerList.add("MSFT");
		tickerList.add("AZFL");
		tickerList.add("DD");



		SequentialDownloader seq = new SequentialDownloader();

		long startTime = System.currentTimeMillis();
		ctrl.downloadTickers(seq, tickerList);
		long stopTime = System.currentTimeMillis();

		System.out.println((stopTime - startTime) + " milli seconds");

	}

	public void getDataForDownloadPar(){

		ArrayList<String> tickerList = new ArrayList<>();
		tickerList.add("AAPL");
		tickerList.add("GME");
		tickerList.add("GOOG");
		tickerList.add("ABC");
		tickerList.add("TTD");
		tickerList.add("DOGE-USD");
		tickerList.add("BABA");
		tickerList.add("QCOM");
		tickerList.add("AMZN");
		tickerList.add("MSFT");
		tickerList.add("AZFL");
		tickerList.add("DD");



		ParallelDownloader seq = new ParallelDownloader();


		long startTime = System.currentTimeMillis();
		ctrl.downloadTickers(seq, tickerList);
		long stopTime = System.currentTimeMillis();

		System.out.println((stopTime - startTime) + " milli seconds");

	}



	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interfacx");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "ABC Data", this::getDataFromCtrl1);
		menu.insert("b", "GME Data", this::getDataFromCtrl2);
		menu.insert("c", "GOOG Data", this::getDataFromCtrl3);
		menu.insert("d", "AAPL Data",this::getDataFromCtrl4);
		menu.insert("z", "Choice User Imput:",this::getDataForCustomInput);
		menu.insert("s", "Download Sequential",this::getDataForDownloadSeq);
		menu.insert("p", "Download Parallel",this::getDataForDownloadPar);
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
