package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Address;
import com.revature.data.AddressDAO;
import com.revature.data.TRAppDAOFactory;

public class AddressServiceOracle implements AddressService {
	private Logger log = Logger.getLogger(AddressServiceOracle.class);
	private TRAppDAOFactory bf = TRAppDAOFactory.getInstance();
	private AddressDAO ad = bf.getAddressDAO();
	

	@Override
	public void updateAddress(Address a) {
		ad.updateAddress(a);
	}

	@Override
	public void deleteAddress(Address a) {
		ad.deleteAddress(a);
	}

	@Override
	public int addAddress(Address address) {
		ad.addAddress(address);
		return 0;
	}

	@Override
	public Address getAddress(Address a) {
		return ad.getAddress(a);

	}

	@Override
	public Address getAddressById(int i) {
		return ad.getAddressById(i);

	}
}
