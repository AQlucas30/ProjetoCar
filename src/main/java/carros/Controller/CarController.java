package carros.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import carros.Model.Car;
import carros.Repository.CarRepository;

@Controller
public class CarController {
	
	
	@Autowired
	private CarRepository carRepository;
	
	@GetMapping("/")
	public String listCar(Model model) {
		model.addAttribute("car", carRepository.findAll());
		return "/car/list";
	}
	
	@GetMapping("/car/add")
	public String addCarsForm(Model model) {
		model.addAttribute("car", new Car());
		return "car/add";
	}
	
	@PostMapping("/car/add")
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
	
	@Controller
	public class ErrorController {

	    @ExceptionHandler(value = { org.springframework.web.servlet.NoHandlerFoundException.class })
	    public String handleNotFoundError() {
	        return "erro"; // Caminho para a página de erro 404 personalizada
	    }
	}

	
	

}
