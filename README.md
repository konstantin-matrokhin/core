# LastCraft Core
Проект состоит из 4 частей:
* **CoreAPI** - API для взаимодействия
* **CoreCLI** - приложение для управления серверами для командной строки
* **CoreBukkit** - плагин для Spigot/Bukkit
* **CoreBungee** - плагин для BungeeCord

## CoreAPI
Содержит все необходимые общие для всех проектов элементы.
Отсюда и задается поведение пакетов, пришедших на один из серверов (__CLI, Bukkit, Proxy__).

## ColeCLI
Для запуска используйте
```
java -cp CoreCLI.jar;lib/* org.kvlt.core.CoreCLI
```

### Зависимости (Maven):
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