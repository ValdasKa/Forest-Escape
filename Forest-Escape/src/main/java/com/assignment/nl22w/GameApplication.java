package com.assignment.nl22w;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.assignment.nl22w.game.impl.GameImpl;


@SpringBootApplication
public class GameApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(GameApplication.class, args);
	    try {
	      new GameImpl("./src./main./resources/map2.txt"); 	      
	      System.out.println("File has been output!");
	      System.out.print(GameImpl.shortLenght);
	    } catch (Exception e) {
	      System.out.println("ERROR : " + e.getMessage());
	      e.printStackTrace();
	    }
	  }
	}