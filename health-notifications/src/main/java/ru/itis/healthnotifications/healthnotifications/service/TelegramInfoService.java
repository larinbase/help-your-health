package ru.itis.healthnotifications.healthnotifications.service;

import java.util.HashMap;
import java.util.List;

public interface TelegramInfoService {

	public List<Long> getSubscribers();

	HashMap<String, Boolean> getEmptyUsers();

	void updateUsers(HashMap<String, Long> updateUsers);

}
