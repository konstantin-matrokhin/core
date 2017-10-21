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

_TODO: use maven dependencies_