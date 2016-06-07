package weathergw.common;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

import weathergw.dal.WeatherInfoFileSupplier;
import weathergw.dal.WeatherInfoServiceSupplier;
import weathergw.domain.Location;
import weathergw.domain.WeatherInfo;


public class SupplierManager {
	
	private static final String KEY = "25781444d49842dc5be040ff259c5";

	public interface WeatherInfoFilter{
		public boolean dateBetween(WeatherInfo wi,LocalDate start, LocalDate end);
	}

	private static Collection<WeatherInfo> getCollectionFromSupplier(Supplier<Collection<WeatherInfo>> s){
		return s.get();
	}

	private static String buildUrl(String loc, LocalDate start, LocalDate end){
		return "http://api.worldweatheronline.com/free/v2/weather.ashx" + 
				"?key=" + KEY + 
				"&q=" + loc + 
				"&format=csv" +
				"&date="+ start.toString() + 
				"&enddate=" + end.toString() +
				"&tp=6";
	}	
		
	public static List<WeatherInfo> weatherInfoAdder(Location loc, LocalDate start, LocalDate end,WeatherInfoFilter fl){
		Collection<WeatherInfo> winfos ;
		Iterator<WeatherInfo> it = loc.iterator();	
		List<WeatherInfo> wil = new ArrayList<WeatherInfo>();
		
		//first check if is in memory
		while(it.hasNext()){
			WeatherInfo wi = it.next();
			if(fl.dateBetween(wi, start, end))
					wil.add(wi);
		}
		
		if(wil.isEmpty()){
			
			try{				
				winfos = getCollectionFromSupplier(new WeatherInfoFileSupplier(loc.getName().toLowerCase()+"-data.csv"));	
				for(WeatherInfo wi : winfos){
					if(fl.dateBetween(wi, start, end)){
						loc.add(wi);
						wil.add(wi);
					}
				}
				if(!wil.isEmpty())					
					return wil; //success on getting data from file
				
			}catch(Exception ex){
				//Log failure getting from file
			}

			try{
				//collection retuned is automatically saved to file
				wil = (List<WeatherInfo>) getCollectionFromSupplier(new WeatherInfoServiceSupplier(buildUrl(loc.getName(),start,end)));				
				for(WeatherInfo wi : wil) loc.add(wi);				
			}catch(Exception ex){
				//Log failure getting from API
				wil.clear();
				return wil;
			}			
		}		
		return wil;	
	}
}
