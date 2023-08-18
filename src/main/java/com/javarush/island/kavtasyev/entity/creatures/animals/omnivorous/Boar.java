package com.javarush.island.kavtasyev.entity.creatures.animals.omnivorous;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Omnivorous;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Caterpillar;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.Mouse;
import com.javarush.island.kavtasyev.entity.creatures.plants.Plant;

/**
 * Каба́н (лат. Sus scrofa), или вепрь, или ди́кая свинья́, — парнокопытное млекопитающее из рода кабанов семейства свиных.j
 * Кабан — всеядное парнокопытное нежвачное млекопитающее из рода кабанов (Sus). Длина тела до 175 см, высота в холке
 * до 1 м. Масса взрослого кабана обычно не превышает 100 кг, хотя может достигать 150—200 кг. Изредка в Восточной
 * Европе попадаются особи массой до 275 кг, а в Приморье и Маньчжурии — до полутонны. Ярко проявляется половой
 * диморфизм — самки меньше: высота в холке до 90 см, масса в пределах 60—180 кг. Длительность жизни животного может
 * достигать 14 лет в природе и 20 лет в неволе и охраняемых территориях. Кабан способен развивать скорость до
 * 40 км/ч. Рацион состоит преимущественно из растительности — на протяжении всего года это клубни, корни, корневища,
 * луковицы; летом и осенью возрастает доля плодов, желудей, семян, орехов, ягод, грибов; наконец, зимой животное часто
 * вынуждено довольствоваться корой деревьев, ветошью, побегами и т. п., — но включающей также различных мелких животных
 * (черви, моллюски, лягушки, ящерицы, змеи, грызуны, насекомоядные, яйца птиц и личинки насекомых) и падаль.
 * Соотношение растительной и животной пищи разнится в зависимости от сезона и природных условий.
 */
@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Boar.yaml")
public class Boar extends Omnivorous
{
	public Boar()
	{
		foodMap.put(Caterpillar.class, 90);
		foodMap.put(Mouse.class, 50);
		foodMap.put(Plant.class, 100);
	}
}


//		enemies.add(Bear.class);
//		enemies.add(Wolf.class);
