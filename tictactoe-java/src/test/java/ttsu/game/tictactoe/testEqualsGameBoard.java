/* Copyright (C) 2024 Jittakan Damrongtrakoonwat - All Rights Reserved
* You may use, distribute and modify this code under the terms of the MIT license.
*/

package ttsu.game.tictactoe;

import org.junit.Test;
import ttsu.game.tictactoe.TicTacToeGameState.Player;

import static org.junit.Assert.*;

public class testEqualsGameBoard {

  @Test
  public void testEquals_reflexive() {
    GameBoard board = new GameBoard();
    assertEquals(board.equals(board), true);
  }

  @Test
  public void testEquals_symmetric() {
    GameBoard board1 = new GameBoard();
    GameBoard board2 = new GameBoard();
    assertEquals(board1.equals(board2), board2.equals(board1));
  }

  @Test
  public void testEquals_transitive() {
    GameBoard board1 = new GameBoard();
    GameBoard board2 = new GameBoard();
    GameBoard board3 = new GameBoard();
    assertEquals(board1.equals(board2), true);
    assertEquals(board2.equals(board3), true);
    assertEquals(board1.equals(board1), true);
  }

  @Test
  public void testEquals_consistent() {
    GameBoard board1 = new GameBoard();
    GameBoard board2 = new GameBoard();
    assertEquals(board1.equals(board2), board1.equals(board2));
  }

  @Test
  public void testEquals_nonNull() {
    GameBoard board = new GameBoard();
    assertEquals(board.equals(null), false);
  }

  @Test
  public void testEquals_emptyBoards() {
    GameBoard board1 = new GameBoard();
    GameBoard board2 = new GameBoard();
    assertEquals(board1.equals(board2), true);
  }

  @Test
  public void testEquals_identicalMarks() {
    GameBoard board1 = createBoard(new Player[][] {
            {Player.X, Player.O, Player.X},
            {Player.O, Player.X, Player.O},
            {Player.X, Player.O, Player.X}});
    GameBoard board2 = createBoard(new Player[][] {
            {Player.X, Player.O, Player.X},
            {Player.O, Player.X, Player.O},
            {Player.X, Player.O, Player.X}});
    assertEquals(board1.equals(board2), true);
  }

  @Test
  public void testEquals_differentMarks() {
    GameBoard board1 = createBoard(new Player[][] {
            {Player.X, Player.O, Player.X},
            {Player.O, Player.X, Player.O},
            {Player.X, Player.O, Player.X}});
    GameBoard board2 = createBoard(new Player[][] {
            {Player.X, Player.O, Player.O},
            {Player.O, Player.X, Player.X},
            {Player.O, Player.X, Player.O}});
    assertEquals(board1.equals(board2), false);
  }

  @Test
  public void testEquals_partiallyFilled() {
    GameBoard board1 = createBoard(new Player[][] {
            {Player.X, null, Player.X},
            {null, Player.X, null},
            {Player.X, null, Player.X}});
    GameBoard board2 = createBoard(new Player[][] {
            {Player.X, null, Player.X},
            {null, Player.X, null},
            {Player.X, null, Player.X}});
    assertEquals(board1.equals(board2), true);
  }

  private GameBoard createBoard(Player[][] board) {
    return new GameBoard(board);
  }
}