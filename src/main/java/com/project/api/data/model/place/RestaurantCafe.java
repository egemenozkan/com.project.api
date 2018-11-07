package com.project.api.data.model.place;

import com.project.api.data.model.common.Network;

public class RestaurantCafe extends Place {
	private String formerName;
	private Network network;

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

}
