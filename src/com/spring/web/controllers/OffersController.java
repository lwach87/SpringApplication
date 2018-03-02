package com.spring.web.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.web.dao.Offer;
import com.spring.web.service.OffersService;

@Controller
public class OffersController {

	@Autowired
	private OffersService offersService;

	@RequestMapping("/offers")
	public String showOffers(Model model) {

		List<Offer> offers = offersService.getCurrent();
		model.addAttribute("offers", offers);

		return "offers";
	}

	@RequestMapping("/createoffer")
	public String createOffer() {

		return "createoffer";
	}

	@RequestMapping(value = "/docreate", method = RequestMethod.POST)
	public String doCreate(Model model, @Valid Offer offer, BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("Form does not validate.");

			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage());
			}

		} else {
			System.out.println("Form validated.");
		}

		return "offercreated";
	}
}
