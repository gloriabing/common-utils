package org.gloria.algorithm.leetcode;

import org.junit.Test;

/**
 * Create on 2017/1/10 11:15.
 *
 * @author : gloria.
 * 
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 * Example:
 *
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 *
 * Answer: 16
 * @see : https://leetcode.com/problems/island-perimeter/
 * 
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {

                if (grid[row][column] == 1) {
                    if (row - 1 < 0 || grid[row - 1][column] == 0) {
                        result++;
                    }
                    if (row + 1 >= grid.length || grid[row + 1][column] == 0) {
                        result++;
                    }
                    if (column - 1 < 0 || grid[row][column - 1] == 0) {
                        result++;
                    }
                    if (column + 1 >= grid[row].length || grid[row][column + 1] == 0) {
                        result++;
                    }
                }
                
            }
        }
        return result;
    }
    
    @Test
    public void test() {
        int[][] grid = new int[][]{
                {0, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 0, 0},
                {1, 1, 0, 0}
        };
        System.out.println(islandPerimeter(grid));
    }
    
}
