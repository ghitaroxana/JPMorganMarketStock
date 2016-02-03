From the requirement of this test I understood that the Stock entity has no price as a field, the price is given.
the Stock object has the following fields: 
* symbol, 
* Type (which is an Enum with 2 values), 
* lastDivident, 
* FixedDivident
* parValue
* trades - I considered that a stock can support more than one trade so I implemented a list o trades

The Trade Object has as fields:
* time - is the Timestamp for the trade, for which I used Instant type from Java 8 to record event time-stamps in my application
* quantity - for BUY indicator the quantity will be positive and for SELL indicator the quantity will be negative
* indicator - an Enum with 2 values
*price

the methods for Trade class are only Getters and Setters.

the methods for Stock Class are Getters and Setters and:
* dividendYield -> for a given price it calculates Dividend Yield of the stock
* PERatio -> for a given price it calculates P/E Ratio of the stock. the formula for P/E Ratio is Price/Dividend, 
I`m not sure about which dividend is that, so I considered that dividend=dividendYield
I calculated the P/E ratio as Price/DividendYield;

For these two methods, if the price is null or zero, an Exception ins trown.

* volumeWeightedStockPrice ->this method calls getLastTrades method to get the trades of a stock from the last 15 minute
					      this number of minutes is parametrized.
					      
for GBCE I considered that the price is not a field of Stock class, so the GBCE method is not specific to this class.
I could make this method static and to declare and implement it into Stock class but again, the method is not specific to it;
I declared a class named StockMarket which implements this method.
I considered two ways to implement this method because I`m not sure about the source of prices:
1. GBCE   ->for a given list of stocks, I build a list with all the trades from every  and based on this list of trades I build a list of prices, from every trade.
2. GBCEPrices  ->the list of prices is a parameter for the method

based on the list o prices I calculated the geometric mean of them

to calculate the geometric mean of prices it was need to implement a method to get the nth root of a value. 
If the number is 0 or less than 0 then the methos will not continue, else, the nth root of a value is calculated, with an accepted error;

to test my code I wrote a Test Class: TestStockMarket where I defined some Stock objects and I calculated the principal methods from this class;