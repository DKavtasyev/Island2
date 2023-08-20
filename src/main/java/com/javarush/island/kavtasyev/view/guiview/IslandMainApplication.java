package com.javarush.island.kavtasyev.view.guiview;

import com.javarush.island.kavtasyev.config.IslandConfig;
import com.javarush.island.kavtasyev.entity.Island;
import com.javarush.island.kavtasyev.entity.Statistics;
import com.javarush.island.kavtasyev.entity.island.Cell;
import com.javarush.island.kavtasyev.view.guiview.controller.IslandConfigPaneController;
import com.javarush.island.kavtasyev.view.guiview.controller.IslandRootLayoutController;
import com.javarush.island.kavtasyev.view.guiview.util.CreatureViewer;
import com.javarush.island.kavtasyev.view.guiview.util.GUIExchanger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class IslandMainApplication extends Application
{
	private Stage primaryStage;
	BorderPane islandRootLayout;
	private Cell[][] cells;
	Island island;
	Statistics statistics;

	public IslandMainApplication()
	{

	}

	@Override
	public void start(Stage stage)
	{
		this.primaryStage = stage;
		this.primaryStage.setTitle("Настройки");
		primaryStage.setScene(getConfigScene());
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	private Scene getConfigScene()
	{
		Scene scene = null;
		try
		{
			URL fxmlLocation = IslandMainApplication.class.getResource("/view/IslandConfigPane.fxml");
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Pane islandConfigLayout = loader.load();                                                                    // Можно дополнительно сделать установку минимальных и максимальных параметров для слайдеров из параметров IslandConfig
			scene = new Scene(islandConfigLayout);

			IslandConfigPaneController controller = loader.getController();
			controller.setIslandMainApplication(this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return scene;
	}

	/**
	 * Формирует корневое окно программы из файла .fxml. Устанавливает в центр окна главную сцену острова. Передаёт
	 * контроллеру корневого окна программы ссылку на объект данного класса. Запускается при нажатии пользователем
	 * кнопки "Старт".
	 */
	public void initIslandRootLayout()
	{
		try
		{
			URL fxmlLocation = IslandMainApplication.class.getResource("/view/IslandRootLayout.fxml");
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			islandRootLayout = loader.load();
			Scene scene = new Scene(islandRootLayout);
			primaryStage.setTitle("Остров");
			primaryStage.setScene(scene);
			primaryStage.centerOnScreen();

			IslandRootLayoutController controller = loader.getController();
			controller.setIslandMainApplication(this);

			showIslandScene();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Устанавливает сцену острова в центр корневого окна программы. Вызывается в методе initIslandRootLayout данного
	 * класса.
	 */
	private void showIslandScene()
	{
		try
		{
			island = GUIExchanger.islandExchanger.exchange(island);
			cells = island.getCells();

			IslandPane islandPane = new IslandPane(island);
			islandRootLayout.setCenter(islandPane.getIslandPane());
			islandRootLayout.setCenterShape(true);																		// Центрирует форму в пределах области width, height
			primaryStage.setWidth(IslandConfig.sceneWidth);
			primaryStage.setHeight(IslandConfig.sceneHeight + 100 + 25);
			primaryStage.setResizable(false);
			primaryStage.centerOnScreen();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void showStatisticsDialog()
	{
		try
		{
			URL fxmlLocation = IslandMainApplication.class.getResource("/view/IslandStatisticsLayout.fxml");
			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			Pane islandConfigLayout = loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Параметры");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(islandConfigLayout);
			dialogStage.setScene(scene);

			IslandRootLayoutController controller = loader.getController();
			controller.setStatisticsStage(dialogStage);

			dialogStage.showAndWait();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void displayCreatures()
	{
		for (Cell[] lineOfCells : cells)
		{
			for (Cell cell : lineOfCells)
			{
				CreatureViewer.showPicturesInCell(cell);
			}
		}
	}

	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	public Cell[][] getCells()
	{
		return cells;
	}
}