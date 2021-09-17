package com.dinner.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.dinner.models.Customers;

import com.dinner.models.Food;
import com.dinner.models.Order;
import com.dinner.services.CustomerService;
import com.dinner.services.FoodService;

@RestController
public class Validate {

	@Autowired
	FoodService foodService;
	@Autowired
	CustomerService customerService;
	@Autowired
	Optional<Customers> customers;
	@Autowired
	Optional<Food> food;
	@Autowired
	Issues issues;
	@Autowired
	Detail detail;



	public boolean all(OrderIn orderIn) throws ParseException {
		boolean flag = false;

		if (validateCustomer(orderIn.getEmail()) && validateFood(orderIn.getSaucer())) {
			flag = true;

		}
		return flag;
	}


	private boolean validateFood(String[] order) {
		boolean flag = false;

		for (String saucer : order) {
			if (!foodService.getById(saucer).isEmpty()) {
				boolean status = foodService.getById(saucer).get().isStatus();
				if (status) {
					flag = true;
				} else {
					this.issues.setStatus(false);
				}
			} else {
				this.issues.setSaucer(saucer);
				flag = false;
			}
		}
		return flag;
	}

	private boolean validateCustomer(String order) {
		boolean flag = false;

		if (!customerService.getById(order).isEmpty()) {
			flag = true;

		}
		return flag;
	}


	public void findCustomer(OrderIn orderIn) {

		this.customers = customerService.getById(orderIn.getEmail());
		this.detail.setEmail(customers.get().getEmail());
		this.detail.setMunicipality(customers.get().getMunicipality());
		this.detail.setNeighborhoodName(customers.get().getNeighborhoodName());
		this.detail.setNum_ex(customers.get().getNum_ex());
		this.detail.setStreet(customers.get().getStreet());

	}


	public void findFood(OrderIn orderIn) {
		List<Map<String, Object>> listFood = new ArrayList<>();

		int subTotal = 0;
		for (String saucer : orderIn.getSaucer()) {
			Optional<Food> food = foodService.getById(saucer);

			listFood.add(addFood(food));

			subTotal += food.get().getPrice();
		}

		this.detail.setSaucers(listFood);
		this.detail.setSubTotal(subTotal);

	}

	private Map<String, Object> addFood(Optional<Food> food) {
		Map<String, Object> mapFood = new HashMap<>();

		mapFood.put("Saucer", food.get().getId());
		mapFood.put("Name Food", food.get().getNameFood());
		mapFood.put("Description", food.get().getDescription());
		mapFood.put("Price", food.get().getPrice());

		return mapFood;
	}

	public boolean validDate() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		boolean flag = false;
		int hrs = Calendar.HOUR_OF_DAY;

		if (hrs > 9 && hrs < 21) {
			flag = true;
			String date = format.format(new Date());
			this.detail.setDate(date);
		}
		return flag;

	}

	public List<Order> getDates(List<Order> list, Date toDate, Date endDate) throws ParseException {
		List<Order> listOrder = new ArrayList<Order>();
		listOrder.clear();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		for (Order order : list) {

			String o = order.getDate();
			Date date = dateFormat.parse(o);

			if ((date.equals(toDate) || date.after(toDate)) && date.before(endDate)) {

				listOrder.add(order);

			}
		}
		return listOrder;

	}

	public Order addSaucers(Entry<String, Object> entry) {
		Order order = new Order();
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
		return order;
	}
	

}