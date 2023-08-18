package com.javarush.island.kavtasyev.entity.creatures.animals.predators;

import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.animals.Predator;
import com.javarush.island.kavtasyev.entity.creatures.animals.herbivorous.*;
import com.javarush.island.kavtasyev.entity.creatures.animals.omnivorous.Boar;

/**
 * Волк, или се́рый волк, или обыкнове́нный волк[1] (лат. Canis lupus), — вид хищных млекопитающих из семейства псовых
 * (Canidae). Волк — одно из самых крупных современных животных в своём семействе: длина его тела (без учёта хвоста)
 * может достигать 160 см, длина хвоста — до 52 см, высота в холке — до 90 см; масса тела может достигать 90—110 кг.
 * В историческое время среди наземных млекопитающих ареал волка занимал второе место по площади после ареала человека,
 * охватывая большую часть Северного полушария; сейчас сильно сократился.
 * В Европе волк сохранился в Испании, Прибалтике, России, Белоруссии, Португалии, Италии, Польше, Скандинавии, на
 * Балканах, и на Украине. На Кавказе — в Азербайджане, Армении, и Грузии. В Азии он населяет Корею, частично Китай и
 * полуостров Индостан, Казахстан, Киргизию, Афганистан, Иран, Ирак, север Аравийского полуострова; в Японии вымерли
 * подвиды Canis lupus hodophilax и Canis lupus hattai. В Северной Америке водится от Аляски до Мексики. В России
 * отсутствует только на некоторых островах (Сахалин, Курилы).
 * Волк — типичный хищник, добывающий пищу активным поиском и преследованием жертв.
 * Основу питания волков составляют копытные животные: в тундре — северные олени; в лесной зоне — лоси, олени, косули,
 * кабаны; в степях и пустынях — антилопы. Нападают волки и на домашних животных (овец, коров, лошадей, кур), в том
 * числе на собак. Ловят, особенно одиночные волки, и более мелкую добычу: зайцев, сусликов, мышевидных грызунов. Летом
 * волки не упускают случая съесть кладку яиц, птенцов, сидящих на гнёздах или кормящихся на земле тетеревиных,
 * водоплавающих и иных птиц. Часто добывают и домашних гусей. Добычей волков порой становятся лисицы, енотовидные
 * собаки, корсаки; изредка голодные волки нападают на спящих в берлоге медведей. Зарегистрирован случай, когда стая
 * волков набросилась на молодого медведя и съела его[18]. Известно много случаев, когда ослабевшие крупные животные
 * (например, лоси, олени), раненые охотниками или сильно пострадавшие в драке в период гона, становились добычей
 * волков. В отличие от многих других хищников волки часто возвращаются к недоеденным остаткам своей добычи, особенно
 * в голодное время года. Всеядны. Не брезгуют трупами домашнего скота, а на морских побережьях — тушами тюленей и
 * других морских зверей, выброшенными на берег. В периоды бескормицы волки едят пресмыкающихся, лягушек и даже крупных
 * насекомых (жуков, саранчу). Волки, особенно в южных районах, поедают и растительные корма — разные ягоды, дикие и
 * садовые фрукты, даже грибы. В степях они часто делают набеги на бахчи арбузов и дынь, удовлетворяя не столько голод,
 * сколько жажду, поскольку нуждаются в регулярном обильном водопое.
 */
@Config(yamlFile = "src/main/java/com/javarush/island/kavtasyev/config/creatures/animals/Wolf.yaml")
public class Wolf extends Predator implements Runnable
{
	public Wolf()
	{
		foodMap.put(Boar.class, 15);
		foodMap.put(Buffalo.class, 10);
		foodMap.put(Deer.class, 15);
		foodMap.put(Duck.class, 40);
		foodMap.put(Goat.class, 60);
		foodMap.put(Horse.class, 10);
		foodMap.put(Mouse.class, 80);
		foodMap.put(Rabbit.class, 60);
		foodMap.put(Sheep.class, 70);
	}
}