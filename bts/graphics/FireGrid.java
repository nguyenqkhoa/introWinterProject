package graphics;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class FireGrid extends DisplayGrid {
	protected int[] point = new int[2];
	protected int mouseStat = 0;

	public FireGrid() {
		super();
		point[0] = -1;
		point[1] = -1;
	}

	@Override
	protected void addInteractible(int colIndex, int rowIndex) {
		Pane pane = new Pane();
		pane.setOnMousePressed(e -> {
			point[0] = colIndex - 1;
			point[1] = rowIndex - 1;
			refresh();
			highlight(Color.BLACK, colIndex, rowIndex);
			System.out.printf("Mouse pressed at cell [%d, %d]%n", colIndex, rowIndex);
			injected();

		});
		this.add(pane, colIndex, rowIndex);
	}

	public void injected() {
	}

	@Override
	public void refresh() {
		clear();
		for (int i = 0; i < 100; i++) {
			if (data.getTurnPlayer().sqHit.contains(i) && data.getEnemyPlayer().sqNotHit.contains(i)) {
				this.highlight(Color.BLUE, i % 10 + 1, i / 10 + 1);
			} else if (data.getTurnPlayer().sqHit.contains(i)) {
				this.highlight(Color.BLACK, i % 10 + 1, i / 10 + 1);
			} else {
				this.highlight(Color.WHITE, i % 10 + 1, i / 10 + 1);
				addInteractible(i % 10 + 1, i / 10 + 1);
			}
		}
	}
}
