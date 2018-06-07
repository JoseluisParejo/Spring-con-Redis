package com.jeromejaglale.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import redis.clients.jedis.Jedis;
import org.springframework.stereotype.Service;
import com.jeromejaglale.domain.Car;

@Service
public class CarService {
	private List<Car> carList;
	private Jedis jedis;

	CarService() {	
		carList = new LinkedList<Car>();
		jedis = new Jedis("localhost");
		Car car1 = new Car();
		car1.setName("Mercedes SL");
		car1.setPrice(new BigDecimal(123400));
		carList.add(car1);
		
		Car car2 = new Car();
		car2.setName("BMW M6 Coupé");
		car2.setPrice(new BigDecimal(125000));
		carList.add(car2);

		Car car3 = new Car();
		car3.setName("Audi R8");
		car3.setPrice(new BigDecimal(136100));
		carList.add(car3);
	}
	public List<Car> findAll() {
		return carList;
	}
	public List<Car> findUser(String name){
		List<Car> cars = new LinkedList<Car>();
		if(jedis.sismember("cars:usuarios:set", name)){
			List<String> carList1 = new ArrayList<String>();
			carList1 = jedis.lrange("cars:" + name + ":list", 0, -1);
			for (String st : carList1 ) {
				Car p = new Car();
				p.setName(st);
				p.setPrice(new BigDecimal(136100));
				cars.add(p);
			}
			return cars;
		//}else{
		//	return carList;
		}
		else{
		 return null;
		}
	}
}
