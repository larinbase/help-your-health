package ru.itis.healthserviceimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthserviceimpl.model.TelegramInfo;

import java.util.Optional;
import java.util.UUID;

public interface TelegramInfoRepository extends JpaRepository<TelegramInfo, UUID> {

	Optional<TelegramInfo> findByNickname(String nickname);

}
