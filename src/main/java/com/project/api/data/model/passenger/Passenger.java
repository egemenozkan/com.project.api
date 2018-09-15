package com.project.api.data.model.passenger;

import com.project.api.data.model.common.Passport;
import com.project.api.data.model.common.Person;

public class Passenger extends Person{
	private Passport passport;
	


	public Passenger() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	@Override
	public boolean equals(Object o){
	  if(o instanceof Passenger){
	    Passenger toCompare = (Passenger) o;
	    return (this.getId() == toCompare.getId());
	  }
	  return false;
	}
}
