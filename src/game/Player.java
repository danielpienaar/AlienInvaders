/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Daniel
 */
public class Player
{
    
    protected String name = "";
    protected int highscore = 0;
    protected int gamesPlayed = 0;
    protected int availableTroops = 0;
    
    public Player(String n, int s, int p, int t)
    {
        name = n;
        highscore = s;
        gamesPlayed = p;
        availableTroops = t;
    }
    
    public int getAvailableTroops()
    {
        return availableTroops;
    }
    
    public void setAvailableTroops(int at)
    {
        availableTroops = at;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getHighScore()
    {
        return highscore;
    }
    
    public void setHighScore(int set)
    {
        highscore = set;
    }
    
    public int getGamesPlayed()
    {
        return gamesPlayed;
    }
    
    public void setGamesPlayed(int p)
    {
        gamesPlayed = p;
    }
    
}
