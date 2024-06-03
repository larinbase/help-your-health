package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.exception.TelegramNicknameNotFoundException;
import ru.itis.healthserviceimpl.model.TelegramInfo;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.TelegramInfoRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;
import ru.itis.healthserviceimpl.service.TelegramNotificationService;
import ru.itis.healthserviceimpl.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;


import java.security.Security;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TelegramNotificationServiceImpl implements TelegramNotificationService {

	private final TelegramInfoRepository telegramRepository;

	private final UserRepository userRepository;

	@Override
	public void subscribe(String nickname) {

		String username = ((BaseUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUsername(username).get();
		telegramRepository.save(TelegramInfo.builder()
				.user(user)
				.nickname(nickname)
				.send(true)
				.build()
		);

	}

	@Override
	public void unsubscribe(String nickname) {

		String username = ((BaseUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		User user = userRepository.findByUsername(username).get();

		if (telegramRepository.findByNickname(nickname).isEmpty()) {
			throw new TelegramNicknameNotFoundException(nickname);
		}
		telegramRepository.save(TelegramInfo.builder()
				.user(user)
				.nickname(nickname)
				.send(true)
				.build()
		);

	}
}
