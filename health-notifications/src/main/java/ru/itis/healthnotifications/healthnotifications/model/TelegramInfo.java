package ru.itis.healthnotifications.healthnotifications.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TelegramInfo {

	private String nickname;

	private Long chatId;

}
