package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.Order;
import beans.Orders;
import beans.User;
import business.MyTimerService;
import business.OrdersBusinessInterface;

@ManagedBean
@ViewScoped
public class FormController 
{
	@Inject
	OrdersBusinessInterface service;
	
	//@EJB
	//MyTimerService timer;
	
	//Orders Orders = new Orders();
	
	/*
	public String onSubmit(User user) throws SQLException
	{
		FacesContext context = FacesContext.getCurrentInstance();
		user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		//System.out.println("First: " + user.getFirstName() + " Last: " + user.getLastName());
		
		//user.orders = context.getApplication().evaluateExpressionGet(context, "#{order}", Orders.class);
		
		Orders orders = user.getOrders();
		
		
		//service.test();
		
		//timer.setTimer(10000);
	
		System.out.println("Original:");
		getAllOrders();
		
		insertOrder();
		
		System.out.println("After insert: ");
		getAllOrders();
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("orders", orders);
		
		return "TestResponse.xhtml";
	}
	*/
	
	public String onLogoff()
	{
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "TestResponse.xhtml?faces-redirect=true";
	}
	
	public String onFlash(User user)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		return "TestResponse2.xhtml";
	}
	
	public OrdersBusinessInterface getService()
	{
		return service;
	}
	
	private void getAllOrders() throws SQLException
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","rootPass");
			System.out.println("Success!");
			
			Statement state = conn.createStatement();
			
			String count = "SELECT COUNT(*) FROM testapp.ORDERS";
			
			int size = 0;
			ResultSet s = state.executeQuery(count);
			if (s.next())
			{
				size = s.getInt(1);
			}
			
			String select = "SELECT * FROM testapp.ORDERS";
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(select);
			
			if (rs.next())
			{
				for (int i = 0; i < size; i++)
				{
					System.out.print(rs.getInt("ID") + " ");
					System.out.print(rs.getString("PRODUCT_NAME") + " ");
					System.out.print(rs.getFloat("PRICE") + " \n");
					rs.next();
				}
			}
			rs.close();
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Failure!");
			e.printStackTrace();
		}
		finally {
			if (conn != null)
			{
				conn.close();
			}
			else System.out.println("Nothing to close");
			
		}
		
	}
	
	private void insertOrder()
	{
		
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","rootPass");
		 
			System.out.println("Success in insert");
			
			Statement state = conn.createStatement();
			String insert = "INSERT INTO  testapp.ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES('001122334455', 'This was inserted new', 25.00, 100)";
			
			state.executeQuery(insert);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public String onSendOrder()
	{
		Order order = new Order("F123","Shirt",24.99f,1);
		service.sendOrder(order);
		return "OrderResponse.xhtml";
	}
}
