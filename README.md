# LastCraft Core
Проект состоит из 4 частей:
* **CoreAPI** - API для взаимодействия (интерфейсы, декодер/энкодер)
* **CoreCLI** - Сервер, который всем управляет
* **CoreBukkit** - плагин для Spigot/Bukkit
* **CoreBungee** - плагин для BungeeCord
## CoreAPI
Содержит все необходимые общие для всех проектов элементы. Например,
интерфейсы Packet, PacketIn, PacketOut, а также кодеки для работы
с пакетами.
## CoreCLI
### Запуск
Для запуска используйте
```bash
java -cp CoreCLI.jar;libs/* org.kvlt.core.CoreCLI
```
А проще всего запустить скрипт из директории Compiled.
Скоро я подключу Maven и всё будет ок.
### Протокол
Пакеты кодируются в поток байтов такого вида:

|5 байт|1 байт|2 байта|N байт (< 65535)|
|:---:|:---:|:---:|:---:|
|**>core**|**ID** (0 < id < 128)|Размер пакета|Данные|
|byte[]|byte|short|ByteBuf|

_>core_ - префикс для всех пакетов для идентификации

_id_ - номер пакета. Интерфейс **Packet** содержит метод _getId()_, который и должен возвращать ID пакета.

_Размер пакета_ - автоматически вычисляется по окончанию записи данных в пакет
___
Все данные записываются записываются и читаются в одинаковом порядке. Строки
кодируются/декодируются с помощью методов
```java
PacketUtil.writeString(String str, ByteBuf byteBuf); // Запись в ByteBuf
PacketUtil.readString(ByteBuf byteBuf); // Чтение из ByteBuf
```
Для записи строки в ByteBuf сначала пишется длина строки в *short* (2 байта),
потом байты строки в **UTF-8**.

Соответственно для чтения сначала считываются 2 байта для определения
длины строки, а потом уже читается строка с определенной длиной.

Всё остальное читается в ByteBuf через встроенные методы.
### API
Пример простого плагина

TestPlugin.java
```java
public class TestPlugin extends CorePlugin {

    @Override
    public void onEnable() {
        System.out.println("Hello Plugin!");
        System.out.println(getConfig().get("test").getAsString());

        CoreServer.get().getEventManager().registerListener(new TestEvents());
    }

    @Override
    public void onDisable() {
        System.out.println("bye bye!");
    }

}
```
TestEvents.java
```java
public class TestEvents implements CoreListener {

    @CoreHandler
    public void onProxy(ProxyConnectEvent event) {
        Proxy p = event.getProxy();
        System.out.println("Из плагина мы тут поняли, что " + p.getName() + " вошел))");
    }

}
```
plugin.json
```json
{
    "name": "TestPlugin",
    "main": "org.kvlt.testplugin.TestPlugin"
}
```
config.json
```json
{
    "test": "random string"
}
```
Отправка пакета
```java
Packet p = new Packet(params...);
...
p.send(Channel channel);
p.send(Destination destination);
p.send(Destination destination, String nodeName);
```
Вызов события
```java
CoreEvent event = CoreEvent(params...);
...
event.invoke();
```
Обработка события
```java
public class TestEvents implements CoreListener {

    @CoreHandler
    public void onJoin(PlayerJoinEvent event) {
        OnlinePlayer p = event.getPlayer();
        Proxy proxy = event.getProxy();

        System.out.println(String.format("%s вошел на прокси %s",
                p.getName(),
                proxy.getName()));
    }

}
```
Регистрация обработчика событий
```java
CoreListener coreListener = new CoreListener();
CoreServer.get().getEventManager().registerListener(coreListener);
```
Регистрация команд
```java
CoreServer.get().getCommandListener().register(new TestPluginCommand());
```
TestPluginCommandjava
```java
public class TestPluginCommand extends Command {

    public TestPluginCommand() {
        super("hi");
    }

    @Override
    protected boolean execute() {
        System.out.println("hello!");
        return true;
    }
}

```
### События
* PacketEvent
* ProxyConnectEvent
* ProxyDisconnectEvent
* ServerConnectEvent
* ServerDisconnectEvent
* PlayerJoinEvent
* PlayerQuitEvent
* PlayerSwitchServerEvent
##### Что сделано:
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
```
## CoreBukkit
Работает, гоняет пакеты, хорошо всё там.
## CoreBungee
Что-то делает. Работает и ладно.
