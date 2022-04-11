package com.hcl.ecommerce.controller;

import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.ecommerce.dto.PaymentInfo;
import com.hcl.ecommerce.dto.Purchase;
import com.hcl.ecommerce.dto.PurchaseResponse;
import com.hcl.ecommerce.service.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

import java.util.logging.Logger;
@Log4j2
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

	 // private Logger logger = Logger.getLogger(getClass().getName());
	// Logger logger = LoggerFactory.getLogger(CheckoutController.class);

	private CheckoutService checkoutService;
	
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@PostMapping("/purchase")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		
		PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

		//log.info("Method accessed");
		return purchaseResponse;
	}
	
	@PostMapping("/payment-intent")
	public ResponseEntity<String> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException {


		PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
		
		String paymentStr = paymentIntent.toJson();
		
		return new ResponseEntity<>(paymentStr, HttpStatus.OK);
	}

}
