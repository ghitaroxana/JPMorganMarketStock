import java.sql.Timestamp;
import java.time.Instant;


public class Trade {

	Instant time;
	//for BUY indicator the quantity will be positive and for SELL indicator the quantity will be negative
	int quantity;
	Indicator indicator;
	Double price;


	public Trade(Instant time, int quantity, Indicator indicator, Double price) {
		super();
		this.time = time;
		this.quantity = quantity;
		this.indicator = indicator;
		this.price = price;
	}
	public Instant getTime() {
		return time;
	}
	public void setTime(Instant time) {
		this.time = time;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Indicator getIndicator() {
		return indicator;
	}
	public void setIndicator(Indicator indicator) {
		this.indicator = indicator;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}



}
