/* Copyright (C) 2024 Jittakan Damrongtrakoonwat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package ttsu.game.tictactoe;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class testToString_GameBoard {

  @Test
  public void testToString_emptyBoard() {
    GameBoard board = new GameBoard();
    String expected = "   \n   \n   \n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_fullBoard() {
    GameBoard board = createBoard(new Player[][] {
            { Player.X, Player.O, Player.X },
            { Player.O, Player.X, Player.O },
            { Player.X, Player.O, Player.X } });
    String expected = "XOX\nOXO\nXOX\n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_partiallyFilledBoard() {
    GameBoard board = createBoard(new Player[][] {
            { Player.X, null, Player.X },
            { null, Player.O, null },
            { Player.X, null, Player.O } });
    String expected = "X X\n O \nX O\n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_winningLine() {
    GameBoard board = createBoard(new Player[][] {
            { Player.X, Player.O, Player.X },
            { Player.O, Player.X, Player.O },
            { Player.X, null, null } });
    String expected = "XOX\nOXO\nX  \n";
    assertEquals(expected, board.toString());
  }

  // Helper method to create a GameBoard from a 2D array
  private GameBoard createBoard(Player[][] board) {
    return new GameBoard(board);
  }
}