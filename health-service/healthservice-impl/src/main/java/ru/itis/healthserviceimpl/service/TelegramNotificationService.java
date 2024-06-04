package ru.itis.healthserviceimpl.service;

public interface TelegramNotificationService {

	public void subscribe(String nickname);

	public void unsubscribe(String nickname);

}
