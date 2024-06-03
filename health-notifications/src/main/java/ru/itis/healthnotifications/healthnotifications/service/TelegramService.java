package ru.itis.healthnotifications.healthnotifications.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TelegramService {

	private final RestTemplate restTemplate;

	@Value("telegram.bot.token")
	private String token;

	private HashMap<String, Long> getInfo() {
		HashMap<String, Long> info = new HashMap<>();
		String url = "https://api.telegram.org/bot" + token + "/getUpdates";
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
		for (String nickname : telegramChatMapping.keySet()) {
			Optional<UserEntity> opt = userRepository.findByTelegram(nickname);
			if (opt.isEmpty()) {
				continue;
			}
			System.out.println(opt.get().getTelegram());
			UserEntity user = opt.get();
			user.setChatId(telegramChatMapping.get(nickname));
			userRepository.save(user);
		}
	}

}
