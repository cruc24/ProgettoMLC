package com.Project.utilities;

import com.Project.beans.*;

public class GestioneUtente 
{
	public void AddUser(Utente u){
		Utente user = new Utente();
		user.setUserName(u.getUserName());
		user.setPassword(u.getPassword());
	}
	public void DeleteUser(Utente u){
		
		
	}
	
}
