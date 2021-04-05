package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Orders;
import beans.User;

@ManagedBean
@ViewScoped
public class FormController 
{
	//Orders Orders = new Orders();
	
	public String onSubmit(User user)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		System.out.println("First: " + user.getFirstName() + " Last: " + user.getLastName());
		
		//user.orders = context.getApplication().evaluateExpressionGet(context, "#{order}", Orders.class);
		
		Orders orders = user.getOrders();
		
		System.out.println(orders.toString());
		
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("user", user);
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("orders", orders);
		
		return "TestResponse.xhtml";
	}
	
	public String onFlash(User user)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		user = context.getApplication().evaluateExpressionGet(context, "#{user}", User.class);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("user", user);
		return "TestResponse2.xhtml";
	}
}
