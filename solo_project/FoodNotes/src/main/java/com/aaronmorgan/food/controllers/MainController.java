package com.aaronmorgan.food.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aaronmorgan.food.models.Dish;
import com.aaronmorgan.food.models.Restaurant;
import com.aaronmorgan.food.models.User;
import com.aaronmorgan.food.services.DishService;
import com.aaronmorgan.food.services.NoteService;
import com.aaronmorgan.food.services.RestaurantService;
import com.aaronmorgan.food.services.UserService;
import com.aaronmorgan.food.validators.UserValidator;


@Controller
public class MainController {
	@Autowired
	private UserValidator validator;

	@Autowired
	private UserService uService;
	
	@Autowired
	private RestaurantService rService;
	
	@Autowired
	private DishService dService;
	
	@Autowired
	private NoteService nService;
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/images/";


	@RequestMapping("/")
	public String registerForm(@Valid @ModelAttribute("user") User user) {
	    return "loginRegistration.jsp";
	}    
	@PostMapping(value="/registration")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
	    validator.validate(user, result);
		if(result.hasErrors()) {
	    	return "loginRegistration.jsp";
	    }
	    User u = uService.registerUser(user);
	    session.setAttribute("userId", u.getId());
	    return "redirect:/home";
	}
	
	@PostMapping(value="/login")
	public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttr) {
	   boolean isAuthenticated = uService.authenticateUser(email, password);
	   if(isAuthenticated) {
		   User u = uService.findByEmail(email);
		   session.setAttribute("userId", u.getId());
		   return "redirect:/home";
	   } else {
    	   redirectAttr.addFlashAttribute("error", "Email or Password incorrect.");
    	   return "redirect:/";
       }
	}
    @RequestMapping("/home")
    public String home(HttpSession session, User user, Model model, @ModelAttribute("restaurant") Restaurant restaurant, Model viewModel, Model userModel) {
    	userModel.addAttribute("allUsers", this.uService.getAllUsers());
    	viewModel.addAttribute("allRestaurants", this.rService.getAllRestaurants());
        Long userId = (Long) session.getAttribute("userId");
        User u = uService.findUserById(userId);
    	model.addAttribute("sessionUser", u);
        if(userId == null) {
    	   return "redirect:/";
        }
        return "index.jsp";
    }
    @RequestMapping("/restaurant/new")
    public String newRestaurant(HttpSession session, User user, @ModelAttribute("restaurant") Restaurant restaurant, Model model, Model viewModel) {
        Long userId = (Long) session.getAttribute("userId");
        User u = uService.findUserById(userId);
        model.addAttribute("sessionUser", u);
        if(userId == null) {
    	   return "redirect:/";
        }
    	return "newRestaurant.jsp";
    }
    @PostMapping(value="/restaurant/new")
    public String createRestaurant(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult result, Model model, HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId"); 
    	
    	User u = uService.findUserById(userId);
        model.addAttribute("user", u);

        rService.createRestaurant(restaurant);
        return "redirect:/home";
    }
	@GetMapping("/restaurant/like/{id}")
	public String likeRestaurant(@PathVariable("id") Long id, HttpSession session) {
		Restaurant oneRestaurant = this.rService.getSingleRestaurant(id);
		User liker = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.rService.likeRestaurant(liker, oneRestaurant);
		return "redirect:/home";
	}
	
	@GetMapping("/restaurant/unlike/{id}")
	public String unlikeRestaurant(@PathVariable("id") Long id, HttpSession session) {
		Restaurant oneRestaurant = this.rService.getSingleRestaurant(id);
		User unliker = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.rService.unlikeRestaurant(unliker, oneRestaurant);
		return "redirect:/home";
	}
	@GetMapping("/lightmode")
	public String lightMode(HttpSession session) {
		User user = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.uService.lightMode(user);
		return "redirect:/home";
	}
	@GetMapping("/darkmode")
	public String darkMode(HttpSession session) {
		User user = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.uService.darkMode(user);
		return "redirect:/home";
	}
	@GetMapping("/profile/{id}")
	public String viewProfile(@ModelAttribute("user") User user, Model model, @PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId"); 
    	User u = uService.findUserById(userId);
        model.addAttribute("sessionUser", u);
		User oneUser = this.uService.findUserById(id);
		model.addAttribute("user", oneUser);
		return "profile.jsp";
	}
	//Follow
	@GetMapping("/profile/follow/{id}")
	public String followUser(@PathVariable("id") Long id, HttpSession session) {
		User oneUser = this.uService.findUserById(id);
		User sessionUser = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.uService.followUser(oneUser, sessionUser);
		return "redirect:/profile/" +id;
	}
	//Unfollow
	@GetMapping("/profile/unfollow/{id}")
	public String unfollowUser(@PathVariable("id") Long id, HttpSession session) {
		User oneUser = this.uService.findUserById(id);
		User sessionUser = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.uService.unfollowUser(oneUser, sessionUser);
		return "redirect:/profile/" +id;
	}
	@PostMapping("/upload/{id}")
	public String uploadProfilePic(@RequestParam("picture") MultipartFile file, HttpSession session, RedirectAttributes redirectAttr, @PathVariable("id") Long id) {
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message", "Upload an image");
			return "redirect:/home";
		}
		try {
			User user = this.uService.findUserById((Long) session.getAttribute("userId"));
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			String image_url = "/images/" + file.getOriginalFilename();
			this.uService.uploadProfilePic(user, image_url);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/profile/"+ id;
	}
	@GetMapping("/restaurant/{id}")
	public String viewRestaurant(@ModelAttribute("user") User user, Model model, @PathVariable("id") Long id, HttpSession session, @ModelAttribute("restaurant") Restaurant restaurant, @ModelAttribute("dish") Dish dish) {
		Long userId = (Long) session.getAttribute("userId"); 
    	User u = uService.findUserById(userId);
        model.addAttribute("sessionUser", u);
        Restaurant oneRestaurant = this.rService.getSingleRestaurant(id);
		model.addAttribute("restaurant", oneRestaurant);
		return "restaurant.jsp";
	}
	//Add Restaurant Pic
		@PostMapping("/upload/restaurant/{id}")
		public String uploadRestaurantPic(@RequestParam("picture") MultipartFile file, HttpSession session, RedirectAttributes redirectAttr, @PathVariable("id") Long id) {
			if(file.isEmpty()) {
				redirectAttr.addFlashAttribute("message", "Upload an image");
				return "redirect:/home";
			}
			try {
				Restaurant restaurant = this.rService.getSingleRestaurant(id);
				byte[] bytes = file.getBytes();
				Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
				Files.write(path, bytes);
				String image_url = "/images/" + file.getOriginalFilename();
				this.rService.uploadRestaurantPic(restaurant, image_url);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return "redirect:/restaurant/"+ id;
		}
	//New Dish
	@PostMapping(value="/restaurant/dish/{id}")
    public String createDish(@PathVariable("id") Long id, HttpSession session, @RequestParam("dishPic")String dishPic, @RequestParam("dishName")String dishName, RedirectAttributes redirs, @ModelAttribute("dish") Dish dish) {
    	Restaurant server = this.rService.getSingleRestaurant(id);
    	if(dishName.equals("")) {
			redirs.addFlashAttribute("error", "There has to be a dish name");
			return "redirect:/restaurant/" + id ;
		}
        dService.addDish(server, dishName, dishPic);
        return "redirect:/restaurant/" + id;
    }
	@GetMapping("/dish/favorite/{id}")
	public String favoriteDish(@PathVariable("id") Long id, HttpSession session) {
		Dish oneDish = this.dService.getSingleDish(id);
		User favoritor = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.dService.favoriteDish(favoritor, oneDish);
		return "redirect:/restaurant/" +oneDish.getServer().getId();
	}
	@GetMapping("/dish/unfavorite/{id}")
	public String unfavoriteDish(@PathVariable("id") Long id, HttpSession session) {
		Dish oneDish = this.dService.getSingleDish(id);
		User favoritor = this.uService.findUserById((Long) session.getAttribute("userId"));
		this.dService.unfavoriteDish(favoritor, oneDish);
		return "redirect:/home";
	}
	//View Dish
	@GetMapping("/dish/{id}")
	public String viewDish(@ModelAttribute("user") User user, Model model, @PathVariable("id") Long id, HttpSession session, @ModelAttribute("dish") Dish dish) {
		Long userId = (Long) session.getAttribute("userId"); 
    	User u = uService.findUserById(userId);
        model.addAttribute("sessionUser", u);
        Dish oneDish = this.dService.getSingleDish(id);
		model.addAttribute("dish", oneDish);
		return "dish.jsp";
	}
	//Add Dish Pic
	@PostMapping("/upload/dish/{id}")
	public String uploadDishPic(@RequestParam("picture") MultipartFile file, HttpSession session, RedirectAttributes redirectAttr, @PathVariable("id") Long id) {
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message", "Upload an image");
			return "redirect:/home";
		}
		try {
			Dish dish = this.dService.getSingleDish(id);
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			String image_url = "/images/" + file.getOriginalFilename();
			this.dService.uploadDishPic(dish, image_url);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return "redirect:/dish/"+ id;
	}
	//Add Note
	@PostMapping(value="/dish/note/{id}")
    public String createNote(@PathVariable("id") Long id, HttpSession session, @RequestParam("content")String content, RedirectAttributes redirs) {
    	Long userId = (Long) session.getAttribute("userId");     	
    	User author = uService.findUserById(userId);
    	Dish dishNoted = this.dService.getSingleDish(id);
    	if(userId == null) {
     	   return "redirect:/";
         }
    	if(content.equals("")) {
			redirs.addFlashAttribute("error", "There has to be content");
			return "redirect:/dish/" + id ;
		}
        nService.addNote(author, dishNoted, content);
        return "redirect:/dish/" + id;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
       session.invalidate();
       return "redirect:/";
    }
    //Delete Restaurant
    @RequestMapping("/restaurant/delete/{id}")
    	public String deleteRestaurant(@PathVariable("id") Long id) {
    	Restaurant restaurant = this.rService.getSingleRestaurant(id);
    	this.rService.deleteRestaurant(id);
    	return "redirect:/home";
    }
}
