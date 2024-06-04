package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
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
	@JoinColumn(referencedColumnName = "id", name = "user_id")
	private User user;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "chat_id")
	private Long chatId;

	@Column(name = "send")
	private Boolean send;

}
