package task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CircleDot {

	public static void main(String[] args) {
		
		String circleFilepath = args[0];
		String pointsFilepath = args[1];
		
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new FileReader(circleFilepath));
			
			FPoint circleOrigin = readFPointFromLine(reader.readLine());
			float radius = Float.parseFloat(reader.readLine());
			
			reader.close();
			
			reader = new BufferedReader(new FileReader(pointsFilepath));
			
			String line = reader.readLine();
			while(line != null) {
				
				FPoint testPoint = readFPointFromLine(line);
				
				float distance = calcDistance(circleOrigin, testPoint);
				
				int comp = Float.compare(distance, radius);
				int result;
				
				if (comp < 0) result = 1;
				else if (comp == 0) result = 0;
				else result = 2;
				
				System.out.println(result);
				
				line = reader.readLine();
				
			}
			
			reader.close();
			
		} catch (IOException ioe) {
			
			ioe.printStackTrace();
		}

	}
	
	// Парсит прочитанную строку в FPoint (2 координаты float)
	public static FPoint readFPointFromLine(String line) {
		
		String[] coords = line.split("\\s+");
		FPoint fpoint = new FPoint(Float.parseFloat(coords[0]), Float.parseFloat(coords[1]));
		
		return fpoint;
	}
	
	// Рассчитывает расстояние между двумя точками
	public static float calcDistance(FPoint circleOrigin, FPoint testPoint) {
		
		float xdist = Math.abs(circleOrigin.getX() - testPoint.getX());
		float ydist = Math.abs(circleOrigin.getY() - testPoint.getY());
		float hyp = (float) Math.sqrt(xdist * xdist + ydist * ydist);
		
		return hyp;
	}
}
