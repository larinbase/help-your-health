package ru.itis.healthserviceimpl.exception;

public class TelegramNicknameNotFoundException extends NotFoundException{
	public TelegramNicknameNotFoundException(String nickname) {
		super("Telegram account with %s nickname is not linked to this account");
	}
}
