package testing;

import org.easymock.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.UIManager;

import nl.elridge.sudoku.controller.SudokuController;
import nl.elridge.sudoku.model.Game;
import nl.elridge.sudoku.model.UpdateAction;
import nl.elridge.sudoku.view.*;


public class EasyMockTests{
	
	
	 @Test
	    public void testGenerateSolution_Good() {
		 int[][] sol=null;
		 int[][] ret=null;
		 Game game=EasyMock.mock(Game.class);
		 EasyMock.expect(game.generateSolution(new int[9][9], 0)).andReturn(sol);
		 ret=game.generateSolution(new int[9][9], 0);
	      assertEquals(sol,ret);
	    }
	 
	 @Test
	    public void testSetNumber_UserInput() {
		 Field field = EasyMock.mock(Field.class);
			field.setNumber(1, true);
	        EasyMock.expect(field.getForeground()).andReturn(Color.BLUE);
	        field.setNumber(1, false);
	        EasyMock.expect(field.getForeground()).andReturn(Color.BLACK);
	        EasyMock.replay(field);
	        
	    }
	 
	 @Test
	    public void testIsPossibleX_WrongPos() {
		 Game game=EasyMock.mock(Game.class);
		 int[][] sudoku=new int[9][9];
		 EasyMock.expect(game.getGame()).andReturn(sudoku);
 		 EasyMock.expect(game.isPossibleX(sudoku, -5, 10)).andThrow(new ArrayIndexOutOfBoundsException());
	    }
	 
	 @Test
	    public void testIsPossibleY_WrongPos() {
		 Game game=EasyMock.mock(Game.class);
		 int[][] sudoku=new int[9][9];
		 EasyMock.expect(game.getGame()).andReturn(sudoku);
		 EasyMock.expect(game.isPossibleY(sudoku, -5, 10)).andThrow(new ArrayIndexOutOfBoundsException());
	    }
	
	 @Test()
	 public void testSetNumber_WrongPos() {
		 Game game=EasyMock.mock(Game.class);
		 game.setNumber(-1, -10, 3);
		 EasyMock.expectLastCall().andThrow(new ArrayIndexOutOfBoundsException());
	}
	
	   @Test()
	    public void testGetNumber_WrongPos() {
		   Game game=EasyMock.mock(Game.class);
		   EasyMock.expect( game.getNumber(-1, -10)).andThrow(new ArrayIndexOutOfBoundsException());
		 
		  }
	 
}
