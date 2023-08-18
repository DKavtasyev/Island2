package com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Herbivorous;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;

@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Duck.yaml")
public class Duck extends Herbivorous
{
	public Duck()
	{
		foodMap.put(Caterpillar.class, 90);
		foodMap.put(Plant.class, 100);
	}
}


//		enemies.add(Bear.class);
//		enemies.add(Boa.class);
//		enemies.add(Eagle.class);
//		enemies.add(Fox.class);
//		enemies.add(Wolf.class);
