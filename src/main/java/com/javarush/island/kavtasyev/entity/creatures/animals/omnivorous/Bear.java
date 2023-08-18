package com.javarush.island.kavtasyev.entity.creatures.animals.omnivorous;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Omnivorous;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.*;
import com.javarush.island.kavtasyev.entity.creatures.animals.predators.Boa;

@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Bear.yaml")
public class Bear extends Omnivorous
{
	public Bear()
	{
		foodMap.put(Boa.class, 80);
		foodMap.put(Boar.class, 50);
		foodMap.put(Buffalo.class, 20);
		foodMap.put(Deer.class, 80);
		foodMap.put(Duck.class, 10);
		foodMap.put(Goat.class, 70);
		foodMap.put(Horse.class, 40);
		foodMap.put(Mouse.class, 90);
		foodMap.put(Rabbit.class, 80);
		foodMap.put(Sheep.class, 70);
	}
}
