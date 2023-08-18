package com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Herbivorous;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;

@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Deer.yaml")
public class Deer extends Herbivorous
{
	public Deer()
	{
		foodMap.put(Plant.class, 100);
	}
}


//		enemies.add(Bear.class);
//		enemies.add(Wolf.class);
