package com.javarush.island.kavtasyev.factory;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.javarush.island.kavtasyev.abstraction.Config;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.creatures.animals.Animal;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.exception.CreateObjectException;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

public class CreaturesFactory
{
	private static final YAMLMapper MAPPER = new YAMLMapper();
	private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

	public static <T> T getInstance(Class<T> clazz, Cell currentCell) throws IOException, CreateObjectException
	{
		String yamlFile = getYAMLPath(clazz);
		T creature = MAPPER.readValue(readYAMLContent(yamlFile), clazz);
		Image image = getImageForCreature(creature);
		((Creature) creature).setImage(image);
		((Creature) creature).setCurrentCell(currentCell);
		setParameters((Creature) creature);
		return creature;
	}

	private static <T> String getYAMLPath(Class<T> clazz) throws CreateObjectException
	{
		if (!clazz.isAnnotationPresent(Config.class))
			throw new CreateObjectException(String.format("Класс %s должен иметь аннотацию @Config!", clazz.getSimpleName())); //TODO определить тип Exception

		Config config = clazz.getAnnotation(Config.class);
		return config.yamlFile();
	}

	private static String readYAMLContent(String yamlFile)
	{
		String yamlContent = "";
		try(BufferedReader reader = new BufferedReader(new FileReader(yamlFile)))
		{
			StringBuilder builder = new StringBuilder();
			while (reader.ready())
			{
				builder.append(reader.readLine()).append("\n");
			}
			yamlContent = builder.toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return yamlContent;
	}

	private static <T> Image getImageForCreature(T creature)
	{
		String s = ((Creature)creature).getImagePath();
		Image image;
		try(FileInputStream fileInputStream = new FileInputStream(s))
		{
			image = new Image(fileInputStream);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return image;
	}

	private static void setParameters(Creature creature)
	{
		if (creature instanceof Animal)
		{
			creature.setAge(RANDOM.nextInt( creature.getMaxAge() ));
			creature.setMale(RANDOM.nextBoolean());
			creature.setWantToEat(creature.getMassOfFood() == 0 ? 0 : RANDOM.nextDouble( creature.getMassOfFood() / 2, creature.getMassOfFood() ));
		}
	}

	public static Creature clone(Creature creature)
	{
		Creature newInstance = ((Animal) creature).clone();

		Image image = getImageForCreature(newInstance);
		newInstance.setImage(image);
		newInstance.setCurrentCell(creature.getCurrentCell());
		setParameters(newInstance);
		return newInstance;
	}
}
