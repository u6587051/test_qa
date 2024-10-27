/* Copyright (C) 2024 Jittakan Damrongtrakoonwat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MIT license.
 */

package ttsu.game.tictactoe;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ttsu.game.tictactoe.TicTacToeGameState.Player;

public class testToString_GameBoard {

  @Test
  public void testToString_FullPattern1() {
    GameBoard board = createBoard(new Player[][] {
            { Player.X, Player.O, Player.X },
            { Player.O, Player.X, Player.O },
            { Player.X, Player.O, Player.X }
    });
    String expected = "XOX\nOXO\nXOX\n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_MixedPattern2() {
    GameBoard board = createBoard(new Player[][] {
            { Player.X, null, Player.X },
            { null, Player.O, null },
            { Player.X, null, Player.O }
    });
    String expected = "X X\n O \nX O\n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_AlternatingPattern3() {
    GameBoard board = createBoard(new Player[][] {
            { Player.O, Player.X, Player.O },
            { Player.X, Player.O, Player.X },
            { Player.O, Player.X, Player.O }
    });
    String expected = "OXO\nXOX\nOXO\n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_EmptyMixedPattern4() {
    GameBoard board = createBoard(new Player[][] {
            { Player.X, Player.O, null },
            { Player.O, Player.X, null },
            { null, Player.O, Player.X }
    });
    String expected = "XO \nOX \n OX\n";
    assertEquals(expected, board.toString());
  }

  @Test
  public void testToString_EmptyBoardPattern5() {
    GameBoard board = createBoard(new Player[][] {
            { null, null, null },
            { null, Player.X, null },
            { null, null, null }
    });
    String expected = "   \n X \n   \n";
    assertEquals(expected, board.toString());
  }

  // Helper method to create a GameBoard from a 2D array
  private GameBoard createBoard(Player[][] board) {
    return new GameBoard(board);
  }
}