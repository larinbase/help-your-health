package ru.itis.healthnotifications.healthnotifications.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.healthnotifications.healthnotifications.model.TelegramInfo;
import ru.itis.healthnotifications.healthnotifications.repository.TelegramInfoRepository;
import ru.itis.healthnotifications.healthnotifications.service.TelegramInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramInfoServiceImpl implements TelegramInfoService {

	private final TelegramInfoRepository telegramInfoRepository;

	@Override
	public List<Long> getSubscribers() {
		List<Long> subscribers = new ArrayList<>();
		for (TelegramInfo info : telegramInfoRepository.findAllSubscribers()) {
			subscribers.add(info.getChatId());
		}
		return subscribers;
	}

	@Override
	public HashMap<String, Boolean> getEmptyUsers() {
		List<String> emtyUsersNames = telegramInfoRepository.findEmptyUsers();
		HashMap<String, Boolean> emptyUsers = new HashMap<>();
		for (String nickname : emtyUsersNames) {
			emptyUsers.put(nickname, false);
		}
		return emptyUsers;
	}

	@Override
	public void updateUsers(HashMap<String, Long> updateUsers) {
		for (String nickname : updateUsers.keySet()) {
			telegramInfoRepository.updateUser(nickname, updateUsers.get(nickname));
		}
	}

}
