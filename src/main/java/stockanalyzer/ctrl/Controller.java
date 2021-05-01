package stockanalyzer.ctrl;

import org.w3c.dom.ls.LSOutput;
import yahooApi.YahooFinance;
import yahooApi.beans.QuoteResponse;
import yahooApi.beans.YahooResponse;
import yahoofinance.Stock;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {
		
	public ArrayList<String> process(String ticker) throws IOException{
		System.out.println("Start process");

		ArrayList<String> data = new ArrayList<>(getData(ticker));

		//TODO implement Error handling

		//TODO implement methods for
		//1) Daten laden
		//2) Daten Analyse

		Stock stock = null;

		Calendar from = Calendar.getInstance();
		from.add(Calendar.WEEK_OF_MONTH, -2);

		stock = yahoofinance.YahooFinance.get(ticker);
		List<HistoricalQuote> h = stock.getHistory(from, Interval.DAILY);

		data.add("Recent Max: " + getRecentMax(h));
		data.add("Recent Average: " + getRecentAverage(h));
		data.add("Count of Records: " + getRecords(h));

		return data;

	}
	

	public ArrayList<String> getData(String searchString) throws IOException {

		ArrayList<String> data = new ArrayList<>();

		YahooFinance yahooFinance = new YahooFinance();
		ArrayList<String> tickers = new ArrayList<>();
		tickers.add(searchString);
		YahooResponse yahooResponse = yahooFinance.getCurrentData(tickers);
		QuoteResponse quotes = yahooResponse.getQuoteResponse();


		quotes.getResult()
				.forEach(q-> data.add("Name: " + q.getLongName()));

		quotes.getResult()
				.forEach(q -> data.add("Ask: "  + q.getAsk().toString()));

		return data;
	}


	public double getRecentMax(List<HistoricalQuote> history){


			return  history.stream()
					.mapToDouble(q-> q.getClose().doubleValue())
					.max()
					.orElse(0.0);

	}

	public double getRecentAverage(List<HistoricalQuote> history){

			return  history.stream()
					.mapToDouble(q-> q.getClose().doubleValue())
					.average()
					.orElse(0.0);

	}

	public int getRecords(List<HistoricalQuote> history){

			return (int) history.stream()
					.mapToDouble(q->q.getClose().intValue())
					.count();

	}

}
