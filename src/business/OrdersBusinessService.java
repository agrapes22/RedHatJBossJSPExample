package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Order;

@Stateless
@LocalBean
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {

	List<Order> orders;
	
    public OrdersBusinessService() 
    {
    	orders = new ArrayList<>();
		
    	orders.add(new Order("1234","Shoes", 64.99f, 1));
    	orders.add(new Order("5678","Socks", 12.99f, 2)); 
    	orders.add(new Order("9012","Hat", 14.99f, 1));

    }

	@Override
	public void test() 
	{
		System.out.println("Hello from the OrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
