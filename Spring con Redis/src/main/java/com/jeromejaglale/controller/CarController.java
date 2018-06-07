package com.jeromejaglale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import com.jeromejaglale.domain.Car;
import com.jeromejaglale.service.CarService;

@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@RequestMapping("/car/list")
	//public void carList(@RequestParam("name") String name, Model model) {
	public void carList(Model model) {	
		List<Car> carList = carService.findAll();
		model.addAttribute("carList", carList);
	}
	@RequestMapping("/car/list/{name}")
	public String carList2(@PathVariable("name") String name, Model model) {
		System.out.println(name);
		List<Car> carList = carService.findUser(name);
		if(carList == null){
			carList = carService.findAll();
		}
		model.addAttribute("carList", carList);
		return "/car/list";
	}
}