import java.awt.List;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Comparator;


public class Stock {
	String symbol;
	Type type;
	Integer lastDividend;
	Double fixedDividend;
	Integer parValue;
	ArrayList<Trade> trades;//a stock can support more than one trade

	public Stock(String symbol, Type type, Integer lastDividend,
			Double fixedDividend, Integer parValue) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public ArrayList<Trade> getTrades() {
		return trades;
	}

	public void addTrade(Trade trade){
		if (this.trades==null)
			this.trades = new ArrayList<Trade>();
		this.trades.add(trade);
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Integer getLastDividend() {
		return lastDividend;
	}
	public void setLastDividend(Integer lastDividend) {
		this.lastDividend = lastDividend;
	}
	public Double getFixedDividend() {
		return fixedDividend;
	}
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}
	public Integer getParValue() {
		return parValue;
	}
	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	public Double dividendYield(Double price) throws Exception{
		if (price>0 && price!=null)
		{
			if (this.type==Type.COMMON)
				return this.lastDividend/price;
			else if (this.type==Type.PREFERRED)
				return this.fixedDividend*this.parValue/price;

		}
		else throw new Exception("Price must be greater than 0");
		return null;
	}

	public Double PERatio(Double price) throws Exception{
		//the formula for P/E Ratio is Price/Dividend, I`m not sure about which 
		// dividend is that, so I considered that dividend=dividendYield

		if (price>0 && price!=null)
		{
			Double divY = this.dividendYield(price);
			if (divY>0 && divY!=null)
				return price/divY;
			else throw new Exception("Dividend Yield is null or less than 0, P/E Ration cannot be calculated");
		}
		else 
			throw new Exception("Price must be greater than 0");
	}

	public Double volumeWeightedStockPrice(){

		Double buff= 0.0; //the sum of (price*quantity)
		int totalQ=0; //the sum of quantity
		ArrayList<Trade> lastTrades = this.getLastTrades(15);//15 is the number of minutes
		
		if (lastTrades!=null && lastTrades.size()>0) //check if there are trades
		{	for (Trade t:lastTrades){
			buff+=(t.getPrice()*t.getQuantity());
			totalQ+=t.getQuantity();
			}
			return buff/totalQ;
		}
		else return 0.0;
	}
	
	protected ArrayList<Trade> getLastTrades(int minutes){

		ArrayList<Trade> lastTrades=new ArrayList<Trade>();
		Instant now = Instant.now();
		for(Trade t:this.trades)
			if (t.getTime().plusSeconds(minutes*60).isAfter(now))	//	check la trades after the last 15 minute
				lastTrades.add(t);

		return lastTrades;
	}
}
