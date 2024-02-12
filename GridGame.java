import java.util.Scanner;
import java.util.Random;

public class GridGame {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("WELCOME TO GRID GAME");
		// Prompt user to enter the size of the grid
		System.out.print("Please enter the Grid size: ");
		int N = input.nextInt();
		int[][] grid = new int[N][N];
		placeItems(grid, N);
		int maxMovements = (int) (2.5 * N);
		//System.out.println("Moves: " + maxMovements);
		int movements = 0;
		int itemsLeft = N / 2;
		int[] currentPosition = { 0, 0 };
		while (true) {
			displayGrid(grid, currentPosition);
			System.out.print("Choose Your Direction (R,L,U,D): ");
			char direction = input.next().charAt(0);

			if (direction == 'X') {
				System.out.println("Exiting Game");
				break;
			} else if (direction == 'R' && currentPosition[1] < N - 1) {
				currentPosition[1]++;
			} else if (direction == 'L' && currentPosition[1] > 0) {
				currentPosition[1]--;
			} else if (direction == 'D' && currentPosition[0] < N - 1) {
				currentPosition[0]++;
			} else if (direction == 'U' && currentPosition[0] > 0) {
				currentPosition[0]--;
			} else {
				System.out.println("Invalid direction. Please enter R,L,U,D.");
				continue;
			}
			if (grid[currentPosition[0]][currentPosition[1]] == 5) {
				itemsLeft--;
				System.out.println("Items collected! Items left: " + itemsLeft);
				grid[currentPosition[0]][currentPosition[1]] = 1;
			} else {
				grid[currentPosition[0]][currentPosition[1]] = 1;
			}
			movements++;
			System.out.println("Moves: " +(maxMovements- movements));
			if (itemsLeft == 0 || movements >= maxMovements) {
				if (itemsLeft == 0) {
					System.out.println("Congratulations! All items collected.");
				} else {
					System.out.println("Maximum movements reached. Game Over.");
				}
				break;

			}
		}
		input.close();

	}

	private static void placeItems(int[][] grid, int N) {
		Random random = new Random();
		int itemsToPlace = N / 2;
		while (itemsToPlace > 0) {
			int x = random.nextInt(N);
			int y = random.nextInt(N);
			if (grid[x][y] == 0) {
				grid[x][y] = 5;
				itemsToPlace--;
			}
		}

	}

	private static void displayGrid(int[][] grid, int[] currentPosition) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (i == currentPosition[0] && j == currentPosition[1]) {
					System.out.print("X "); // for person

				} else if (grid[i][j] == 1) {
					System.out.print("+ "); // for used cells
				} else if (grid[i][j] == 5) {
					System.out.print("* "); // for items
				} else {
					System.out.print("| "); // for left and right walls
				}
			}
			System.out.println();
		}
	}

}
