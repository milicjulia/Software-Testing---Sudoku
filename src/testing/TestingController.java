package testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JPanel;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.*;

import nl.elridge.sudoku.controller.*;
import nl.elridge.sudoku.model.Game;
import nl.elridge.sudoku.model.UpdateAction;
import nl.elridge.sudoku.view.Field;
import nl.elridge.sudoku.view.Sudoku;
import nl.elridge.sudoku.view.SudokuPanel;

public class TestingController{
	private static Game igra=new Game();
	private static SudokuPanel sudokuPanel=new SudokuPanel();
	private static SudokuController sudokuController;
	
	
	@Test
	 public void testActionPerformed_New() {
		
		Sudoku sud=new Sudoku();
		sud.buttonPanel.getBtnNew().doClick();
		String lbl=sud.buttonPanel.getBtnNew().getLabel();
		assertEquals(lbl, "New");
		
		sud=new Sudoku();
		sud.buttonPanel.getBtnCheck().doClick();
		lbl=sud.buttonPanel.getBtnCheck().getLabel();
		assertEquals(lbl, "Check");
		
		sud=new Sudoku();
		sud.buttonPanel.getBtnNumber(1).doClick();
		lbl=sud.buttonPanel.getBtnNumber(1).getLabel();
		assertEquals(lbl, "2");
		
		sud=new Sudoku();
		sud.buttonPanel.getCbHelp().doClick();
		lbl=sud.buttonPanel.getCbHelp().getLabel();
		assertEquals(lbl, "Help on");
		
		//sud=new Sudoku();
		//sud.buttonPanel.getBtnExit().doClick();
		//lbl=sud.buttonPanel.getBtnExit().getLabel();
		//Assertions.assertEquals(lbl, "Exit");
		
		//igra=new Game();
	}
	
	
/*
	@Test
	public void testMousePressed() {
       Game game=new Game();
       game.setSelectedNumber(2);
     SudokuPanel  sudokuPanel=new SudokuPanel();
     SudokuController sudokuController=new SudokuController(sudokuPanel, game);
       int xx1=0, yy1=0,xx2=0,yy2=0;
       //xx1=sudokuPanel.getPanels()[1][1].getX();
      // yy1=sudokuPanel.getPanels()[1][1].getY();
       xx2=sudokuPanel.getPanels()[1][1].getWidth()/2;
       yy2=sudokuPanel.getPanels()[1][1].getHeight()/2;
       
		
       MouseEvent mousePressed=new MouseEvent(sudokuController.getSudokuPanel().getPanels()[1][1], MouseEvent.MOUSE_PRESSED, 0, 0, xx2,yy2,0 ,0 , 1, false, MouseEvent.BUTTON1);
		JPanel panel = (JPanel)mousePressed.getSource();
        Component component = panel.getComponentAt(mousePressed.getPoint());
		//if (component instanceof Field) {
		Field field = (Field)component;
            int x = field.getFieldX();
            int y = field.getFieldY();
            //if(game.getNumber(x, y) == 0 || field.getForeground().equals(Color.BLUE)) {
            game.setNumber(x, y, 2);
            sudokuPanel.update(game, UpdateAction.CANDIDATES);
           sudokuController.mousePressed(mousePressed);
          //  }
	//	}
		//else assertNull(mousePressed.getComponent());
	}
	*/
	
	@Test
	public void testMouseClicked() {
		SudokuController sud=new SudokuController(sudokuPanel,igra);
		sud.mouseClicked(null);
	}
	
	@Test
	public void testMouseEntered() {
		SudokuController sud=new SudokuController(sudokuPanel,igra);
		sud.mouseEntered(null);
	}
	
	@Test
	public void testMouseExited() {
		SudokuController sud=new SudokuController(sudokuPanel,igra);
		sud.mouseExited(null);
	}
	
	@Test
	public void testMouseReleased() {
		SudokuController sud=new SudokuController(sudokuPanel,igra);
		sud.mouseReleased(null);
	}
	

	
	

	
	
	
	
}
	