package beans;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.*;

@ManagedBean
@ViewScoped
public class Orders 
{
	List<Order> orders;

	public Orders()
	{
		orders = new ArrayList<>();
		
		orders.add(new Order("JFHL","T-Shirt", 32.99f, 1));
		orders.add(new Order("UENS","Dress Shirt", 43.99f, 2)); 
		orders.add(new Order("WNEE","Slacks", 55.99f, 1));
	}
	
	public List<Order> getOrders() 
	{
		return orders;
	}

	public void setOrders(List<Order> orders) 
	{
		this.orders = orders;
	}
	
	public String toString()
	{
		String out = "";
		for (Order o : orders)
		{
			out += o.orderNum;
			out += o.productName;
			out += o.price;
			out += o.quantity;
		}
		
		return out;
	}
}
