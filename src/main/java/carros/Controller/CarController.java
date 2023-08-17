package carros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import carros.Model.Car;
import carros.Repository.CarRepository;

public class CarController {
	
	
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping("/")
	public String listCar(Model model) {
		model.addAttribute("cars", carRepository.findAll());
		return "cars/list";
	}
	
	@GetMapping("/cars/add")
	public String addCarsForm(Model model) {
		model.addAttribute("car", new Car());
		return "car/add";
	}
	
	@PostMapping("/task/add")
	public String addCar(@ModelAttribute Car car) {
		carRepository.save(car);
		return "redirect:/";
	}
	
	@GetMapping("/car/edit/{id}")
	public String editCarForm(@PathVariable Long id, Model model) {
		Car car = carRepository.findById(id).orElse(null);
		model.addAttribute("car", car);
		return "car/edit";
	}
	
	@PostMapping("/car/edit")
	public String editCar(@ModelAttribute Car car) {
		carRepository.save(car);
		return "redirect:/";
	}
	
	@GetMapping("/car/delete/{id}")
	public String deleteCar(@PathVariable Long id) {
		carRepository.deleteById(id);
		return "redirect:/";
	}
	
	

}
