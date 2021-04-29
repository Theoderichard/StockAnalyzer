package stockanalyzer.ctrl;

import org.w3c.dom.ls.LSOutput;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;
import yahoofinance.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class Controller {
		
	public void process(String ticker) throws IOException {
		System.out.println("Start process");



		ArrayList<String> name = new ArrayList<>();
		//TODO implement Error handling 

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse
		Stock stock = null;
		try {
			 stock = yahoofinance.YahooFinance.get(ticker);
			 var result = stock.getHistory().stream()
					 .mapToDouble(q -> q.getClose().doubleValue())
					 .count();


			 //stock.getHistory().forEach(q -> System.out.println(q));

			//System.out.println(result);
		} catch (IOException e) {
			 e.printStackTrace();
		}

		getData(ticker);


	}
	

	public QuoteResponse getData(String searchString) throws IOException {

		YahooFinance yahooFinance = new YahooFinance();
		ArrayList<String> tickers = new ArrayList<>();
		tickers.add(searchString);
		YahooResponse yahooResponse = yahooFinance.getCurrentData(tickers);
		QuoteResponse quotes = yahooResponse.getQuoteResponse();

		return quotes;
	}


	public void getRecentMax(String ticker){
		Stock stock = null;
		try {
			stock = yahoofinance.YahooFinance.get(ticker);
			var result = stock.getHistory().stream()
					.mapToDouble(q -> q.getClose().doubleValue())
					.max();
			//stock.getHistory().forEach(System.out::println);

			System.out.println(result);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	public void getRecentAverage(){
		//TODO Durchschnitt der letzten Tage errechnen
	}

	public void getRecords(){
		//TODO Anzahl der Datensätze errechnen
	}

}
