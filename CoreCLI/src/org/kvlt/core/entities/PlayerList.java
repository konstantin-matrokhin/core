package org.kvlt.core.entities;

import java.util.HashSet;

/**
 * Список игроков
 * @param <T> должен содержать класс игроков из CoreAPI
 */
public class PlayerList<T extends ServerPlayer> extends HashSet<T> {

    /**
     * Передается объект, но в любом случае удаление происходит по имени
     * Ищет по всему списку подходящего по имени игрока и удаляет его
     */
    @Override
    public boolean remove(Object o) {
        T target = (T) o;
        return removeIf(p -> p.getName().equalsIgnoreCase(target.getName()));
    }

    /**
     * Передается имя игрока и через поиск удаляется ссылка на объект игрока с таким именем
     * @param name кого удаляем
     * @return истина, если объект найден и удален
     */
    public boolean remove(String name) {
        return removeIf(p -> p.getName().equalsIgnoreCase(name));
    }

    /**
     * Проверяет есть ли игрок с таким именем в списке
     * @param name имя игрока
     * @return истина, если имя найдено в списке, иначе - <b>null</b>
     */
    public boolean contains(String name) {
        return stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null) != null;
    }

    /**
     * Работает также, как и #contanins(String name), но принимает объект и берет его имя для поиска
     * @param o Ссылка на игрока
     * @return истина, если найдено имя в списке, иначе - <b>null</b>
     */
    @Override
    public boolean contains(Object o) {
        T target = (T) o;
        return stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(target.getName()));
    }

    /**
     * @param name принимает имя игрока
     * @return объект игрока из списка
     */
    public T get(String name){
        return stream().filter(p ->
                p.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
