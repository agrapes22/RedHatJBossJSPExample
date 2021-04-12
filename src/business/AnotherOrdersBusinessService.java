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
public class AnotherOrdersBusinessService implements OrdersBusinessInterface{

	List<Order> orders;
	
    public AnotherOrdersBusinessService() 
    {
    	orders = new ArrayList<>();
		
    	orders.add(new Order("WE56","Apples", 1.29f, 4));
    	orders.add(new Order("IW19","Oranges", 0.89f, 6)); 
    	orders.add(new Order("NU09","Pears", 2.49f, 4));
    }

	@Override
	public void test() 
	{
		System.out.println("Hello from the AnotherOrdersBusinessService");
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
