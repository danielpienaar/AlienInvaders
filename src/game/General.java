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
public class General extends Player
{
    
    
    
    public General(String n, int s, int p, int t)
    {
        super(n, s, p, t);
    }
    
    public int addReinforcements(int USA, int SA, int EU, int AF, int AI, int AU)
    {
        int reinforcements = 0;
        reinforcements += (EU * 2);
        reinforcements += (USA * 2);
        reinforcements += (AF * 2);
        reinforcements += (AU * 1);
        reinforcements += (AI * 3);
        reinforcements += (SA * 2);
        return reinforcements;
    }
}
