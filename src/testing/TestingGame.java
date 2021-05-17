package testing;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import nl.elridge.sudoku.model.Game;

public class TestingGame {

	public static Game game=new Game();
	
	@BeforeEach
	public void resetGame() {
		game=new Game();
	}
	
	
	@Test
	public void testNewGame() {
		game=new Game();
		Assertions.assertNotNull(game);
		Assertions.assertNotNull(game.getCheck());
		Assertions.assertTrue(game.isHelp());
		Assertions.assertNotNull(game.getSolution());
	}
	
	@Test
    public void testCheckGame() {
		game.checkGame();
    	
    }
	
	@Test
	public void testSetSolution_Valid() {
		int[][] mat=new int[9][9];
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
				mat[i][j]=(int)(Math.random()*9)+1;
		game.setSolution(mat);
		assertNotNull(game);
	}
	
	
	@Test
	public void testSetHelp_On() {
		game.setHelp(true);
		assertEquals(game.isHelp(), true);
	}
	
	@Test
	public void testSetHelp_Off() {
		game.setHelp(false);
		assertEquals(game.isHelp(), false);
	}

	
	@ParameterizedTest
	@ValueSource(ints = {1, 3, 5, 8, 9})
	public void testSetSelectedNumber_Exist(int num) {
		game.setSelectedNumber(num);
		assertEquals(game.getSelectedNumber(), num);
	}
	
	@Test
	public void testSetSelectedNumber_NotExist() {
		game.setSelectedNumber(10);
		assertEquals(game.getSelectedNumber(), 10);
	}
	
	@Test
	 public void testSetNumber_Good() {
	       game.setNumber(3,1,9);  
	       assertEquals(game.getNumber(3, 1), 9);
	 }
	
	   
	   @Test
	    public void testGetNumber_GoodPos() {
		   game.getNumber(1, 5);
	    }
	
	 @Test
	    public void testIsCheckValid_Good() {
		 Assertions.assertFalse(game.isCheckValid(1, 3));
	    }
	 
	 @Test
	    public void testIsCheckValid_WrongPos() {
		 Assertions.assertFalse(game.isCheckValid(0, 5));
	 }

	   
	 @Test
	    public void testIsPossibleX_Valid() {
		 game=new Game();
		//Assertions.assertTrue(game.isPossibleX(game.getGame(), 5, 1));
		game.getGame()[2][3]=1;
		Assertions.assertFalse(game.isPossibleX(game.getGame(), 2, 1));
	    }
	 
	 
	 @Test
	    public void testIsPossibleX_WrongNum() {
	       Assertions.assertTrue(game.isPossibleX(game.getGame(), 2, 10));
	    }


	 @Test
	    public void testIsPossibleY() {
		 int num;
    	 for(int i=0;i<9;i++)
    		 if(game.getNumber(i, i)==0) {
    			 num=game.getSolution()[i][i];
    			 Assertions.assertTrue(game.isPossibleY(game.getGame(), i, num));   
    			 break;
    		 }
		
	    }

	 
	 @Test
	    public void testIsPossibleY_WrongNum() {
	       Assertions.assertTrue(game.isPossibleY(game.getGame(), 2, 10));
	    }

     
     @Test
    public void testIsPossibleBlock(){
    	 int num;
    	 for(int i=0;i<9;i++)
    		 if(game.getNumber(i, i)==0) {
    			 num=game.getSolution()[i][i];
    			 assertTrue(game.isPossibleBlock(game.getGame(), i, i, num));   
    			 break;
    		 }
    }
	 
	 @Test
     public void testGetNextPossibleNumber() {
		 int num=0;
		 for(int i=0;i<9;i++) {
			 for(int j=0;j<9;j++) {
			 if(game.getNumber(i, j)==0) {
				 num=game.getSolution()[j][i];
				 List<Integer> numbers = new ArrayList<Integer>();
		    	 numbers.add(num);
		    	 numbers.add(2);
		    	 numbers.add(1);
		    	 assertEquals(game.getNextPossibleNumber(game.getGame(), i, j,numbers),num);
				 break;
				 }
			 }
		 }
    	 
    	
    }
     
	 @Test
     public void testGetNextPossibleNumber_Empty() {
    	 List<Integer> numbers = new ArrayList<Integer>();
    	 game.getNextPossibleNumber(game.getGame(), 1, 2,numbers);
     }
     
	
	 @Test
	    public void testGenerateSolution_Good() {
	       assertNotNull(game.generateSolution(new int[9][9], 0));
	    }
	 
	 
	 @Test
	    public void testGenerateSolution_WrongIndex1() {
	       assertNotNull(game.generateSolution(new int[9][9], 3));
	    }
	 
	@Test
	    public void testGenerateSolution_WrongIndex2() throws FileNotFoundException {
		String line = "";  
		String splitBy = ",";  
		int i=0;
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Julija\\Desktop\\data.csv")); 
		try {
			while ((line = br.readLine()) != null){  
				String[] num = line.split(splitBy); 
				Assertions.assertNotNull(game.generateSolution(new int[9][9], Integer.parseInt(num[i])));
				i++;
			 }
		} catch (Exception e) {e.printStackTrace();} 
		
	    }
	 
	 
	 @Test
	    public void testGenerateGame1() {
	       Assertions.assertNotNull(game.generateGame(game.getSolution()));
	    }
	 
	@Test
	    public void testGenerateGame2_Good() {
	    	List<Integer> positions = new ArrayList<Integer>();
	    	positions.add(3); positions.add(2);
	    	Assertions.assertNotNull(game.generateGame(game.getGame(), positions));
	    }
	    
	@Test
	    public void testGenerateGame2_Empty() {
	    	List<Integer> positions = new ArrayList<Integer>();
	    	Assertions.assertNotNull(game.generateGame(game.getGame(), positions));
	    }
	
	 @Test
    public void testIsValid() {
		 Assertions.assertTrue(game.isValid(game.getGame()));
    }
	 
	 @	ParameterizedTest
		@ValueSource(ints = {50, 75, 80})
	    public void  testIsValid2_NotOver(int num) {
		 int index=num;
		 int x=index%9;
		 int y=index/9;
		 int[] numberofSolutions=new int[1];
		 numberofSolutions[0]=1;
		 
			 if(!game.isValid(game.getGame(), index+1, numberofSolutions) || game.getGame()[y][x]==0) {
				 Assertions.assertFalse(game.isValid(game.getGame(), index+1, numberofSolutions));
			 } else Assertions.assertTrue(game.isValid(game.getGame(), index+1, numberofSolutions));
		 }
	 
	 
	 @Test
	    public void  testIsValid2_Over() {
		 int[] numberofSolutions=new int[1];
		 numberofSolutions[0]=0;
		 String line = "";  
		 String splitBy = ",";  
		 int i=0;
		 try {
			 BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Julija\\Desktop\\data.csv")); 
			while ((line = br.readLine()) != null){  
				String[] num = line.split(splitBy); 
				assertTrue(game.isValid(game.getGame(), Integer.parseInt(num[i]), numberofSolutions));
				i++;
			}
		 } catch (Exception e) {e.printStackTrace();} 
	    }
	 
	 
	 	@Test
	    public void testIsSelectedNumberCandidate_Valid() {
	 		int num;
	 		for(int i=0;i<9;i++)
	 			for(int j=0;j<9;j++)
	 				if(TestingGame.game.getNumber(i, j)==0) {
	 					num=TestingGame.game.getSolution()[j][i];
	 					game.setSelectedNumber(num);
	 					Assertions.assertTrue(game.isSelectedNumberCandidate(i, j));
	 				}
	    }
	 	
	 	
	 	@AfterEach
	 	public void testexit() {}
	

    
}
