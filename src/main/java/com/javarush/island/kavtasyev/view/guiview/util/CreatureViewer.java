package com.javarush.island.kavtasyev.view.guiview.util;

import com.javarush.island.kavtasyev.config.IslandConfig;
import com.javarush.island.kavtasyev.entity.creatures.Creature;
import com.javarush.island.kavtasyev.entity.island.Cell;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class CreatureViewer
{
	private CreatureViewer()
	{
	}

	public static void showPicturesInCell(Cell cell)
	{
		ConcurrentHashMap<Class<? extends Creature>, Set<Creature>> cellCreatures = cell.getCellCreatures();

		for(Class<? extends Creature> clazz : cellCreatures.keySet())
		{
			Set<Creature> species = cellCreatures.get(clazz);
			for (Creature creature : species)
			{
				ImageView imageView = creature.getImageView();

				imageView.setFitWidth(IslandConfig.cellSize - 2);
				imageView.setFitHeight(IslandConfig.cellSize - 2);

				cell.getChildren().add(imageView);
			}
		}
	}

	public static void moveCreaturePicture(Creature creature, Cell oldCell)
	{
		ObservableList<Node> oldCellChildren = oldCell.getChildren();
		ObservableList<Node> newCellChildren = creature.getCurrentCell().getChildren();
		ImageView imageView = creature.getImageView();
		imageView.setFitWidth(IslandConfig.cellSize - 2);
		imageView.setFitHeight(IslandConfig.cellSize - 2);
		oldCellChildren.remove(imageView);
		newCellChildren.add(imageView);
	}

	public static void deleteCreaturePicture(Creature creature)
	{
		ObservableList<Node> children = creature.getCurrentCell().getChildren();
		ImageView imageView = creature.getImageView();
		imageView.setFitWidth(IslandConfig.cellSize - 2);
		imageView.setFitHeight(IslandConfig.cellSize - 2);
		children.remove(imageView);
	}

	public static void showCreaturePicture(Creature creature)
	{
		ObservableList<Node> children = creature.getCurrentCell().getChildren();
		ImageView imageView = creature.getImageView();
		imageView.setFitWidth(IslandConfig.cellSize - 2);
		imageView.setFitHeight(IslandConfig.cellSize - 2);
		children.add(imageView);
	}
}


//	ObservableList<Node> list = cell.getChildren();
//		for(Node node : list)
//				{
//				((ImageView) node).setImage(null);
//				}
//				list.clear();
//
//				for (Creature creature : creatures)
//				{
//				ImageView imageView = new ImageView(creature.getImage());
//
//				imageView.setFitWidth(IslandConfig.locationSize - 2);
//				imageView.setFitHeight(IslandConfig.locationSize - 2);
//
//				cell.getChildren().add(imageView);
//				}
