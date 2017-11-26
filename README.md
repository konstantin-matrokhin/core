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
```bash
java -cp CoreCLI.jar;libs/* org.kvlt.core.CoreCLI
```
А проще всего запустить скрипт из директории Compiled
### Хочу подергать API (CoreCLI)
```java
// Онлайн-игрок
OnlinePlayer onlinePlayer = CoreServer.get().getOnlinePlayers().get("Steve");

// Любой игрок
ServerPlayer serverPlayer = PlayerDB.loadServerPlayer(name);

// Получить все игровые серверы
GameServer gameServer = CoreServer.get().getGameServers();

//Получить прокси по имени
Proxy proxy = CoreServer.get().getProxies().get("swlobby-1");

//Отправка пакета
BroadcastPacket bp = new BroadbastPacket("Hello World");

CorePlugin.get().sendPacket(bp); //из баккита
CoreBungee.get().sendPacket(bp); //из прокси

GameServer#send(Packet p); //в баккит
Proxy#send(Packet p); //в прокси

// Получить данных из секции конфига
String bukkitSectionValue = Config.getServer("key");

/**
* Создать пакет можно унаследовав класс Packet и имплементировав методы из него
*/
public class TestPacket extends Packet {
    @Override
    protected void onCore() {
        //Если пакет пришел на кор
    }

    @Override
    protected void onServer() {
        //... и если на баккит
        
        /* !!Используйте только методы из CoreAPI/CoreBungee/CoreBukkit!! */
        /* Использование методов из Spigot или BungeeCord напрямую может вызвать ошибку */
    }

    @Override
    protected void onProxy() {
        //... и если на прокси
    }
}

```
#### Что сделано:
* Ничего
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
* Отправка личных сообщений между игроками
* Модели таблиц базы данных и маппинг данных MySQL --> POJO!
* Получение информации об игроке (Пример: /whois <name>)
* Отправка объявлений (/alert <msg> или /salert <server> <msg>)
* Регистрация (/register <pass> <pass>)
* Авторизация (/login <pass>)
* Время игры за всё время (/time)
* Ответочка (/r <msg>)
* /hub | /lobby
* Куча пакетов
* Отправка комманд /sendcommand <server> <cmd>
#### TODO
* Доделать авторизацию и вход по лицензии
* Система банов
* Стафф-чат
* Email restore
* Антиспам
* Автосообщения (реализовать таймер)
* заюзать Groups
* доделать все модели из бд и их загрузку/выгрузку
* и тп
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

<!-- https://mvnrepository.com/artifact/org.sql2o/sql2o -->
<dependency>
    <groupId>org.sql2o</groupId>
    <artifactId>sql2o</artifactId>
    <version>1.6.0-RC3</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.google.code.gson/gson -->
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.2</version>
</dependency>

<!-- https://mvnrepository.com/artifact/org.codehaus.groovy/groovy-all -->
<dependency>
    <groupId>org.codehaus.groovy</groupId>
    <artifactId>groovy-all</artifactId>
    <version>2.4.11</version>
</dependency>
```
Используйте Maven только для загрузки зависимостей, для компиляции он не подходит из-за циклических зависимостей.
## CoreBukkit
Работает, гоняет пакеты, хорошо всё там. Трогать лишний раз не надо, только пакеты отсылать, да события обрабатывать.
Отрефакторить надо бы.
## CoreBungee
Что-то делает. Работает и ладно.
