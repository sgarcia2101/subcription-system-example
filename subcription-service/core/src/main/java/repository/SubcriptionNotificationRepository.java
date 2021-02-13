package repository;

import domain.Subcription;

public interface SubcriptionNotificationRepository {

	public void sendNew(Subcription subcription);
	
}
