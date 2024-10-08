# Help your health

Технологии: Java, Spring Boot, PostgreSQL, Spring Data JPA, MongoDB, Redis, Spring Security, RabbitMQ, OAuth2, JUnit, Mockito, Docker

Мы с командой решили создать приложение “Help your health” на Spring Boot.  Это многофункциональное приложение, предназначенное для помощи пользователям в поддержании здорового образа жизни. Приложение предлагает ряд функций, включая отслеживание потребления пищи и воды, упражнения и рецепты. Кроме того, оно предоставляет возможность создания напоминаний о необходимости употребления воды через телеграм-бота.

Оно содержит следующий функционал:
  1) КБЖУ и водный баланс:
     
    a) Автоматический подсчет дневной нормы КБЖУ и воды на основе роста, веса, 
    возраста и физической активности пользователя при регистрации;

    b) Автоматический пересчет нормы КБЖУ и воды при обновлении данных о пользователе;
    
    c) Добавление съеденной еды и выпитой воды, проделанные тренировки за день и 
    формирование дневной статистики по калориями, выпитой воде и т.д;
    
    d) Возможность поставить напоминания о том, что пора пить воду, в телеграм-боте, 
    который будет их отправлять каждые 2 часа.
    
  2) Упражнения:
     
    a) Набор упражнений, которые содержат название, описание, изображения и количество сжигаемых калорий 
    за определенную единицу времени (например, 100 калорий за 1 км);

    b) Возможность пользователю выбрать упражнение, которое он хочет сделать (бег, ходьба, отжимания и т.д.). 
    В зависимости от того, как долго он его выполняет, сжигается n-ое количество калорий;
    
    c) Возможность создавать свои упражнения.
    
  3) Рецепты:
     
    a) Поиск рецептов по названию, категории и времени приготовления;

    b) Набор рецептов, которые содержат инструкции, 
    изображения и КБЖУ на 100 граммов;
    
    c) Возможность создавать свои рецепты.
