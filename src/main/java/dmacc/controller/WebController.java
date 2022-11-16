/**
 * @author Elijah Edlund - ezedlund
 * CIS175 - Fall 2021
 * Nov 14, 2022
 */
package dmacc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import dmacc.beans.Customer;
import dmacc.beans.Movie;
import dmacc.repository.CustomerRepository;
import dmacc.repository.MovieRepository;

@Controller
public class WebController {
	@Autowired
	CustomerRepository repoCustomer;
	@Autowired
	MovieRepository repoMovie;

	/* ================ */
	/* !!! CUSTOMER !!! */
	/* ================ */

	// Create customer with model
	@GetMapping("/inputCustomer")
	public String addNewCustomer(Model model) {
		Customer c = new Customer();
		model.addAttribute("newCustomer", c);
		return "inputCustomer";
	}

	// Create customer with actual data
	@PostMapping("/inputCustomer")
	public String addNewCustomer(@ModelAttribute Customer c, Model model) {
		repoCustomer.save(c);
		return viewAllCustomers(model);
	}

	// View all
	@GetMapping("/viewAllCustomers")
	public String viewAllCustomers(Model model) {
		if (repoCustomer.findAll().isEmpty())
			return addNewCustomer(model);
		model.addAttribute("customers", repoCustomer.findAll());
		return "resultsCustomer";
	}

	// Edit customer info
	@GetMapping("/editCustomer/{ID}")
	public String showUpdateContactCustomer(@PathVariable("ID") long ID, Model model) {
		Customer c = repoCustomer.findById(ID).orElse(null);
		model.addAttribute("newCustomer", c);
		return "inputCustomer";
	}

	// Update customer
	@PostMapping("/updateCustomer/{ID}")
	public String reviseCustomer(Customer c, Model model) {
		repoCustomer.save(c);
		return viewAllCustomers(model);
	}

	// Delete customer
	@GetMapping("/deleteCustomer/{ID}")
	public String deleteCustomer(@PathVariable("ID") long ID, Model model) {
		Customer c = repoCustomer.findById(ID).orElse(null);
		repoCustomer.delete(c);
		return viewAllCustomers(model);
	}

	/* ============= */
	/* !!! MOVIE !!! */
	/* ============= */

	// Create movie with model
	@GetMapping("/inputMovie")
	public String addNewMovie(Model model) {
		Movie m = new Movie();
		model.addAttribute("newMovie", m);
		return "inputMovie";
	}

	// Create movie with actual data
	@PostMapping("/inputMovie")
	public String addNewMovie(@ModelAttribute Movie m, Model model) {
		repoMovie.save(m);
		return viewAllMovies(model);
	}

	// View all
	@GetMapping("/viewAllMovies")
	public String viewAllMovies(Model model) {
		if (repoMovie.findAll().isEmpty())
			return addNewMovie(model);
		model.addAttribute("movies", repoMovie.findAll());
		return "resultsMovie";
	}

	// Edit movie info
	@GetMapping("/editMovie/{ID}")
	public String showUpdateContactMovie(@PathVariable("ID") long ID, Model model) {
		Movie m = repoMovie.findById(ID).orElse(null);
		model.addAttribute("newMovie", m);
		return "inputMovie";
	}

	// Update movie
	@PostMapping("/updateMovie/{ID}")
	public String reviseMovie(Movie m, Model model) {
		repoMovie.save(m);
		return viewAllMovies(model);
	}

	// Delete movie
	@GetMapping("/deleteMovie/{ID}")
	public String deleteMovie(@PathVariable("ID") long ID, Model model) {
		Movie m = repoMovie.findById(ID).orElse(null);
		repoMovie.delete(m);
		return viewAllMovies(model);
	}

}
