package edu.books.controller.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admincp/books")
public class WebAdminBookController {

	@GetMapping("index")
	public String index() {
		return "admin/book/index";
	}

	@GetMapping("detail/{id}")
	public String detail(@PathVariable Integer id) {
		return "admin/book/detail";
	}

	@GetMapping("add")
	public String add() {
		return "admin/book/add";
	}
	
	@GetMapping("edit/{id}")
	public String edit() {
		return "admin/book/edit";
	}
}
