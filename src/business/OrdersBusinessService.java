package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import beans.Order;

@Stateless
@LocalBean
@Alternative
public class OrdersBusinessService implements OrdersBusinessInterface {

	List<Order> orders;
	
	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName="java:/jms/queue/Order")
	private Queue queue;

	
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
		//System.out.println("Hello from the OrdersBusinessService");
	}

	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	@Override
	public void sendOrder(Order order)
	{
		try 
		{
			Connection connection = connectionFactory.createConnection();
			Session  session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(null);
			TextMessage message1 = session.createTextMessage();
			message1.setText("This is test message");
			messageProducer.send((Destination) queue, message1);
			connection.close();
		} 
		catch (JMSException e) 
		{
			e.printStackTrace();
		}		

	}

}
