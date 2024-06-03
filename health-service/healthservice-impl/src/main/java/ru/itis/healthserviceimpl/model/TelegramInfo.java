package ru.itis.healthserviceimpl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "telegram_info")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TelegramInfo extends AbstractModel {

	@OneToOne
	@Column(name = "user_id")
	private User user;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "chat_id")
	private Long chatId;

	@Column(name = "send")
	private Boolean send;

}
