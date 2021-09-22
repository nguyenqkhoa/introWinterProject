package pkg;

import java.lang.Math;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ShipGrid extends DisplayGrid{
	private Data data = Data.getInstance();
	protected int[] point1 = {-1,-1};
	protected int[] point2 = {-1,-1};
	protected int mouseStat = 0;
	protected int[] collision = {0,0};


	public ShipGrid(int[][] prevShipList){
		this();
	}

	public ShipGrid(){
		super();
	}

	private final void formatPoints() {//make numbers behave nicely
		if (collision[0] <= 0){
			point1[0] = point1[0] + collision[0];
			collision[0] = -collision[0];
			point2[0] = point1[0] + collision[0];
		}
		if (collision[1] <= 0){
			point1[1] = point1[1] + collision[1];
			collision[1] = -collision[1];
			point2[1] = point1[1] + collision[1];
		}
	}


	@Override
	protected void addInteractible(int colIndex, int rowIndex) {
			Pane pane = new Pane();
			pane.setOnMousePressed(e -> {
					if (mouseStat == 0){
						point1[0] = colIndex-1;
						point1[1] = rowIndex-1;
						mouseStat = 1;
						System.out.println("0");
					} else if (mouseStat == 1){
						point2[0] = colIndex-1;
						point2[1] = rowIndex-1;
						mouseStat = -1;//will stop color changing after 1 ship
						int vDistance = (point2[1] - point1[1]);
						int hDistance = (point2[0] - point1[0]);
						if (vDistance == 0 && hDistance == 0){
							mouseStat = 0;
						} else {
							if (Math.abs(vDistance) > Math.abs(hDistance)){
								for (int i = 0; i < Math.abs(vDistance)+1; i++){
									int j = vDistance < 0? -i+1 : i+1;
									this.highlight(Color.BLACK, point1[0]+1, j+point1[1]);
									if (i==4){
										collision[1] = vDistance < 0? -4: 4;
										collision[0] = 0;
										break;
									}
									collision[1] = vDistance;
									collision[0] = 0;
								}
							} else {
								for (int i = 0; i < Math.abs(hDistance)+1; i++){
									int j = hDistance < 0? -i+1 : i+1;
									this.highlight(Color.BLACK, j+point1[0], point1[1]+1);
									if (i==4){
										collision[0] = hDistance < 0? -4: 4;
										collision[1] = 0;
										break;
									}
									collision[0] = hDistance;
									collision[1] = 0;
								}
							}
						}
					}
					System.out.printf("Mouse pressed at cell [%d, %d]%n", colIndex, rowIndex);
					formatPoints();
			});
			this.add(pane, colIndex, rowIndex);
		}



}
