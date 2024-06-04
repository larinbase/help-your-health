package ru.itis.healthnotifications.healthnotifications.notifications.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itis.healthnotifications.healthnotifications.notifications.TelegramService;
import ru.itis.healthnotifications.healthnotifications.service.TelegramInfoService;
import ru.itis.healthnotifications.healthnotifications.service.impl.TelegramInfoServiceImpl;

import java.util.HashMap;
import java.util.HashSet;

@Log4j2
@Service
@RequiredArgsConstructor
public class TelegramServiceImpl implements TelegramService {

	private final RestTemplate restTemplate;

	private final TelegramInfoService telegramInfoService;

	private final String MESSAGE_TEXT = "ДА ИСПРАВИЛ МОЖНО БЕЗ АГРЕССИИ";
//	private final String MESSAGE_TEXT = "Привет! Прошло еще 2 часа, пришло время немного освежиться)";

	@Value("${telegram.bot.token}")
	private String token;

	private HashMap<String, Long> getInfo() {
		HashMap<String, Long> info = new HashMap<>();
		String url = "https://api.telegram.org/bot" + token + "/getUpdates";
		log.info(url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		if (response.getBody() == null)
			return new HashMap<>();
		JsonArray updates = JsonParser.parseString(response.getBody())
				.getAsJsonObject()
				.getAsJsonArray("result");
		System.out.println("Parsing...");
		for (JsonElement update : updates) {
			JsonObject message = update.getAsJsonObject().getAsJsonObject("message");
			if (message != null) {
				JsonObject chat = message.getAsJsonObject("chat");
				if (chat.has("username")) {
					info.put(chat.get("username").getAsString(), chat.get("id").getAsLong());
				}
			}
		}
		return info;
	}

	public void parsing() {
		HashMap<String, Long> telegramChatMapping = getInfo();
		HashMap<String, Boolean> emptyUsers = telegramInfoService.getEmptyUsers();
		HashMap<String, Long> updateUsers = new HashMap<>();
		for (String nickname : telegramChatMapping.keySet()) {
			if (emptyUsers.containsKey(nickname)) {
				updateUsers.put(nickname, telegramChatMapping.get(nickname));
			}
		}
		telegramInfoService.updateUsers(updateUsers);
	}

	public void sendNotification() {
		System.out.println("Sending...");
		String url = "https://api.telegram.org/bot" + token + "/sendMessage";
		for (Long chatId : telegramInfoService.getSubscribers()) {

			String body = "{\"chat_id\": \"%d\", \"text\": \"%s\"}".formatted(chatId, MESSAGE_TEXT);
			log.info(body);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(body, headers);

			restTemplate.postForObject(url, request, String.class);
		}
	}

}
