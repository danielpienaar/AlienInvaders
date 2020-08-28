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
public class Alien extends Player
{

    public Alien(String n, int s, int p, int t)
    {
        super(n, s, p, t);
    }

    public int calculateBackup(int roundsWithout)
    {
        int backup = 0;
        if (roundsWithout <= 10)
        {
            backup = (int) (Math.random() * ((10 * roundsWithout) - (2 * roundsWithout) + 1) + (2 * roundsWithout));
        } else if (roundsWithout > 10)
        {
            backup = (int) (Math.random() * ((10 * 10) - (2 * 10) + 1) + (2 * 10));
        }
        return backup;
    }
}
