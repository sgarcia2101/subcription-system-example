package com.sgarcia.subcription.repository;

import com.sgarcia.subcription.domain.Subcription;

public interface SubcriptionNotificationRepository {

	public void sendNew(Subcription subcription);
	
}
