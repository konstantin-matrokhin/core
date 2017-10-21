# LastCraft Core
Проект состоит из 4 частей:
* **CoreAPI** - API для взаимодействия
* **CoreCLI** - приложение для управления серверами для командной строки
* **CoreBukkit** - плагин для Spigot/Bukkit
* **CoreBungee** - плагин для BungeeCord

## CoreAPI
Содержит все необходимые общие для всех проектов элементы.
Отсюда и задается поведение пакетов, пришедших на один из серверов (__CLI, Bukkit, Proxy__).

## CoreCLI
Для запуска используйте
```
java -cp CoreCLI.jar;lib/* CoreCLI
```

#### Что сделано:
* Отправка пакетов между серверами
* Соответственно их получение и обработка
* Базовые пакеты: подключение/отключение сервера/прокси/игрока, бродкаст сообщений, отправка motd
* Конфигурация главного сервера
* Контейнеры и адаптеры для хранения прокси, игровых серверов и игроков
* Базовые интерфейсы игроков
* Обработка команд с CoreCLI
* Базовая работа с БД MySQL
* Основа работы с сетью (нуждается в доработке)
* Обработка некоторых событий игроков и отправка соотв. пакетов

#### TODO
* Бизнес-логика
* Новые пакеты
* Взаимодействие с игроками, регистрация, лицензия и тд и тп
* Очень много всего

#### Зависимости (Maven):
```xml
<!-- https://mvnrepository.com/artifact/io.netty/netty-all -->
<dependency>
    <groupId>io.netty</groupId>
    <artifactId>netty-all</artifactId>
    <version>4.1.16.Final</version>
</dependency>

<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>6.0.6</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.2</version>
</dependency>
```
Используйте Maven только для загрузки зависимостей, для компиляции он не подходит из-за циклических зависимостей.