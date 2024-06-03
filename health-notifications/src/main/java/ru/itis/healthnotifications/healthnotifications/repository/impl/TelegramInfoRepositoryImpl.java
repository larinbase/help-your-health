package ru.itis.healthnotifications.healthnotifications.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.healthnotifications.healthnotifications.model.TelegramInfo;
import ru.itis.healthnotifications.healthnotifications.repository.TelegramInfoRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TelegramInfoRepositoryImpl implements TelegramInfoRepository {

	private final JdbcTemplate jdbcTemplate;

	private final String FIND_ALL_SUBSCRIBERS = "select nickname, chat_id from telegram_info where chat_id is not null and send is true";

	private final String FIND_EMPTY_USERS = "select nickname from telegram_info where chat_id is null";

	private final String UPDATE_USER = "update telegram_info set chat_id = ? where nickname = ?";

	private RowMapper<TelegramInfo> mapper = (rs, rowNum) -> TelegramInfo.builder()
			.nickname(rs.getString("nickname"))
			.chatId(rs.getLong("chat_id"))
			.build();

	@Override
	public List<TelegramInfo> findAllSubscribers() {
		return jdbcTemplate.query(FIND_ALL_SUBSCRIBERS, mapper);
	}

	@Override
	public List<String> findEmptyUsers() {
		return jdbcTemplate.queryForList(FIND_EMPTY_USERS, String.class);
	}

	@Override
	public void updateUser(String nickname, Long chatId) {
		jdbcTemplate.update(UPDATE_USER, chatId, nickname);
	}

}
