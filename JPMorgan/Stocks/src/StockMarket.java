import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

//this class is used to calculate the GBCE index of prices
public class StockMarket {


	
	public static double GBCE(List<Stock> stocks){
		List<Trade> trades=new ArrayList<Trade>();
		List<Double> prices = new ArrayList<Double>();
		
		for (Stock s: stocks)
			if (s.getTrades()!=null)
			trades.addAll(s.getTrades());
		
		for (Trade t: trades)
			prices.add(t.getPrice());
		
		double buff=1;
		for (Double f:prices)
			buff*=f;

		return nRoot(buff,prices.size(),0);
	}

	public static double GBCEPrices(List<Double> prices){
		double buff=1;
		
		for (Double f:prices)
			buff*=f;

		return nRoot(buff,prices.size(),0);
	}

	public static double nRoot( double nr,int root, double p) {
		//p is the accepted error
		if(nr < 0) {
			System.err.println("Number is < 0");//only positive numbers
			return -1.0;
		} else if(nr == 0) {
			return 0;
		}
		double prev = nr;
		double x = nr / root;
		while(Math.abs(x - prev) > p) {
			prev = x;
			x = ((root - 1.0) * x + nr / Math.pow(x, root - 1.0)) / root;
		}
		return x;
	}


}
