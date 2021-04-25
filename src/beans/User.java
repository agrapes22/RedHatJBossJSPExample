package beans;

import javax.faces.bean.*;
import javax.faces.bean.ViewScoped;
import javax.validation.constraints.*;

@ManagedBean
@SessionScoped
public class User 
{
	@NotNull
	@Size(min=5, max=15)
	String firstName;
	
	@NotNull
	@Size(min=5, max=15)
	String lastName;
	
	Orders orders = new Orders();
	
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	public String getLastName() 
	{
		return lastName;
	}
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public Orders getOrders()
	{
		return orders;
	}
	
}
