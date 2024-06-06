package ru.itis.healthnotifications.healthnotifications.repository;

import ru.itis.healthnotifications.healthnotifications.model.TelegramInfo;

import java.util.List;

public interface TelegramInfoRepository {

	public List<TelegramInfo> findAllSubscribers();

	List<String> findEmptyUsers();

	void updateUser(String nickname, Long chatId);

}
