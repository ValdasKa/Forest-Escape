package com.assignment.nl22w.game.impl;

import com.assignment.nl22w.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

@Component
@Slf4j
public class GameImpl {
	 private char[][] theForest;
	  private int colStart, rowStart;
	  private int rows, cols;
	  private String outputFilename;
	  int visited[][];
	  public static int shortLenght = 1;
	  
	  
	  
	  public GameImpl(String filename) throws IOException {
	    try {
	      this.outputFilename = filename;
	      Scanner scan = new Scanner(new File(filename));
	      StringBuilder sb = new StringBuilder();
	      while (scan.hasNext()) {
	        sb.append(scan.nextLine());
	        this.rows++;
	      }
	      this.cols = sb.length() / this.rows;
	      this.theForest = new char[this.rows][this.cols];
	      int m = 0;
	      System.out.println();
	      for (int i = 0; i < this.rows; i++) {
	        for (int j = 0; j < this.cols; j++) {
	          this.theForest[i][j] = sb.charAt(m++);
	        }
	      }
	      scan.close();
	      findStart();
	      findPathOut(this.rowStart, this.colStart);
	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	      System.out.println("ERROR : " + e.getMessage());
	    }
	  }

	  private void findStart() {
	    for (int i = 0; i < this.rows; i++) {
	      for (int j = 0; j < this.cols; j++) {
	        if (theForest[i][j] == 'X') {
	          this.rowStart = i;
	          this.colStart = j;
	        }
	      }
	    }
	  }

	 
	  private boolean findPathOut(int row, int col) {
	    char right = this.theForest[row][col + 1];
	    char left = this.theForest[row][col - 1];
	    char up = this.theForest[row - 1][col];
	    char down = this.theForest[row + 1][col];
	    if (right == 'G' || left == 'G' || up == 'G' || down == 'G') {
	      this.theForest[row][col] = '.'; 
	      try {
	        File file = new File(this.outputFilename + ".pathfound"); // creating an output file and naming
	        
	        PrintWriter writer = new PrintWriter(file);
	        for (int i = 0; i < this.rows; i++) {
	          for (int j = 0; j < this.cols; j++) {
	            writer.print(this.theForest[i][j]);
	          }
	          writer.println();
	        }
	        writer.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("ERROR : " + e.getMessage());
	      }
	      
	      return true; 
	    }

	    boolean pathfound = false;
	    
	    if (this.theForest[row][col] != 'X') {
	      this.theForest[row][col] = '.'; 
	    }
	    if (right == ' ' && !pathfound) {
	      pathfound = findPathOut(row, col + 1);
	      shortLenght++;
	    }
	    if (down == ' ' && !pathfound) {
	      pathfound = findPathOut(row + 1, col);
	      shortLenght++;
	    }
	    if (left == ' ' && !pathfound) {
	      pathfound = findPathOut(row, col - 1);
	     shortLenght++;
	    }
	    if (up == ' ' && !pathfound) {
	      pathfound = findPathOut(row - 1, col);
	      shortLenght++;
	    }
	    if (!pathfound) {
	      this.theForest[row][col] = ' ';  // from no needed path marks as clear
	      shortLenght--; // take - from distance
	    }
	    
	    return pathfound; 
	    
	    
	  }
	  
}
