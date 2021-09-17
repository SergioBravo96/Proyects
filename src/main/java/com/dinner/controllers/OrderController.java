package com.dinner.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dinner.models.Order;
import com.dinner.services.OrderService;
import com.dinner.validate.Detail;
import com.dinner.validate.OrderIn;
import com.dinner.validate.Validate;
import com.fasterxml.jackson.databind.util.JSONPObject;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@Autowired
	Validate valid;
	@Autowired
	Detail detail;

	Random rnd = new Random();
	int number = rnd.nextInt(99999);

	@PostMapping()
	public Object saveOrder(@RequestBody OrderIn orderIn) throws ParseException {

		if (valid.all(orderIn)) {

			if (valid.validDate()) {
				valid.findCustomer(orderIn);
				valid.findFood(orderIn);

				for (Map<String, Object> map : this.detail.getSaucers()) {
					Order order = new Order();
					for (Entry<String, Object> entry : map.entrySet()) {

						switch (entry.getKey()) {
						case "Name Food":
							order.setNameFood((String) entry.getValue());
							break;

						case "Price":
							order.setPrice((Integer) entry.getValue());
							break;
						case "Saucer":
							order.setSaucer((String) entry.getValue());
							break;

						default:
							break;
						}

					}
					order.setSubOrder((long) this.number);
					order.setEmail(detail.getEmail());
					order.setDate(detail.getDate());
					order.setMunicipality(detail.getMunicipality());
					order.setNeighborhoodName(detail.getNeighborhoodName());
					order.setStreet(detail.getStreet());
					order.setNum_ex(detail.getNum_ex());
					order.setSubTotal(detail.getSubTotal());
					orderService.saveOrder(order);
					detail.setId(order.getId_order());

					
				}

				detail.setSubOrder((long) this.number);
				return detail;

			} else {
				return new JSONPObject("", "Pedido Fuera de Horario");
			}
		} else {

			return new JSONPObject("Customer o Saucer", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/query")
	public ArrayList<Order> getSaucer(@RequestParam("saucer") String saucer) {
		return orderService.getSaucer(saucer);

	}

	@PutMapping("/update")
	public Object updateOrder(@RequestBody OrderIn update) throws ParseException {
		if(valid.all(update)) {
			if(valid.validDate()) {
			valid.findCustomer(update);
			valid.findFood(update);
			
			for(Map<String, Object> map: this.detail.getSaucers()) {
				Order order = new Order();
				for (Entry<String, Object> entry : map.entrySet()) {
					switch (entry.getKey()) {
					case "Name Food":
						order.setNameFood((String) entry.getValue());
						break;

					case "Price":
						order.setPrice((Integer) entry.getValue());
						break;
					case "Saucer":
						order.setSaucer((String) entry.getValue());
						break;

					default:
						break;
					}
					
				}
				order.setSubOrder((long) number);
				order.setEmail(detail.getEmail());
				order.setDate(detail.getDate());
				order.setMunicipality(detail.getMunicipality());
				order.setNeighborhoodName(detail.getNeighborhoodName());
				order.setStreet(detail.getStreet());
				order.setNum_ex(detail.getNum_ex());
				order.setSubTotal(detail.getSubTotal());
				order.setId_order(update.getId_order());
				orderService.saveOrder(order);
			
			}
			detail.setId(update.getId_order());
		
			detail.setSubOrder((long) this.number);
			return detail;
			
		}else {
			return  HttpStatus.NOT_FOUND;
		}
		
	}else {
		return new JSONPObject("Customer o Saucer", HttpStatus.NOT_FOUND);
	}
	}

	@GetMapping("/queryParam")
	public ArrayList<Order> getDate(@RequestParam("saucer") String saucer, @RequestParam("toDate") String toDate,
			@RequestParam("endDate") String endDate) throws ParseException {
		SimpleDateFormat date = new SimpleDateFormat("yyy-MM-dd");
		Date to = date.parse(toDate);
		Date end = date.parse(endDate);
		List<Order> list = orderService.getSaucer(saucer);
		List<Order> list1 = valid.getDates(list, to, end);

		return (ArrayList<Order>) list1;
	}

}
