package simulation;

import java.util.Random;

public class Network {

	int flagCount;
	int sendID;
	boolean[] visited;
	Random rand;

	Network() {
		visited = new boolean[15];
		rand = new Random();

		for (int i = 0; i < 15; i++) {
			visited[i] = false;
		}
	}

	int getStrongestWireless() {

		float curSignal;
		int curID;

		if (flagCount == 0) {
			curSignal = rand.nextFloat();
			curID = rand.nextInt(15);

			if (curSignal >= 0.8) {
				sendID = getNextUnvisited(curID);
				flagCount = 4 + rand.nextInt(6);
				return sendID;
			} else {
				return -1;
			}

		} else {
			flagCount--;
			return sendID;
		}

	}

	int getNextUnvisited(int startIndex) {
		if (visited[(startIndex)] == false) {
			visited[(startIndex)] = true;
			return startIndex;
		} else {
			for (int i = 0; i < 15; i++) {
				if (visited[(startIndex + 1 + i) % 15] == true) {
					continue;

				} else {
					visited[(startIndex + 1 + i) % 15] = true;
					return (startIndex + 1 + i) % 15;
				}
			}
			return -2;
		}

	}
}
