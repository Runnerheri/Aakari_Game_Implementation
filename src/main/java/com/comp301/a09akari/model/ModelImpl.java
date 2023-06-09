package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private PuzzleLibrary library;
  private int activePuzzle = 0;
  private List<ModelObserver> observers;

  private int[][] lampBoard;

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }
    this.library = library;
    lampBoard =
        new int[library.getPuzzle(activePuzzle).getHeight()]
            [library.getPuzzle(activePuzzle).getWidth()];
    observers = new ArrayList<>();
  }

  public void addLamp(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzle).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampBoard[r][c] = 1;
    notifyObservers();
  }

  public void removeLamp(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzle).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    lampBoard[r][c] = 0;
    notifyObservers();
  }

  public boolean isLit(int r, int c) {
    if (r < 0
        || r >= library.getPuzzle(activePuzzle).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (library.getPuzzle(activePuzzle).getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    Puzzle currentpuzzle = library.getPuzzle(activePuzzle);
    if (lampBoard[r][c] == 1) {
      return true;
    }

    boolean rowtoright = true;
    boolean rowtoleft = true;
    boolean columnbelow = true;
    boolean columnabove = true;
    // loop through same row in either direction
    for (int i = c; i < lampBoard[r].length; i++) {
      if (lampBoard[r][i] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(r, i) == CellType.CLUE
          || currentpuzzle.getCellType(r, i) == CellType.WALL) {
        rowtoright = false;
        break;
      }
    }
    for (int i = c; i >= 0; i--) {
      if (lampBoard[r][i] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(r, i) == CellType.CLUE
          || currentpuzzle.getCellType(r, i) == CellType.WALL) {
        rowtoleft = false;
        break;
      }
    }
    // loop through the same column in either direction
    for (int j = r; j < lampBoard.length; j++) {
      if (lampBoard[j][c] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(j, c) == CellType.CLUE
          || currentpuzzle.getCellType(j, c) == CellType.WALL) {
        columnabove = false;
        break;
      }
    }
    for (int j = r; j >= 0; j--) {
      if (lampBoard[j][c] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(j, c) == CellType.CLUE
          || currentpuzzle.getCellType(j, c) == CellType.WALL) {
        columnbelow = false;
        break;
      }
    }
    if (columnabove == false && columnbelow == false && rowtoright == false && rowtoleft == false) {
      return false;
    }
    return false;
  }

  public boolean isLamp(int r, int c) {
    Puzzle currentPuzzle = library.getPuzzle(activePuzzle);
    if (r < 0
        || r >= library.getPuzzle(activePuzzle).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }
    if (lampBoard[r][c] == 1) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isLampIllegal(int r, int c) {
    // 4 for loops
    // break when needed
    Puzzle currentpuzzle = library.getPuzzle(activePuzzle);
    if (r < 0
        || r >= library.getPuzzle(activePuzzle).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (lampBoard[r][c] != 1 || currentpuzzle.getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    }

    boolean rowtoright = false;
    boolean rowtoleft = false;
    boolean columnabove = false;
    boolean columnbelow = false;
    // Check to the right
    for (int i = c + 1; i < lampBoard[r].length; i++) {
      if (lampBoard[r][i] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(r, i) == CellType.CLUE
          || currentpuzzle.getCellType(r, i) == CellType.WALL) {
        rowtoright = true;
        break;
      }
    }
    for (int i = c - 1; i >= 0; i--) {
      if (lampBoard[r][i] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(r, i) == CellType.CLUE
          || currentpuzzle.getCellType(r, i) == CellType.WALL) {
        rowtoleft = true;
        break;
      }
    }
    for (int j = r + 1; j < lampBoard.length; j++) {
      if (lampBoard[j][c] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(j, c) == CellType.CLUE
          || currentpuzzle.getCellType(j, c) == CellType.WALL) {
        columnabove = true;
        break;
      }
    }
    for (int j = r - 1; j >= 0; j--) {
      if (lampBoard[j][c] == 1) {
        return true;
      }
      if (currentpuzzle.getCellType(j, c) == CellType.CLUE
          || currentpuzzle.getCellType(j, c) == CellType.WALL) {
        columnbelow = true;
        break;
      }
    }
    if (rowtoright == true && rowtoleft == true && columnabove == true && columnbelow == true) {
      return false;
    }
    return false;
  }

  public Puzzle getActivePuzzle() {
    return library.getPuzzle(activePuzzle);
  }

  public int getActivePuzzleIndex() {
    return activePuzzle;
  }

  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= library.size()) {
      throw new IndexOutOfBoundsException();
    }
    activePuzzle = index;
    lampBoard = new int[library.getPuzzle(index).getHeight()][library.getPuzzle(index).getWidth()];
    notifyObservers();
  }

  public int getPuzzleLibrarySize() {
    return library.size();
  }

  public void resetPuzzle() {
    Puzzle activePuzzle = library.getPuzzle(getActivePuzzleIndex());
    int width = activePuzzle.getWidth();
    int height = activePuzzle.getHeight();
    lampBoard = new int[height][width];
    notifyObservers();
  }

  public boolean isSolved() {
    Puzzle currentpuzzle = library.getPuzzle(activePuzzle);
    // double for loop to loop through each cell
    // check if cell type is clue
    // check if clue is satisfied, it not return false
    // check if cell is corridor
    // check if it is not lit OR it is an illegal lamp, return false
    // return true at end as default

    for (int i = 0; i < currentpuzzle.getHeight(); i++) {
      for (int j = 0; j < currentpuzzle.getWidth(); j++) {
        if (currentpuzzle.getCellType(i, j) == CellType.CLUE) {
          if (isClueSatisfied(i, j) == false) {
            System.out.print("clue not satisfied" + i + j);
            return false;
          }
        }
        if (currentpuzzle.getCellType(i, j) == CellType.CORRIDOR) {
          if (isLit(i, j) == false) {
            System.out.print("Cell not lit at index" + i + j);
            return false;
          }

          if (isLamp(i, j)) {
            if (isLampIllegal(i, j)) {
              System.out.print("illegal lamp" + i + j);
              return false;
            }
          }
        }
      }
    }
    return true;
  }

  public boolean isClueSatisfied(int r, int c) {
    Puzzle currentPuzzle = library.getPuzzle(activePuzzle);
    if (r < 0
        || r >= library.getPuzzle(activePuzzle).getHeight()
        || c < 0
        || c >= library.getPuzzle(activePuzzle).getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (currentPuzzle.getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int count = 0;
    if (r + 1 >= currentPuzzle.getHeight()) {
      count += 0;
    } else if (lampBoard[r + 1][c] == 1) {
      count += 1;
    }

    if (r - 1 < 0) {
      count += 0;
    } else if (lampBoard[r - 1][c] == 1) {
      count += 1;
    }

    if (c + 1 >= currentPuzzle.getWidth()) {
      count += 0;
    } else if (lampBoard[r][c + 1] == 1) {
      count += 1;
    }
    if (c - 1 < 0) {
      count += 0;
    } else if (lampBoard[r][c - 1] == 1) {
      count += 1;
    }
    int clue = currentPuzzle.getClue(r, c);

    if (clue == count) {
      return true;
    } else {
      return false;
    }
  }

  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }
}
