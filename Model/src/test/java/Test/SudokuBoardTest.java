package Test;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import org.junit.*;
import sudoku.*;
import static sudoku.BacktrackingSudokuSolver.Field;

public class SudokuBoardTest {
	@Test
	public void checkEveryBox()
	{
		SudokuBoard sb = new SudokuBoard();
		BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
		ss.solve(sb, new Field(0,0));
		int[][] tmp = new int[9][9];
		for (int i = 0, tmp3 = 0; i < 3 ; i ++)
			for(int j = 0 ; j < 3 ; j ++, tmp3++) {
				tmp[0][tmp3] = sb.get(i, j);
				tmp[1][tmp3] = sb.get(i + 3, j);
				tmp[2][tmp3] = sb.get(i + 6, j);
				tmp[3][tmp3] = sb.get(i, j + 3);
				tmp[4][tmp3] = sb.get(i, j + 6);
				tmp[5][tmp3] = sb.get(i + 3, j + 3);
				tmp[6][tmp3] = sb.get(i + 3, j + 6);
				tmp[7][tmp3] = sb.get(i + 6, j + 3);
				tmp[8][tmp3] = sb.get(i + 6, j + 6);
			}
		for(int i = 0 ; i < 9 ; i ++)
			Assert.assertEquals(true, (checkBox(tmp[i])));
	}

	private boolean checkBox(int[] tmp)
	{
		Arrays.sort(tmp);
		for(int i = 0 ; i < 9 ; i ++)
		{	
			if(!(tmp[i] == i+1))
			return false;
		}
		return true;
	}

	@Test
	public void randomBoards()
    {
        SudokuBoard sb = new SudokuBoard();
		BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
		ss.solve(sb, new Field(0,0));
        SudokuBoard sb2 = new SudokuBoard();
		ss.solve(sb2, new Field(0,0));
        Assert.assertEquals(false , sb2.equals(sb));
    }

	@Test
	public void rowCheck()
	{
		SudokuBoard sb = new SudokuBoard();
		BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
		ss.solve(sb, new Field(0,0));
		int tmp[][] = new int[9][9];
		for(int x = 0 ; x < 9; x++)
			for(int y = 0; y < 9; y++)
        		tmp[x][y] = sb.get(x, y);
        for(int i = 0 ; i < 9 ;  i ++)
        {
        	Arrays.sort(tmp[i]);
        	for(int j = 0 ; j < 9 ; j ++)
        		Assert.assertEquals(true, (checkBox(tmp[i])));
        }
	}

	@Test
	public void columnCheck()
	{
		SudokuBoard sb = new SudokuBoard();
		BacktrackingSudokuSolver ss = new BacktrackingSudokuSolver();
		ss.solve(sb, new Field(0,0));
		int tmp[][] = new int[9][9];
		for(int x = 0 ; x < 9; x++)
			for(int y = 0; y < 9; y++)
				tmp[x][y] = sb.get(x, y);
        for(int i = 0 ; i < 9 ; i ++)
        	for(int j = 0 ; j < 9 ; j ++)
        	{
        		if(i == j )
        			continue;
        		else
        		{
        			 int t = tmp[i][j];
        			 tmp[i][j] = tmp[j][i];
        			 tmp[j][i] = t;
        		}
        	}
        for(int i = 0 ; i < 9 ;  i ++)
        {
        	Arrays.sort(tmp[i]);
        	for(int j = 0 ; j < 9 ; j ++)
        		Assert.assertEquals(true, (checkBox(tmp[i])));
        }
	}
	@Test
	public void unsolvable()
	{
		SudokuBoard sb = new SudokuBoard();
		SudokuSolver ss = new BacktrackingSudokuSolver();
		for(int i = 0, val =0; i < 3; i++)
			for(int j = 0; j < 3; j++, val++)
				sb.set(i,j,1);
		sb.set(0,8,1);
		boolean b = ss.solve(sb, new BacktrackingSudokuSolver.Field(0, 0));
		Assert.assertEquals(false, b);
	}

	@Test
	public void equalsTestTrue()
	{
		SudokuBoard sb1 = new SudokuBoard();
		Assert.assertEquals(true, sb1.equals(sb1));
	}

	@Test
	public void equalsTestFalse()
	{
		SudokuBoard sb1 = new SudokuBoard();
		SudokuField field = new SudokuField();
		Assert.assertEquals(false, sb1.equals(field));
	}

	@Test
	public void hashCodeTest()
	{
		SudokuBoard sb1 = new SudokuBoard();
		SudokuBoard sb2 = new SudokuBoard();
		Assert.assertNotEquals(sb1.hashCode(), sb2.hashCode());
	}

	//@Test
	//public void toStringTest()
	//{
	//	SudokuBoard sb = new SudokuBoard();
	//	System.out.println(sb.toString());
	//}
}
