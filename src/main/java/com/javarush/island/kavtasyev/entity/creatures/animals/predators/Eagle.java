package com.javarush.island.kavtasyev.entity.creatures.animals.predators;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Predator;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Duck;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Mouse;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Rabbit;

/**
 * Орлы́ (лат. Aquila) — род хищных птиц семейства ястребиных. Длина тела 75—88 см, хвост довольно короткий,
 * крылья широкие, до 2,4 м в размахе, ноги оперены до пальцев. Распространены в Евразии, Африке и Северной
 * Америке от лесотундры до пустынь. Гнездятся на земле, скалах или деревьях. Питаются мелкими и средней величины
 * позвоночными (высматривают, паря в воздухе, или подкарауливают, сидя на возвышенном месте), иногда падалью.
 */

@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Eagle.yaml")
public class Eagle extends Predator
{
	public Eagle()
	{
		foodMap.put(Duck.class, 80);
		foodMap.put(Fox.class, 10);
		foodMap.put(Mouse.class, 90);
		foodMap.put(Rabbit.class, 90);
	}
}
