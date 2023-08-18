package com.javarush.island.kavtasyev.entity.creatures.animals.predators;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Predator;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Duck;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Mouse;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Rabbit;

/**
 * Обыкновенный удав (лат. Boa constrictor) — неядовитая змея из подсемейства удавов
 * семейства ложноногих. Очень агрессивен. Распространён в Южной и Центральной Америке
 * и на Малых Антильских островах. Предпочитает селиться в сухих местах, но поблизости
 * от водоёмов. Интродуцирован во Флориде (США).
 * Питается мелкими млекопитающими, птицами, иногда рептилиями.
 */
@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Boa.yaml")
public class Boa extends Predator
{
	public Boa()
	{
		foodMap.put(Duck.class, 10);
		foodMap.put(Fox.class, 15);
		foodMap.put(Mouse.class, 40);
		foodMap.put(Rabbit.class, 20);
	}
}

//		enemies.add(Bear.class);
