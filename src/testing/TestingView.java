package testing;

import java.awt.Color;

import javax.swing.UIManager;

import nl.elridge.sudoku.model.Game;
import nl.elridge.sudoku.view.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.easymock.EasyMock;
import org.easymock.Mock;


public class TestingView {
	
public static ButtonPanel buttonPanel=new ButtonPanel();
public static Field field=new Field(1,2);
public static Sudoku sudoku=new Sudoku();
public static SudokuPanel sudokuPanel=new SudokuPanel();
	
	@Test
	public void testGetFieldX() {
        assertEquals(field.getFieldX(),1);
    }
	
	@Test
	public void testGetFieldY() {
       assertEquals(field.getFieldY(),2);
    }
	
	@Test
    public void testSetNumber_UserInput() {
		field.setNumber(1, true);
        assertEquals(field.getForeground(),Color.BLUE);
        field.setNumber(1, false);
        assertEquals(field.getForeground(),Color.BLACK);
        
    }
	
	@Test
    public void testSetNumber_Number() {
        field.setNumber(1, true);
        assertEquals(field.getText(),"1");
        field.setNumber(0, true);
        assertEquals(field.getText(),"");
    }

	@Test
	public void testGetBgNumbers() {
    	assertNotNull(buttonPanel.getBgNumbers());
    }
	
	@Test
	public void testGetPanels() {
		assertNotNull(sudokuPanel.getPanels());
		
	}
	
	@Test
	public void testMainSudoku_Valid() {
		Sudoku.main(null);
		assertDoesNotThrow(()->UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()));
		Sudoku.main(null);
	}
	
	@Test
	public void testMainSudoku_Invalid() {
		String str=null;
		Sudoku.main(null);
		assertThrows(NullPointerException.class,()->UIManager.setLookAndFeel(str));
	}
	
	@Test
    public void testSetGameCheck1() {
		buttonPanel.getBgNumbers().setSelected(buttonPanel.getBgNumbers().getSelection(), true);
		sudokuPanel.setGameCheck(TestingGame.game);
		assertEquals(sudokuPanel.getFields()[2][1].getBackground(),Color.WHITE );
    }
	
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4}) 
    public void testSetGameCheck2(int x) {
		sudokuPanel.getFields()[1][x].setForeground(Color.BLUE);
		sudokuPanel.setGameCheck(TestingGame.game);
		assertEquals(sudokuPanel.getFields()[1][x].getBackground(),Color.RED);
    }
	
	@Test
    public void testSetGameCheck3() {
		for(int y=0;y<9;y++)
			for(int x=0;x<9;x++)
                if (sudokuPanel.getFields()[y][x].getForeground().equals(Color.BLUE)) {
                	int sol=TestingGame.game.getSolution()[x][y];
                	TestingGame.game.getGame()[y][x]=sol;
                	TestingGame.game.getCheck()[y][x] = true;
                	sudokuPanel.setGameCheck(TestingGame.game);
                	assertEquals(sudokuPanel.getFields()[y][x].getBackground(),Color.GREEN);
                   
            }
     }
        

	
    @Test
    public void testSetCandidates_Valid() {
    	int num=TestingGame.game.getSolution()[5][5];
    	TestingGame.game.setSelectedNumber(num);
    	TestingGame.game.setHelp(true);
    	buttonPanel.getBgNumbers().setSelected(buttonPanel.getBgNumbers().getSelection(), true);
    	sudokuPanel.setCandidates(TestingGame.game);
       // assertEquals(sudokuPanel.getFields()[5][5].getBackground(),new Color(102, 153, 255));
    	//assertEquals(sudokuPanel.getFields()[5][5].getBackground(),new Color(255, 255, 255));
    }
    

    
    @Test
    public void testBtnExit() {
    	assertNotNull(buttonPanel.getBtnExit());
    }
	
	
	
}
