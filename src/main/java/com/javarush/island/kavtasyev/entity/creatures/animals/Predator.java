package com.javarush.island.kavtasyev.entity.creatures.animals;

public abstract class Predator extends Animal
{
}


//	protected static final ArrayList<Class<? extends Creature>> enemies = new ArrayList<>();


//	protected Queue<Cell> queue = new ArrayDeque<>();
//	protected ArrayList<Cell> observedCells = new ArrayList<>();

//	private Coordinates getCoordinatesWithAim(Collection<Class<? extends Creature>> creaturesClasses)
//	{
//		synchronized (currentCell.getNeighbors())
//		{
//			Coordinates coordinates = currentCell.getCoordinates();
//			Cell observableCell = currentCell;
//			queue.add(observableCell);
//
//			if (hasFood(observableCell))
//			{
//				queue.clear();
//				return coordinates;
//			}
//
//			int step = 0;
//
//			while(!queue.isEmpty() && !hasFood(observableCell))
//			{
//				observableCell = queue.remove();                        // Ячейка удаляется из очереди на просмотр на наличие еды
//				observedCells.add(observableCell);                      // Ячейка добавляется в просмотренные. Но возможно она с едой
//				if(!hasFood(observableCell) && step < 9)
//				{
//					for(Cell cell : observableCell.getNeighbors())
//					{
//						if(!observedCells.contains(cell) && !queue.contains(cell))
//							queue.add(cell);
//					}
//					step++;
//				}
//				else if (hasFood(observableCell))
//					return observableCell.getCoordinates();
//				else if (queue.isEmpty())
//					return null;
//			}
//		}
//		return null;
//	}

//	@Override
//	protected Coordinates lookForFood()
//	{
//
//	}

//	@Override
//	Coordinates lookForBreed()
//	{
//		return null;
//	}

//	@Override
//	void catchUpTheFood(Coordinates coordinatesWithFood)
//	{
//
//	}
