/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Pienaar
 */
public class AlienInvadersGUI extends javax.swing.JFrame
{

    Connect connect = new Connect();
    String userGeneral = "";
    String userAlien = "";
    String turn = "";
    int actions = 0;
    int totalRounds = 0;
    int roundsWithout = 0;
    boolean start = true;
    boolean moving = false;
    int totalGeneral = 0;
    int totalAlien = 0;
    int maxGeneral = 0;
    int maxAlien = 0;
    JButton[] alienTerritories = new JButton[34];
    JButton[] generalTerritories = new JButton[34];
    JButton[] unoccupied = new JButton[34];
    JButton movingButton = new JButton();
    General g = new General("", 0, 0, 0);
    Alien a = new Alien("", 0, 0, 0);

    public AlienInvadersGUI()
    {
        //setup program
        initComponents();
        btnPlay.setOpaque(false);
        btnPlay.setContentAreaFilled(false);
        btnPlay.setBorderPainted(false);
        btnHighScores.setOpaque(false);
        btnHighScores.setContentAreaFilled(false);
        btnHighScores.setBorderPainted(false);
        btnHelp.setOpaque(false);
        btnHelp.setContentAreaFilled(false);
        btnHelp.setBorderPainted(false);
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        btnAccountEditor.setOpaque(false);
        btnAccountEditor.setContentAreaFilled(false);
        btnAccountEditor.setBorderPainted(false);
        btnDoneAlien.setOpaque(false);
        btnDoneAlien.setContentAreaFilled(false);
        btnDoneAlien.setBorderPainted(false);
        btnDoneGeneral.setOpaque(false);
        btnDoneGeneral.setContentAreaFilled(false);
        btnDoneGeneral.setBorderPainted(false);

        unoccupied[0] = btnSA1;
        unoccupied[0].setForeground(Color.black);
        unoccupied[1] = btnSA2;
        unoccupied[1].setForeground(Color.black);
        unoccupied[2] = btnSA3;
        unoccupied[2].setForeground(Color.black);
        unoccupied[3] = btnSA4;
        unoccupied[3].setForeground(Color.black);
        unoccupied[4] = btnUSA1;
        unoccupied[4].setForeground(Color.black);
        unoccupied[5] = btnUSA2;
        unoccupied[5].setForeground(Color.black);
        unoccupied[6] = btnUSA3;
        unoccupied[6].setForeground(Color.black);
        unoccupied[7] = btnUSA4;
        unoccupied[7].setForeground(Color.black);
        unoccupied[8] = btnUSA5;
        unoccupied[8].setForeground(Color.black);
        unoccupied[9] = btnUSA6;
        unoccupied[9].setForeground(Color.black);
        unoccupied[10] = btnUSA7;
        unoccupied[10].setForeground(Color.black);
        unoccupied[11] = btnUSA8;
        unoccupied[11].setForeground(Color.black);
        unoccupied[12] = btnEU1;
        unoccupied[12].setForeground(Color.black);
        unoccupied[13] = btnEU2;
        unoccupied[13].setForeground(Color.black);
        unoccupied[14] = btnEU3;
        unoccupied[14].setForeground(Color.black);
        unoccupied[15] = btnAF1;
        unoccupied[15].setForeground(Color.black);
        unoccupied[16] = btnAF2;
        unoccupied[16].setForeground(Color.black);
        unoccupied[17] = btnAF3;
        unoccupied[17].setForeground(Color.black);
        unoccupied[18] = btnAF4;
        unoccupied[18].setForeground(Color.black);
        unoccupied[19] = btnAF5;
        unoccupied[19].setForeground(Color.black);
        unoccupied[20] = btnAU1;
        unoccupied[20].setForeground(Color.black);
        unoccupied[21] = btnAU2;
        unoccupied[21].setForeground(Color.black);
        unoccupied[22] = btnAU3;
        unoccupied[22].setForeground(Color.black);
        unoccupied[23] = btnAI1;
        unoccupied[23].setForeground(Color.black);
        unoccupied[24] = btnAI2;
        unoccupied[24].setForeground(Color.black);
        unoccupied[25] = btnAI3;
        unoccupied[25].setForeground(Color.black);
        unoccupied[26] = btnAI4;
        unoccupied[26].setForeground(Color.black);
        unoccupied[27] = btnAI5;
        unoccupied[27].setForeground(Color.black);
        unoccupied[28] = btnAI6;
        unoccupied[28].setForeground(Color.black);
        unoccupied[29] = btnAI7;
        unoccupied[29].setForeground(Color.black);
        unoccupied[30] = btnAI8;
        unoccupied[30].setForeground(Color.black);
        unoccupied[31] = btnAI9;
        unoccupied[31].setForeground(Color.black);
        unoccupied[32] = btnAI10;
        unoccupied[32].setForeground(Color.black);
        unoccupied[33] = btnAI11;
        unoccupied[33].setForeground(Color.black);

        frmGeneralLogin.getRootPane().setDefaultButton(btnLoginGeneral);
        frmAlienLogin.getRootPane().setDefaultButton(btnLoginAlien);
        frmEndAlien.getRootPane().setDefaultButton(btnDoneAlien);
        frmEndGeneral.getRootPane().setDefaultButton(btnDoneGeneral);
        frmRegister.getRootPane().setDefaultButton(btnRegister2);

        cmbManageUser.removeAllItems();
        String sql = "SELECT Username FROM Profiles;";
        ResultSet rs = connect.query(sql);
        try
        {
            while (rs.next())
            {
                String n = rs.getString("Username");
                cmbManageUser.addItem(n);
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }

        btnReinforcements.setEnabled(false);
        printUnoccupied();
    }

    private void printUnoccupied()
    {
        //for testing purposes
        System.out.println("Unoccupied:");
        System.out.println("-----------");
        for (int i = 0; i < unoccupied.length; i++)
        {
            if (unoccupied[i] != null)
            {
                System.out.println(unoccupied[i].getName());
            }
        }
        System.out.println("");
    }

    private void printGeneral()
    {
        //for testing purposes
        System.out.println("General's territories:");
        System.out.println("-----------");
        for (int i = 0; i < generalTerritories.length; i++)
        {
            if (generalTerritories[i] != null)
            {
                System.out.println(generalTerritories[i].getName());
            }
        }
        System.out.println("");
    }

    private void printAlien()
    {
        //for testing purposes
        System.out.println("Alien's territories:");
        System.out.println("-----------");
        for (int i = 0; i < alienTerritories.length; i++)
        {
            if (alienTerritories[i] != null)
            {
                System.out.println(alienTerritories[i].getName());
            }
        }
        System.out.println("");
    }

    private void refreshAccountEditor()
    {
        try
        {
            //display list of profiles and highscores
            String sql = "";
            sql = "SELECT Username, HSGeneral, HSAlien, GamesPlayed FROM Profiles ORDER BY Username";
            ResultSet rs = connect.query(sql);
            String out = "Username" + "\t\t" + "Highscore as General" + "\t\t" + "Highscore as Alien" + "\t\t" + "Games Played" + "\n\n";
            while (rs.next())
            {
                String name = rs.getString("Username");
                String scoreGeneral = rs.getString("HSGeneral");
                String scoreAlien = rs.getString("HSAlien");
                String gamesPlayed = rs.getString("GamesPlayed");
                String space = "";
                if (name.length() >= 8)
                {
                    space = "\t\t\t";
                } else if (name.length() > 4)
                {
                    space = "\t\t\t\t";
                } else if (name.length() <= 4)
                {
                    space = "\t\t\t\t";
                }

                out += name + space
                        + scoreGeneral + "\t\t\t\t"
                        + scoreAlien + "\t\t\t\t"
                        + gamesPlayed
                        + "\n";
            }
            txaAccounts.setText(out);
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        //refresh combo box
        cmbManageUser.removeAllItems();
        String sql = "SELECT Username FROM Profiles;";
        ResultSet rs = connect.query(sql);
        try
        {
            while (rs.next())
            {
                String n = rs.getString("Username");
                cmbManageUser.addItem(n);
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
    }

    private void refreshHighscores()
    {
        String space = "";
        //Display general scores
        String sql = "SELECT Username, HSGeneral FROM Profiles ORDER BY HSGeneral DESC, Username;";
        ResultSet rs = connect.query(sql);
        String out = "Username\t\tHighscore\n\n";
        int rank = 1;
        try
        {
            while (rs.next())
            {
                String name = rs.getString("Username");
                String score = rs.getString("HSGeneral");
                if (name.length() == 15)
                {
                    space = "\t";
                } else if (name.length() >= 8)
                {
                    space = "\t\t";
                } else if (name.length() > 4)
                {
                    space = "\t\t";
                } else if (name.length() <= 4)
                {
                    space = "\t\t\t";
                }

                out += rank + ". " + name + space + score + "\n";
                rank++;
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        txaHighscoresGeneral.setText(out);
        //Display alien scores
        sql = "SELECT Username, HSAlien FROM Profiles ORDER BY HSAlien DESC, Username;";
        rs = connect.query(sql);
        out = "Username\t\tHighscore\n\n";
        rank = 1;
        try
        {
            while (rs.next())
            {
                String name = rs.getString("Username");
                String score = rs.getString("HSAlien");
                if (name.length() == 15)
                {
                    space = "\t";
                } else if (name.length() >= 8)
                {
                    space = "\t\t";
                } else if (name.length() > 4)
                {
                    space = "\t\t";
                } else if (name.length() <= 4)
                {
                    space = "\t\t\t";
                }

                out += rank + ". " + name + space + score + "\n";
                rank++;
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        txaHighscoresAlien.setText(out);
    }

    int usa = 0;
    int sa = 0;
    int eu = 0;
    int af = 0;
    int au = 0;
    int ai = 0;

    private void calculateReinforcements()
    {
        //calculates amount of territories for general in different continents for use in calculating general reinforcements
        usa = 0;
        sa = 0;
        eu = 0;
        af = 0;
        au = 0;
        ai = 0;
        for (int i = 0; i < generalTerritories.length; i++)
        {
            if (generalTerritories[i] != null)
            {
                if (generalTerritories[i].getName().substring(0, 6).equals("btnUSA"))
                {
                    usa++;
                } else if (generalTerritories[i].getName().substring(0, 5).equals("btnSA"))
                {
                    sa++;
                } else if (generalTerritories[i].getName().substring(0, 5).equals("btnEU"))
                {
                    eu++;
                } else if (generalTerritories[i].getName().substring(0, 5).equals("btnAF"))
                {
                    af++;
                } else if (generalTerritories[i].getName().substring(0, 5).equals("btnAU"))
                {
                    au++;
                } else if (generalTerritories[i].getName().substring(0, 5).equals("btnAI"))
                {
                    ai++;
                }
            }
        }
    }

    private void alienTurn(Alien a)
    {
        //sets up game for alien's turn
        if (totalRounds < 100)
        {
            totalRounds++;
        }
        if (totalAlien > maxAlien)
        {
            maxAlien = totalAlien;
        }
        JOptionPane.showMessageDialog(this, a.getName() + "'s turn.");
        lblTurn.setText(a.getName() + "'s Turn");
        turn = a.getName();
        int prev = a.getAvailableTroops();
        int p = a.calculateBackup(roundsWithout);
        a.setAvailableTroops(prev + p);
        lblTroops.setText("Reinforcements: " + a.getAvailableTroops());
        btnReinforcements.setEnabled(false);
        for (int i = 0; i < alienTerritories.length; i++)
        {
            if (alienTerritories[i] != null)
            {
                alienTerritories[i].setEnabled(true);
                if (alienTerritories[i].getText().equals("0"))
                {
                    alienTerritories[i].setEnabled(false);
                }
            }
        }
        for (int i = 0; i < unoccupied.length; i++)
        {
            if (unoccupied[i] != null)
            {
                unoccupied[i].setEnabled(false);
            }
        }
        for (int i = 0; i < generalTerritories.length; i++)
        {
            if (generalTerritories[i] != null)
            {
                generalTerritories[i].setEnabled(false);
            }
        }
        actions = 3;
        lblActions.setText("Actions left: " + actions);
    }

    private void generalTurn(General g)
    {
        //sets up game for general's turn
        if (totalRounds < 100)
        {
            totalRounds++;
        }
        if (totalGeneral > maxGeneral)
        {
            maxGeneral = totalGeneral;
        }
        roundsWithout++;
        JOptionPane.showMessageDialog(this, g.getName() + "'s turn.");
        lblTurn.setText(g.getName() + "'s Turn");
        turn = g.getName();
        calculateReinforcements();
        int p = g.getAvailableTroops();
        p += g.addReinforcements(usa, sa, eu, af, ai, au);
        g.setAvailableTroops(p);
        lblTroops.setText("Reinforcements: " + g.getAvailableTroops());
        btnReinforcements.setEnabled(false);
        for (int i = 0; i < generalTerritories.length; i++)
        {
            if (generalTerritories[i] != null)
            {
                generalTerritories[i].setEnabled(true);
                if (generalTerritories[i].getText().equals("0"))
                {
                    generalTerritories[i].setEnabled(false);
                }
            }
        }
        for (int i = 0; i < unoccupied.length; i++)
        {
            if (unoccupied[i] != null)
            {
                unoccupied[i].setEnabled(false);
            }
        }
        for (int i = 0; i < alienTerritories.length; i++)
        {
            if (alienTerritories[i] != null)
            {
                alienTerritories[i].setEnabled(false);
            }
        }
        actions = 3;
        lblActions.setText("Actions left: " + actions);
    }

    private void generalMove(JButton b)
    {
        //calculates movement options when general clicks on one of their territories
        if (totalGeneral > maxGeneral)
        {
            maxGeneral = totalGeneral;
        }
        switch (b.getName())
        {
            case "btnUSA1":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA2.setEnabled(true);
                btnUSA3.setEnabled(true);
                btnAI10.setEnabled(true);
                break;
            case "btnUSA2":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA1.setEnabled(true);
                btnUSA3.setEnabled(true);
                btnUSA6.setEnabled(true);
                btnUSA7.setEnabled(true);
                break;
            case "btnUSA3":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA3.setEnabled(false);
                btnUSA1.setEnabled(true);
                btnUSA2.setEnabled(true);
                btnUSA4.setEnabled(true);
                btnUSA7.setEnabled(true);
                break;
            case "btnUSA4":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA3.setEnabled(true);
                btnUSA5.setEnabled(true);
                btnUSA7.setEnabled(true);
                btnUSA8.setEnabled(true);
                break;
            case "btnUSA5":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA4.setEnabled(true);
                btnUSA8.setEnabled(true);
                btnSA1.setEnabled(true);
                break;
            case "btnUSA6":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA2.setEnabled(true);
                btnUSA7.setEnabled(true);
                btnEU1.setEnabled(true);
                btnEU3.setEnabled(true);
                break;
            case "btnUSA7":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA2.setEnabled(true);
                btnUSA3.setEnabled(true);
                btnUSA4.setEnabled(true);
                btnUSA6.setEnabled(true);
                btnUSA8.setEnabled(true);
                btnEU3.setEnabled(true);
                break;
            case "btnUSA8":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA4.setEnabled(true);
                btnUSA5.setEnabled(true);
                btnUSA7.setEnabled(true);
                break;
            case "btnSA1":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA5.setEnabled(true);
                btnSA2.setEnabled(true);
                btnSA3.setEnabled(true);
                break;
            case "btnSA2":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA1.setEnabled(true);
                btnSA3.setEnabled(true);
                btnSA4.setEnabled(true);
                break;
            case "btnSA3":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA1.setEnabled(true);
                btnSA2.setEnabled(true);
                btnSA4.setEnabled(true);
                btnAF1.setEnabled(true);
                break;
            case "btnSA4":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA2.setEnabled(true);
                btnSA3.setEnabled(true);
                break;
            case "btnEU1":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA6.setEnabled(true);
                btnEU2.setEnabled(true);
                break;
            case "btnEU2":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU1.setEnabled(true);
                btnEU3.setEnabled(true);
                btnAI1.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI3.setEnabled(true);
                break;
            case "btnEU3":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA6.setEnabled(true);
                btnUSA7.setEnabled(true);
                btnEU2.setEnabled(true);
                btnAI3.setEnabled(true);
                btnAF1.setEnabled(true);
                break;
            case "btnAF1":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA3.setEnabled(true);
                btnEU3.setEnabled(true);
                btnAF2.setEnabled(true);
                btnAF3.setEnabled(true);
                btnAF4.setEnabled(true);
                break;
            case "btnAF2":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF1.setEnabled(true);
                btnAF3.setEnabled(true);
                btnAI3.setEnabled(true);
                break;
            case "btnAF3":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF1.setEnabled(true);
                btnAF2.setEnabled(true);
                btnAF4.setEnabled(true);
                btnAF5.setEnabled(true);
                break;
            case "btnAF4":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF1.setEnabled(true);
                btnAF3.setEnabled(true);
                btnAF5.setEnabled(true);
                break;
            case "btnAF5":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF3.setEnabled(true);
                btnAF4.setEnabled(true);
                break;
            case "btnAU1":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI8.setEnabled(true);
                btnAI9.setEnabled(true);
                btnAU2.setEnabled(true);
                btnAU3.setEnabled(true);
                break;
            case "btnAU2":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAU1.setEnabled(true);
                btnAU3.setEnabled(true);
                break;
            case "btnAU3":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAU1.setEnabled(true);
                btnAU2.setEnabled(true);
                break;
            case "btnAI1":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU2.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI4.setEnabled(true);
                btnAI8.setEnabled(true);
                break;
            case "btnAI2":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU2.setEnabled(true);
                btnAI1.setEnabled(true);
                btnAI3.setEnabled(true);
                btnAI5.setEnabled(true);
                btnAI8.setEnabled(true);
                break;
            case "btnAI3":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU2.setEnabled(true);
                btnEU3.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI5.setEnabled(true);
                btnAF2.setEnabled(true);
                break;
            case "btnAI4":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI1.setEnabled(true);
                btnAI6.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAI11.setEnabled(true);
                break;
            case "btnAI5":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI2.setEnabled(true);
                btnAI3.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAI9.setEnabled(true);
                break;
            case "btnAI6":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI4.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI10.setEnabled(true);
                break;
            case "btnAI7":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI4.setEnabled(true);
                btnAI6.setEnabled(true);
                btnAI10.setEnabled(true);
                btnAI11.setEnabled(true);
                break;
            case "btnAI8":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAU1.setEnabled(true);
                btnAI1.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI4.setEnabled(true);
                btnAI5.setEnabled(true);
                btnAI9.setEnabled(true);
                btnAI11.setEnabled(true);
                break;
            case "btnAI9":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI5.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAU1.setEnabled(true);
                break;
            case "btnAI10":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI6.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI11.setEnabled(true);
                btnUSA1.setEnabled(true);
                break;
            case "btnAI11":
                moving = true;
                if (g.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI4.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAI10.setEnabled(true);
                break;
        }
        for (int i = 0; i < generalTerritories.length; i++)
        {
            if (generalTerritories[i] != null)
            {
                generalTerritories[i].setEnabled(true);
            }
        }
    }

    private void alienMove(JButton b)
    {
        //calculates movement options when alien clicks on one of their territories
        if (totalAlien > maxAlien)
        {
            maxAlien = totalAlien;
        }
        switch (b.getName())
        {
            case "btnUSA1":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA2.setEnabled(true);
                btnUSA3.setEnabled(true);
                btnAI10.setEnabled(true);
                break;
            case "btnUSA2":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA1.setEnabled(true);
                btnUSA3.setEnabled(true);
                btnUSA6.setEnabled(true);
                btnUSA7.setEnabled(true);
                break;
            case "btnUSA3":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA3.setEnabled(false);
                btnUSA1.setEnabled(true);
                btnUSA2.setEnabled(true);
                btnUSA4.setEnabled(true);
                btnUSA7.setEnabled(true);
                break;
            case "btnUSA4":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA3.setEnabled(true);
                btnUSA5.setEnabled(true);
                btnUSA7.setEnabled(true);
                btnUSA8.setEnabled(true);
                break;
            case "btnUSA5":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA4.setEnabled(true);
                btnUSA8.setEnabled(true);
                btnSA1.setEnabled(true);
                break;
            case "btnUSA6":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA2.setEnabled(true);
                btnUSA7.setEnabled(true);
                btnEU1.setEnabled(true);
                btnEU3.setEnabled(true);
                break;
            case "btnUSA7":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA2.setEnabled(true);
                btnUSA3.setEnabled(true);
                btnUSA4.setEnabled(true);
                btnUSA6.setEnabled(true);
                btnUSA8.setEnabled(true);
                btnEU3.setEnabled(true);
                break;
            case "btnUSA8":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA4.setEnabled(true);
                btnUSA5.setEnabled(true);
                btnUSA7.setEnabled(true);
                break;
            case "btnSA1":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA5.setEnabled(true);
                btnSA2.setEnabled(true);
                btnSA3.setEnabled(true);
                break;
            case "btnSA2":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA1.setEnabled(true);
                btnSA3.setEnabled(true);
                btnSA4.setEnabled(true);
                break;
            case "btnSA3":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA1.setEnabled(true);
                btnSA2.setEnabled(true);
                btnSA4.setEnabled(true);
                btnAF1.setEnabled(true);
                break;
            case "btnSA4":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA2.setEnabled(true);
                btnSA3.setEnabled(true);
                break;
            case "btnEU1":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA6.setEnabled(true);
                btnEU2.setEnabled(true);
                break;
            case "btnEU2":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU1.setEnabled(true);
                btnEU3.setEnabled(true);
                btnAI1.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI3.setEnabled(true);
                break;
            case "btnEU3":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnUSA6.setEnabled(true);
                btnUSA7.setEnabled(true);
                btnEU2.setEnabled(true);
                btnAI3.setEnabled(true);
                btnAF1.setEnabled(true);
                break;
            case "btnAF1":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnSA3.setEnabled(true);
                btnEU3.setEnabled(true);
                btnAF2.setEnabled(true);
                btnAF3.setEnabled(true);
                btnAF4.setEnabled(true);
                break;
            case "btnAF2":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF1.setEnabled(true);
                btnAF3.setEnabled(true);
                btnAI3.setEnabled(true);
                break;
            case "btnAF3":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF1.setEnabled(true);
                btnAF2.setEnabled(true);
                btnAF4.setEnabled(true);
                btnAF5.setEnabled(true);
                break;
            case "btnAF4":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF1.setEnabled(true);
                btnAF3.setEnabled(true);
                btnAF5.setEnabled(true);
                break;
            case "btnAF5":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAF3.setEnabled(true);
                btnAF4.setEnabled(true);
                break;
            case "btnAU1":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI8.setEnabled(true);
                btnAI9.setEnabled(true);
                btnAU2.setEnabled(true);
                btnAU3.setEnabled(true);
                break;
            case "btnAU2":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAU1.setEnabled(true);
                btnAU3.setEnabled(true);
                break;
            case "btnAU3":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAU1.setEnabled(true);
                btnAU2.setEnabled(true);
                break;
            case "btnAI1":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU2.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI4.setEnabled(true);
                btnAI8.setEnabled(true);
                break;
            case "btnAI2":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU2.setEnabled(true);
                btnAI1.setEnabled(true);
                btnAI3.setEnabled(true);
                btnAI5.setEnabled(true);
                btnAI8.setEnabled(true);
                break;
            case "btnAI3":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnEU2.setEnabled(true);
                btnEU3.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI5.setEnabled(true);
                btnAF2.setEnabled(true);
                break;
            case "btnAI4":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI1.setEnabled(true);
                btnAI6.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAI11.setEnabled(true);
                break;
            case "btnAI5":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI2.setEnabled(true);
                btnAI3.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAI9.setEnabled(true);
                break;
            case "btnAI6":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI4.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI10.setEnabled(true);
                break;
            case "btnAI7":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI4.setEnabled(true);
                btnAI6.setEnabled(true);
                btnAI10.setEnabled(true);
                btnAI11.setEnabled(true);
                break;
            case "btnAI8":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAU1.setEnabled(true);
                btnAI1.setEnabled(true);
                btnAI2.setEnabled(true);
                btnAI4.setEnabled(true);
                btnAI5.setEnabled(true);
                btnAI9.setEnabled(true);
                btnAI11.setEnabled(true);
                break;
            case "btnAI9":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI5.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAU1.setEnabled(true);
                break;
            case "btnAI10":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI6.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI11.setEnabled(true);
                btnUSA1.setEnabled(true);
                break;
            case "btnAI11":
                moving = true;
                if (a.getAvailableTroops() != 0)
                {
                    btnReinforcements.setEnabled(true);
                }
                btnAI4.setEnabled(true);
                btnAI7.setEnabled(true);
                btnAI8.setEnabled(true);
                btnAI10.setEnabled(true);
                break;
        }
        for (int i = 0; i < alienTerritories.length; i++)
        {
            if (alienTerritories[i] != null)
            {
                alienTerritories[i].setEnabled(true);
            }
        }
    }

    private void gameStartGeneral()
    {
        //sets up game for general to deploy troops
        JOptionPane.showMessageDialog(this, g.getName() + ", deploy your troops!");
        g.setAvailableTroops(30);
        lblTroops.setText("Reinforcements: " + g.getAvailableTroops());
        lblTurn.setText(g.getName() + "'s Turn");
        turn = g.getName();
    }

    private void gameStartAlien()
    {
        //sets up game for alien to deploy troops, and ensures that alien cannot click on a general territory
        JOptionPane.showMessageDialog(this, a.getName() + ", deploy your troops!");
        a.setAvailableTroops(50);
        lblTroops.setText("Reinforcements: " + a.getAvailableTroops());
        lblTurn.setText(a.getName() + "'s Turn");
        turn = a.getName();
        for (int i = 0; i < generalTerritories.length; i++)
        {
            if (generalTerritories[i] != null)
            {
                generalTerritories[i].setEnabled(false);
            }
        }
    }

    private void startup(JButton b)
    {
        //startup troop placement for alien and general
        if (start == true && turn.equals(g.getName()))
        {
            int place = 0;
            boolean placeValid = true;
            try
            {
                place = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops do you want to place here?"));
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Enter a number!");
                placeValid = false;
            }
            if (place > g.getAvailableTroops() || place < 0)
            {
                JOptionPane.showMessageDialog(this, "Invalid number of troops.");
            } else if (placeValid == true && place != 0)
            {
                int noTroops = Integer.parseInt(b.getText());
                noTroops += place;
                totalGeneral += place;
                g.setAvailableTroops(g.getAvailableTroops() - place);
                b.setText("" + noTroops);
                b.setForeground(Color.green);
                lblTroops.setText("Reinforcements: " + g.getAvailableTroops());
                for (int i = 0; i < unoccupied.length; i++)
                {
                    if (unoccupied[i] != null && unoccupied[i].getName().equals(b.getName()))
                    {
                        unoccupied[i] = null;
                    }
                }
                boolean placed = false;
                for (int i = 0; i < generalTerritories.length; i++)
                {
                    if (generalTerritories[i] == null && placed == false)
                    {
                        generalTerritories[i] = b;
                        placed = true;
                    }
                }
                if (g.getAvailableTroops() == 0)
                {
                    gameStartAlien();
                }
            }
        } else if (start == true && turn.equals(a.getName()))
        {
            int place = 0;
            boolean placeValid = true;
            try
            {
                place = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops do you want to place here?"));
            } catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(this, "Enter a number!");
                placeValid = false;
            }
            if (place > a.getAvailableTroops() || place < 0)
            {
                JOptionPane.showMessageDialog(this, "Invalid number of troops.");
            } else if (placeValid == true && place != 0)
            {
                int noTroops = Integer.parseInt(b.getText());
                noTroops += place;
                totalAlien += place;
                a.setAvailableTroops(a.getAvailableTroops() - place);
                b.setText("" + noTroops);
                b.setForeground(Color.red);
                lblTroops.setText("Reinforcements: " + a.getAvailableTroops());
                for (int i = 0; i < unoccupied.length; i++)
                {
                    if (unoccupied[i] != null && unoccupied[i].getName().equals(b.getName()))
                    {
                        unoccupied[i] = null;
                    }
                }
                boolean placed = false;
                for (int i = 0; i < alienTerritories.length; i++)
                {
                    if (alienTerritories[i] == null && placed == false)
                    {
                        alienTerritories[i] = b;
                        placed = true;
                    }
                }
            }
            if (a.getAvailableTroops() == 0)
            {
                start = false;
                generalTurn(g);
            }
        }
        if (totalAlien > maxAlien)
        {
            maxAlien = totalAlien;
        }
        if (totalGeneral > maxGeneral)
        {
            maxGeneral = totalGeneral;
        }
    }

    private void moving(JButton n)
    {
        //very complex method that calculates movement options depending on conditions present. also checks to see if game has been won.
        if (movingButton.getName().equals(n.getName()) && turn.equals(g.getName()))
        {
            moving = false;
            movingButton.setBackground(null);
            JOptionPane.showMessageDialog(this, "Move cancelled.");
            for (int i = 0; i < generalTerritories.length; i++)
            {
                if (generalTerritories[i] != null)
                {
                    generalTerritories[i].setEnabled(true);
                    if (generalTerritories[i].getText().equals("0"))
                    {
                        generalTerritories[i].setEnabled(false);
                    }
                }
            }
            for (int i = 0; i < unoccupied.length; i++)
            {
                if (unoccupied[i] != null)
                {
                    unoccupied[i].setEnabled(false);
                }
            }
            for (int i = 0; i < alienTerritories.length; i++)
            {
                if (alienTerritories[i] != null)
                {
                    alienTerritories[i].setEnabled(false);
                }
            }
        } else if (movingButton.getName().equals(n.getName()) && turn.equals(a.getName()))
        {
            moving = false;
            movingButton.setBackground(null);
            JOptionPane.showMessageDialog(this, "Move cancelled.");
            for (int i = 0; i < generalTerritories.length; i++)
            {
                if (generalTerritories[i] != null)
                {
                    generalTerritories[i].setEnabled(false);
                }
            }
            for (int i = 0; i < unoccupied.length; i++)
            {
                if (unoccupied[i] != null)
                {
                    unoccupied[i].setEnabled(false);
                }
            }
            for (int i = 0; i < alienTerritories.length; i++)
            {
                if (alienTerritories[i] != null)
                {
                    alienTerritories[i].setEnabled(true);
                    if (alienTerritories[i].getText().equals("0"))
                    {
                        alienTerritories[i].setEnabled(false);
                    }
                }
            }
        } else if (!movingButton.getName().equals(n.getName()))
        {
            if (n.getForeground() == Color.green)
            {
                if (turn.equals(g.getName()))
                {
                    int move = 0;
                    boolean v = true;
                    try
                    {
                        move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to move?"));
                    } catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(this, "Enter a number!");
                        v = false;
                    }

                    if (v == true)
                    {
                        if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                        {
                            JOptionPane.showMessageDialog(this, "Invalid number.");
                        } else
                        {
                            int prev = Integer.parseInt(n.getText());
                            prev += move;
                            n.setText("" + prev);
                            prev = Integer.parseInt(movingButton.getText());
                            prev = prev - move;
                            movingButton.setText("" + prev);
                            movingButton.setBackground(null);
                            moving = false;
                            if (actions > 0)
                            {
                                for (int i = 0; i < generalTerritories.length; i++)
                                {
                                    if (generalTerritories[i] != null)
                                    {
                                        generalTerritories[i].setEnabled(true);
                                        if (generalTerritories[i].getText().equals("0"))
                                        {
                                            generalTerritories[i].setEnabled(false);
                                        }
                                    }
                                }
                                for (int i = 0; i < unoccupied.length; i++)
                                {
                                    if (unoccupied[i] != null)
                                    {
                                        unoccupied[i].setEnabled(false);
                                    }
                                }
                                for (int i = 0; i < alienTerritories.length; i++)
                                {
                                    if (alienTerritories[i] != null)
                                    {
                                        alienTerritories[i].setEnabled(false);
                                    }
                                }
                            }
                            actions--;
                            lblActions.setText("Actions left: " + actions);
                        }
                    }
                } else if (turn.equals(a.getName()))
                {
                    if (!n.getText().equals("0"))
                    {

                        int move = 0;
                        boolean v = true;
                        try
                        {
                            move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to send into battle?"));
                        } catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(this, "Enter a number!");
                            actions++;
                            v = false;
                        }

                        if (v == true)
                        {
                            if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                            {
                                JOptionPane.showMessageDialog(this, "Invalid number.");
                                actions++;
                            } else
                            {
                                JOptionPane.showMessageDialog(null, "Rolling die...");
                                double roll = (double) (Math.random() * (5.5 - 0.5 + 1) + 0.5);
                                roll = Math.round(roll);
                                int roll1 = (int) roll;
                                double chance = ((roll / 10) + 0.2);
                                chance = chance * 100;
                                chance = Math.round(chance);
                                chance = chance / 100;
                                JOptionPane.showMessageDialog(this, "You rolled a " + roll1 + ". This means that each of your soldiers will have a " + (chance*100) + "% chance of killing an enemy.");
                                boolean success = false;
                                while (success == false)
                                {
                                    double kill = (double) (Math.random() * (1));
                                    if (kill > chance)
                                    {
                                        int prev = Integer.parseInt(movingButton.getText());
                                        prev--;
                                        totalAlien--;
                                        movingButton.setText("" + prev);
                                    } else if (kill <= chance)
                                    {
                                        int prev = Integer.parseInt(n.getText());
                                        prev--;
                                        move--;
                                        totalGeneral--;
                                        n.setText("" + prev);
                                    }
                                    if (move == 0)
                                    {
                                        success = true;
                                        JOptionPane.showMessageDialog(this, "Skirmish complete. Check casualties.");
                                    } else if (n.getText().equals("0"))
                                    {
                                        n.setForeground(Color.red);
                                        int prev = Integer.parseInt(n.getText());
                                        prev++;
                                        n.setText("" + prev);
                                        prev = Integer.parseInt(movingButton.getText());
                                        prev--;
                                        movingButton.setText("" + prev);
                                        success = true;
                                        boolean placed = false;
                                        for (int i = 0; i < alienTerritories.length; i++)
                                        {
                                            if (placed == false && alienTerritories[i] == null)
                                            {
                                                alienTerritories[i] = n;
                                                placed = true;
                                            }
                                        }
                                        for (int i = 0; i < generalTerritories.length; i++)
                                        {
                                            if (generalTerritories[i] != null && generalTerritories[i].getName().equals(n.getName()))
                                            {
                                                generalTerritories[i] = null;
                                            }
                                        }
                                    } else if (movingButton.getText().equals("0"))
                                    {
                                        movingButton.setForeground(Color.green);
                                        int prev = Integer.parseInt(n.getText());
                                        prev--;
                                        n.setText("" + prev);
                                        prev = Integer.parseInt(movingButton.getText());
                                        prev++;
                                        movingButton.setText("" + prev);
                                        success = true;
                                        boolean placed = false;
                                        for (int i = 0; i < generalTerritories.length; i++)
                                        {
                                            if (placed == false && generalTerritories[i] == null)
                                            {
                                                generalTerritories[i] = movingButton;
                                                placed = true;
                                            }
                                        }
                                        for (int i = 0; i < alienTerritories.length; i++)
                                        {
                                            if (alienTerritories[i] != null && alienTerritories[i].getName().equals(movingButton.getName()))
                                            {
                                                alienTerritories[i] = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        moving = false;
                        movingButton.setBackground(null);
                        if (actions > 0)
                        {
                            for (int i = 0; i < generalTerritories.length; i++)
                            {
                                if (generalTerritories[i] != null)
                                {
                                    generalTerritories[i].setEnabled(false);
                                }
                            }
                            for (int i = 0; i < unoccupied.length; i++)
                            {
                                if (unoccupied[i] != null)
                                {
                                    unoccupied[i].setEnabled(false);
                                }
                            }
                            for (int i = 0; i < alienTerritories.length; i++)
                            {
                                if (alienTerritories[i] != null)
                                {
                                    alienTerritories[i].setEnabled(true);
                                    if (alienTerritories[i].getText().equals("0"))
                                    {
                                        alienTerritories[i].setEnabled(false);
                                    }
                                }
                            }
                        }
                        actions--;
                        lblActions.setText("Actions left: " + actions);
                    } else if (n.getText().equals("0"))
                    {
                        int move = 0;
                        boolean v = true;
                        try
                        {
                            move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to move?"));
                        } catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(this, "Enter a number!");
                            v = false;
                        }

                        if (v == true)
                        {
                            if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                            {
                                JOptionPane.showMessageDialog(this, "Invalid number.");
                            } else
                            {
                                int prev = Integer.parseInt(n.getText());
                                prev += move;
                                n.setText("" + prev);
                                prev = Integer.parseInt(movingButton.getText());
                                prev = prev - move;
                                movingButton.setText("" + prev);
                                movingButton.setBackground(null);
                                moving = false;
                                if (actions > 0)
                                {
                                    for (int i = 0; i < generalTerritories.length; i++)
                                    {
                                        if (generalTerritories[i] != null)
                                        {
                                            generalTerritories[i].setEnabled(false);
                                        }
                                    }
                                    for (int i = 0; i < unoccupied.length; i++)
                                    {
                                        if (unoccupied[i] != null)
                                        {
                                            unoccupied[i].setEnabled(false);
                                        }
                                    }
                                    for (int i = 0; i < alienTerritories.length; i++)
                                    {
                                        if (alienTerritories[i] != null)
                                        {
                                            alienTerritories[i].setEnabled(true);
                                            if (alienTerritories[i].getText().equals("0"))
                                            {
                                                alienTerritories[i].setEnabled(false);
                                            }
                                        }
                                    }
                                }
                                actions--;
                                lblActions.setText("Actions left: " + actions);
                            }
                            n.setForeground(Color.red);
                            boolean placed = false;
                            for (int i = 0; i < alienTerritories.length; i++)
                            {
                                if (placed == false && alienTerritories[i] == null)
                                {
                                    alienTerritories[i] = n;
                                    placed = true;
                                }
                            }
                            for (int i = 0; i < generalTerritories.length; i++)
                            {
                                if (generalTerritories[i] != null && generalTerritories[i].getName().equals(n.getName()))
                                {
                                    generalTerritories[i] = null;
                                }
                            }
                        }
                    }
                }
            } else if (n.getForeground() == Color.red)
            {
                if (turn.equals(a.getName()))
                {
                    int move = 0;
                    boolean v = true;
                    try
                    {
                        move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to move?"));
                    } catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(this, "Enter a number!");
                        v = false;
                    }

                    if (v == true)
                    {
                        if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                        {
                            JOptionPane.showMessageDialog(this, "Invalid number.");
                        } else
                        {
                            int prev = Integer.parseInt(n.getText());
                            prev += move;
                            n.setText("" + prev);
                            prev = Integer.parseInt(movingButton.getText());
                            prev = prev - move;
                            movingButton.setText("" + prev);
                            movingButton.setBackground(null);
                            moving = false;
                            if (actions > 0)
                            {
                                for (int i = 0; i < generalTerritories.length; i++)
                                {
                                    if (generalTerritories[i] != null)
                                    {
                                        generalTerritories[i].setEnabled(false);
                                    }
                                }
                                for (int i = 0; i < unoccupied.length; i++)
                                {
                                    if (unoccupied[i] != null)
                                    {
                                        unoccupied[i].setEnabled(false);
                                    }
                                }
                                for (int i = 0; i < alienTerritories.length; i++)
                                {
                                    if (alienTerritories[i] != null)
                                    {
                                        alienTerritories[i].setEnabled(true);
                                        if (alienTerritories[i].getText().equals("0"))
                                        {
                                            alienTerritories[i].setEnabled(false);
                                        }
                                    }
                                }
                            }
                            actions--;
                            lblActions.setText("Actions left: " + actions);
                        }
                    }
                } else if (turn.equals(g.getName()))
                {
                    if (!n.getText().equals("0"))
                    {
                        int move = 0;
                        boolean v = true;
                        try
                        {
                            move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to send into battle?"));
                        } catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(this, "Enter a number!");
                            actions++;
                            v = false;
                        }

                        if (v == true)
                        {
                            if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                            {
                                JOptionPane.showMessageDialog(this, "Invalid number.");
                                actions++;
                            } else
                            {
                                JOptionPane.showMessageDialog(null, "Rolling die...");
                                double roll = (double) (Math.random() * (5.5 - 0.5 + 1) + 0.5);
                                roll = Math.round(roll);
                                int roll1 = (int) roll;
                                double chance = ((roll / 10) + 0.2);
                                chance = chance * 100;
                                chance = Math.round(chance);
                                chance = chance / 100;
                                JOptionPane.showMessageDialog(this, "You rolled a " + roll1 + ". This means that each of your soldiers will have a " + (chance*100) + "% chance of killing an enemy.");
                                boolean success = false;
                                while (success == false)
                                {
                                    double kill = (double) (Math.random() * (1));
                                    if (kill > chance)
                                    {
                                        int prev = Integer.parseInt(movingButton.getText());
                                        prev--;
                                        totalGeneral--;
                                        movingButton.setText("" + prev);
                                    } else if (kill <= chance)
                                    {
                                        int prev = Integer.parseInt(n.getText());
                                        prev--;
                                        move--;
                                        totalAlien--;
                                        n.setText("" + prev);
                                    }
                                    if (move == 0)
                                    {
                                        success = true;
                                        JOptionPane.showMessageDialog(this, "Skirmish complete. Check casualties.");
                                    } else if (n.getText().equals("0"))
                                    {
                                        n.setForeground(Color.green);
                                        int prev = Integer.parseInt(n.getText());
                                        prev++;
                                        n.setText("" + prev);
                                        prev = Integer.parseInt(movingButton.getText());
                                        prev--;
                                        movingButton.setText("" + prev);
                                        success = true;
                                        boolean placed = false;
                                        for (int i = 0; i < generalTerritories.length; i++)
                                        {
                                            if (placed == false && generalTerritories[i] == null)
                                            {
                                                generalTerritories[i] = n;
                                                placed = true;
                                            }
                                        }
                                        for (int i = 0; i < alienTerritories.length; i++)
                                        {
                                            if (alienTerritories[i] != null && alienTerritories[i].getName().equals(n.getName()))
                                            {
                                                alienTerritories[i] = null;
                                            }
                                        }
                                    } else if (movingButton.getText().equals("0"))
                                    {
                                        movingButton.setForeground(Color.red);
                                        int prev = Integer.parseInt(n.getText());
                                        prev--;
                                        n.setText("" + prev);
                                        prev = Integer.parseInt(movingButton.getText());
                                        prev++;
                                        movingButton.setText("" + prev);
                                        success = true;
                                        boolean placed = false;
                                        for (int i = 0; i < alienTerritories.length; i++)
                                        {
                                            if (placed == false && alienTerritories[i] == null)
                                            {
                                                alienTerritories[i] = movingButton;
                                                placed = true;
                                            }
                                        }
                                        for (int i = 0; i < generalTerritories.length; i++)
                                        {
                                            if (generalTerritories[i] != null && generalTerritories[i].getName().equals(movingButton.getName()))
                                            {
                                                generalTerritories[i] = null;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        moving = false;
                        movingButton.setBackground(null);
                        if (actions > 0)
                        {
                            for (int i = 0; i < generalTerritories.length; i++)
                            {
                                if (generalTerritories[i] != null)
                                {
                                    generalTerritories[i].setEnabled(true);
                                    if (generalTerritories[i].getText().equals("0"))
                                    {
                                        generalTerritories[i].setEnabled(false);
                                    }
                                }
                            }
                            for (int i = 0; i < unoccupied.length; i++)
                            {
                                if (unoccupied[i] != null)
                                {
                                    unoccupied[i].setEnabled(false);
                                }
                            }
                            for (int i = 0; i < alienTerritories.length; i++)
                            {
                                if (alienTerritories[i] != null)
                                {
                                    alienTerritories[i].setEnabled(false);
                                }
                            }
                        }
                        actions--;
                        lblActions.setText("Actions left: " + actions);
                    } else if (n.getText().equals("0"))
                    {
                        int move = 0;
                        boolean v = true;
                        try
                        {
                            move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to move?"));
                        } catch (NumberFormatException ex)
                        {
                            JOptionPane.showMessageDialog(this, "Enter a number!");
                            v = false;
                        }

                        if (v == true)
                        {
                            if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                            {
                                JOptionPane.showMessageDialog(this, "Invalid number.");
                            } else
                            {
                                int prev = Integer.parseInt(n.getText());
                                prev += move;
                                n.setText("" + prev);
                                prev = Integer.parseInt(movingButton.getText());
                                prev = prev - move;
                                movingButton.setText("" + prev);
                                movingButton.setBackground(null);
                                moving = false;
                                if (actions > 0)
                                {
                                    for (int i = 0; i < generalTerritories.length; i++)
                                    {
                                        if (generalTerritories[i] != null)
                                        {
                                            generalTerritories[i].setEnabled(true);
                                            if (generalTerritories[i].getText().equals("0"))
                                            {
                                                generalTerritories[i].setEnabled(false);
                                            }
                                        }
                                    }
                                    for (int i = 0; i < unoccupied.length; i++)
                                    {
                                        if (unoccupied[i] != null)
                                        {
                                            unoccupied[i].setEnabled(false);
                                        }
                                    }
                                    for (int i = 0; i < alienTerritories.length; i++)
                                    {
                                        if (alienTerritories[i] != null)
                                        {
                                            alienTerritories[i].setEnabled(false);
                                        }
                                    }
                                }
                                actions--;
                                lblActions.setText("Actions left: " + actions);
                            }
                            n.setForeground(Color.green);
                            boolean placed = false;
                            for (int i = 0; i < generalTerritories.length; i++)
                            {
                                if (placed == false && generalTerritories[i] == null)
                                {
                                    generalTerritories[i] = n;
                                    placed = true;
                                }
                            }
                            for (int i = 0; i < alienTerritories.length; i++)
                            {
                                if (alienTerritories[i] != null && alienTerritories[i].getName().equals(n.getName()))
                                {
                                    alienTerritories[i] = null;
                                }
                            }
                        }
                    }
                }
            } else if (n.getForeground() == Color.black)
            {
                if (turn.equals(a.getName()))
                {
                    int move = 0;
                    boolean v = true;
                    try
                    {
                        move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to move?"));
                    } catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(this, "Enter a number!");
                        v = false;
                    }

                    if (v == true)
                    {
                        if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                        {
                            JOptionPane.showMessageDialog(this, "Invalid number.");
                        } else
                        {
                            int prev = Integer.parseInt(n.getText());
                            prev += move;
                            n.setText("" + prev);
                            prev = Integer.parseInt(movingButton.getText());
                            prev = prev - move;
                            movingButton.setText("" + prev);
                            movingButton.setBackground(null);
                            n.setForeground(Color.red);
                            boolean placed = false;
                            for (int i = 0; i < alienTerritories.length; i++)
                            {
                                if (placed == false && alienTerritories[i] == null)
                                {
                                    alienTerritories[i] = n;
                                    placed = true;
                                }
                            }
                            for (int i = 0; i < unoccupied.length; i++)
                            {
                                if (unoccupied[i] != null && unoccupied[i].getName().equals(n.getName()))
                                {
                                    unoccupied[i] = null;
                                }
                            }
                            moving = false;
                            if (actions > 0)
                            {
                                for (int i = 0; i < generalTerritories.length; i++)
                                {
                                    if (generalTerritories[i] != null)
                                    {
                                        generalTerritories[i].setEnabled(false);
                                    }
                                }
                                for (int i = 0; i < unoccupied.length; i++)
                                {
                                    if (unoccupied[i] != null)
                                    {
                                        unoccupied[i].setEnabled(false);
                                    }
                                }
                                for (int i = 0; i < alienTerritories.length; i++)
                                {
                                    if (alienTerritories[i] != null)
                                    {
                                        alienTerritories[i].setEnabled(true);
                                        if (alienTerritories[i].getText().equals("0"))
                                        {
                                            alienTerritories[i].setEnabled(false);
                                        }
                                    }
                                }
                            }
                            actions--;
                            lblActions.setText("Actions left: " + actions);
                        }
                    }
                } else if (turn.equals(g.getName()))
                {
                    int move = 0;
                    boolean v = true;
                    try
                    {
                        move = Integer.parseInt(JOptionPane.showInputDialog(this, "How many troops would you like to move?"));
                    } catch (NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(this, "Enter a number!");
                        v = false;
                    }

                    if (v == true)
                    {
                        if (move > Integer.parseInt(movingButton.getText()) || move < 1)
                        {
                            JOptionPane.showMessageDialog(this, "Invalid number.");
                        } else
                        {
                            int prev = Integer.parseInt(n.getText());
                            prev += move;
                            n.setText("" + prev);
                            prev = Integer.parseInt(movingButton.getText());
                            prev = prev - move;
                            movingButton.setText("" + prev);
                            movingButton.setBackground(null);
                            n.setForeground(Color.green);
                            boolean placed = false;
                            for (int i = 0; i < generalTerritories.length; i++)
                            {
                                if (placed == false && generalTerritories[i] == null)
                                {
                                    generalTerritories[i] = n;
                                    placed = true;
                                }
                            }
                            for (int i = 0; i < unoccupied.length; i++)
                            {
                                if (unoccupied[i] != null && unoccupied[i].getName().equals(n.getName()))
                                {
                                    unoccupied[i] = null;
                                }
                            }
                            moving = false;
                            if (actions > 0)
                            {
                                for (int i = 0; i < generalTerritories.length; i++)
                                {
                                    if (generalTerritories[i] != null)
                                    {
                                        generalTerritories[i].setEnabled(true);
                                        if (generalTerritories[i].getText().equals("0"))
                                        {
                                            generalTerritories[i].setEnabled(false);
                                        }
                                    }
                                }
                                for (int i = 0; i < unoccupied.length; i++)
                                {
                                    if (unoccupied[i] != null)
                                    {
                                        unoccupied[i].setEnabled(false);
                                    }
                                }
                                for (int i = 0; i < alienTerritories.length; i++)
                                {
                                    if (alienTerritories[i] != null)
                                    {
                                        alienTerritories[i].setEnabled(false);
                                    }
                                }
                            }
                            actions--;
                            lblActions.setText("Actions left: " + actions);
                        }
                    }
                }
            }

            for (int i = 0; i < generalTerritories.length; i++)
            {
                if (generalTerritories[i] != null)
                {
                    if (generalTerritories[i].getText().equals("0"))
                    {
                        for (int j = 0; j < unoccupied.length; j++)
                        {
                            if (unoccupied[j] == null)
                            {
                                unoccupied[j] = generalTerritories[i];
                            }
                        }
                        generalTerritories[i].setForeground(Color.black);
                        generalTerritories[i] = null;
                    }
                }
            }
            for (int i = 0; i < alienTerritories.length; i++)
            {
                if (alienTerritories[i] != null)
                {
                    if (alienTerritories[i].getText().equals("0"))
                    {
                        for (int j = 0; j < unoccupied.length; j++)
                        {
                            if (unoccupied[j] == null)
                            {
                                unoccupied[j] = alienTerritories[i];
                            }
                        }
                        alienTerritories[i].setForeground(Color.black);
                        alienTerritories[i] = null;
                    }
                }
            }

            if (actions == 0 && turn.equals(g.getName()))
            {
                alienTurn(a);
            } else if (actions == 0 && turn.equals(a.getName()))
            {
                generalTurn(g);
            }
        }
        btnReinforcements.setEnabled(false);
        //calculate win
        if (totalAlien == 0)
        {
            frmPlay.setVisible(false);
            frmEndGeneral.setVisible(true);
            frmEndGeneral.setLocationRelativeTo(this);
            g.setHighScore(maxAlien + (100 - totalRounds));
            lblEndGeneralTitle.setText("General: " + g.getName());
            lblMaxTroopsGeneral.setText("Max Troops: " + maxGeneral);
            lblScoreGeneral.setText("Score: " + g.getHighScore());
            lblEndAlienTitle.setText("Alien: " + a.getName());
            lblMaxTroopsAlien.setText("Max Troops: " + maxAlien);
            lblScoreAlien.setText("Score: N/A");
            lblTurnsTaken.setText("Turns Taken: " + totalRounds);
        } else if (totalGeneral == 0)
        {
            frmPlay.setVisible(false);
            frmEndAlien.setVisible(true);
            frmEndAlien.setLocationRelativeTo(this);
            a.setHighScore(maxGeneral + (100 - totalRounds));
            lblEndGeneralTitle1.setText("General: " + g.getName());
            lblMaxTroopsGeneral1.setText("Max Troops: " + maxGeneral);
            lblScoreGeneral1.setText("Score: N/A");
            lblEndAlienTitle1.setText("Alien: " + a.getName());
            lblMaxTroopsAlien1.setText("Max Troops: " + maxAlien);
            lblScoreAlien1.setText("Score: " + a.getHighScore());
            lblTurnsTaken1.setText("Turns Taken: " + totalRounds);
        }
    }

    //btnSA1.setForeground(Color.red);   <-- to set button colour
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        frmHighscores = new javax.swing.JFrame();
        lblHighscoresTitle = new javax.swing.JLabel();
        lblGeneralsHighscores = new javax.swing.JLabel();
        lblAlensHighscores = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaHighscoresGeneral = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaHighscoresAlien = new javax.swing.JTextArea();
        btnClear = new javax.swing.JButton();
        btnBackHighscores = new javax.swing.JButton();
        lblHighscoresBackground = new javax.swing.JLabel();
        frmAccountEditor = new javax.swing.JFrame();
        cmbManageUser = new javax.swing.JComboBox<>();
        btnDeleteUser = new javax.swing.JButton();
        btnChangePassword = new javax.swing.JButton();
        btnNewUser = new javax.swing.JButton();
        btnBackAccountEditor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaAccounts = new javax.swing.JTextArea();
        lblAccountEditorTitle = new javax.swing.JLabel();
        lblAccountEditorBackground = new javax.swing.JLabel();
        frmRegister = new javax.swing.JFrame();
        lblRegisterUser = new javax.swing.JLabel();
        lblRegisterPass = new javax.swing.JLabel();
        txfRegisterUser = new javax.swing.JTextField();
        btnRegister2 = new javax.swing.JButton();
        btnCancelRegister = new javax.swing.JButton();
        pwfRegisterPass = new javax.swing.JPasswordField();
        lblRegisterBackground = new javax.swing.JLabel();
        frmHelp = new javax.swing.JFrame();
        lblHelpTitle = new javax.swing.JLabel();
        btnBackHelp = new javax.swing.JButton();
        tabHelp = new javax.swing.JTabbedPane();
        pnlHowToPlay = new javax.swing.JPanel();
        scrHowToPlay = new javax.swing.JScrollPane();
        txaHowToPlay = new javax.swing.JTextArea();
        pnlProfileManagement = new javax.swing.JPanel();
        scrProfileManagement = new javax.swing.JScrollPane();
        txaProfileManagement = new javax.swing.JTextArea();
        pnlHighscores = new javax.swing.JPanel();
        scrHighscores = new javax.swing.JScrollPane();
        txaHighscores = new javax.swing.JTextArea();
        lblHelpBackground = new javax.swing.JLabel();
        frmGeneralLogin = new javax.swing.JFrame();
        lblGeneralLoginTitle = new javax.swing.JLabel();
        txfUsernameGeneral = new javax.swing.JTextField();
        pwfPasswordGeneral = new javax.swing.JPasswordField();
        btnLoginGeneral = new javax.swing.JButton();
        lblGeneralLoginBackground = new javax.swing.JLabel();
        frmAlienLogin = new javax.swing.JFrame();
        txfUsernameAlien = new javax.swing.JTextField();
        pwfPasswordAlien = new javax.swing.JPasswordField();
        btnLoginAlien = new javax.swing.JButton();
        lblAlienLoginBackground = new javax.swing.JLabel();
        frmPlay = new javax.swing.JFrame();
        lblTroops = new javax.swing.JLabel();
        lblTurn = new javax.swing.JLabel();
        lblActions = new javax.swing.JLabel();
        btnReinforcements = new javax.swing.JButton();
        btnExitPlay = new javax.swing.JButton();
        btnSA1 = new javax.swing.JButton();
        btnSA2 = new javax.swing.JButton();
        btnSA3 = new javax.swing.JButton();
        btnSA4 = new javax.swing.JButton();
        btnUSA1 = new javax.swing.JButton();
        btnUSA2 = new javax.swing.JButton();
        btnUSA3 = new javax.swing.JButton();
        btnUSA4 = new javax.swing.JButton();
        btnUSA5 = new javax.swing.JButton();
        btnUSA6 = new javax.swing.JButton();
        btnUSA7 = new javax.swing.JButton();
        btnUSA8 = new javax.swing.JButton();
        btnEU1 = new javax.swing.JButton();
        btnEU2 = new javax.swing.JButton();
        btnEU3 = new javax.swing.JButton();
        btnAF1 = new javax.swing.JButton();
        btnAF2 = new javax.swing.JButton();
        btnAF3 = new javax.swing.JButton();
        btnAF4 = new javax.swing.JButton();
        btnAF5 = new javax.swing.JButton();
        btnAU1 = new javax.swing.JButton();
        btnAU2 = new javax.swing.JButton();
        btnAU3 = new javax.swing.JButton();
        btnAI1 = new javax.swing.JButton();
        btnAI2 = new javax.swing.JButton();
        btnAI3 = new javax.swing.JButton();
        btnAI4 = new javax.swing.JButton();
        btnAI5 = new javax.swing.JButton();
        btnAI6 = new javax.swing.JButton();
        btnAI7 = new javax.swing.JButton();
        btnAI8 = new javax.swing.JButton();
        btnAI9 = new javax.swing.JButton();
        btnAI10 = new javax.swing.JButton();
        btnAI11 = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();
        lblRiskBoard = new javax.swing.JLabel();
        frmEndGeneral = new javax.swing.JFrame();
        lblEarthDefended = new javax.swing.JLabel();
        btnDoneGeneral = new javax.swing.JButton();
        lblEndAlienTitle = new javax.swing.JLabel();
        lblMaxTroopsGeneral = new javax.swing.JLabel();
        lblTurnsTaken = new javax.swing.JLabel();
        lblScoreGeneral = new javax.swing.JLabel();
        lblEndGeneralTitle = new javax.swing.JLabel();
        lblScoreAlien = new javax.swing.JLabel();
        lblMaxTroopsAlien = new javax.swing.JLabel();
        lblEndGeneralBackground = new javax.swing.JLabel();
        frmEndAlien = new javax.swing.JFrame();
        lblAliensInvaded = new javax.swing.JLabel();
        btnDoneAlien = new javax.swing.JButton();
        lblEndAlienTitle1 = new javax.swing.JLabel();
        lblMaxTroopsGeneral1 = new javax.swing.JLabel();
        lblTurnsTaken1 = new javax.swing.JLabel();
        lblScoreGeneral1 = new javax.swing.JLabel();
        lblEndGeneralTitle1 = new javax.swing.JLabel();
        lblScoreAlien1 = new javax.swing.JLabel();
        lblMaxTroopsAlien1 = new javax.swing.JLabel();
        lblEndAlienBackground = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        btnHighScores = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnAccountEditor = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        lblMainMenuBackground = new javax.swing.JLabel();

        frmHighscores.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmHighscores.setTitle("Highscores");
        frmHighscores.setMinimumSize(new java.awt.Dimension(963, 568));
        frmHighscores.setResizable(false);
        frmHighscores.setSize(new java.awt.Dimension(960, 540));
        frmHighscores.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHighscoresTitle.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblHighscoresTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHighscoresTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HighScoresTitle.png"))); // NOI18N
        frmHighscores.getContentPane().add(lblHighscoresTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 420, 50));

        lblGeneralsHighscores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GeneralsHighScores.png"))); // NOI18N
        frmHighscores.getContentPane().add(lblGeneralsHighscores, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 240, 40));

        lblAlensHighscores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AliensHighScores.png"))); // NOI18N
        frmHighscores.getContentPane().add(lblAlensHighscores, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 170, 40));

        txaHighscoresGeneral.setEditable(false);
        txaHighscoresGeneral.setColumns(20);
        txaHighscoresGeneral.setRows(5);
        jScrollPane2.setViewportView(txaHighscoresGeneral);

        frmHighscores.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 430, 360));

        txaHighscoresAlien.setColumns(20);
        txaHighscoresAlien.setRows(5);
        jScrollPane1.setViewportView(txaHighscoresAlien);

        frmHighscores.getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 440, 360));

        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnClearActionPerformed(evt);
            }
        });
        frmHighscores.getContentPane().add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 500, -1, -1));

        btnBackHighscores.setText("BACK");
        btnBackHighscores.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBackHighscoresActionPerformed(evt);
            }
        });
        frmHighscores.getContentPane().add(btnBackHighscores, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 500, -1, -1));

        lblHighscoresBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MainMenu.jpg"))); // NOI18N
        frmHighscores.getContentPane().add(lblHighscoresBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        frmAccountEditor.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmAccountEditor.setTitle("Account Editor");
        frmAccountEditor.setMinimumSize(new java.awt.Dimension(963, 568));
        frmAccountEditor.setResizable(false);
        frmAccountEditor.setSize(new java.awt.Dimension(960, 540));
        frmAccountEditor.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmbManageUser.setToolTipText("Select name of profile to manage");
        frmAccountEditor.getContentPane().add(cmbManageUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, 230, -1));

        btnDeleteUser.setText("DELETE");
        btnDeleteUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDeleteUserActionPerformed(evt);
            }
        });
        frmAccountEditor.getContentPane().add(btnDeleteUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, -1, 20));

        btnChangePassword.setText("CHANGE PASSWORD");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnChangePasswordActionPerformed(evt);
            }
        });
        frmAccountEditor.getContentPane().add(btnChangePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, -1, 20));

        btnNewUser.setText("NEW USER");
        btnNewUser.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnNewUserActionPerformed(evt);
            }
        });
        frmAccountEditor.getContentPane().add(btnNewUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 490, -1, 20));

        btnBackAccountEditor.setText("BACK");
        btnBackAccountEditor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBackAccountEditorActionPerformed(evt);
            }
        });
        frmAccountEditor.getContentPane().add(btnBackAccountEditor, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 490, -1, 20));

        txaAccounts.setEditable(false);
        txaAccounts.setColumns(20);
        txaAccounts.setRows(5);
        jScrollPane3.setViewportView(txaAccounts);

        frmAccountEditor.getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 920, 390));

        lblAccountEditorTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AccountEditorTitle.png"))); // NOI18N
        frmAccountEditor.getContentPane().add(lblAccountEditorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 520, 70));

        lblAccountEditorBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MainMenu.jpg"))); // NOI18N
        frmAccountEditor.getContentPane().add(lblAccountEditorBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        frmAccountEditor.getAccessibleContext().setAccessibleDescription("");

        frmRegister.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmRegister.setTitle("Register");
        frmRegister.setResizable(false);
        frmRegister.setSize(new java.awt.Dimension(400, 240));
        frmRegister.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRegisterUser.setText("Username:");
        frmRegister.getContentPane().add(lblRegisterUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        lblRegisterPass.setText("Password:");
        frmRegister.getContentPane().add(lblRegisterPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));
        frmRegister.getContentPane().add(txfRegisterUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 300, -1));

        btnRegister2.setText("REGISTER");
        btnRegister2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnRegister2ActionPerformed(evt);
            }
        });
        frmRegister.getContentPane().add(btnRegister2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        btnCancelRegister.setText("CANCEL");
        btnCancelRegister.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnCancelRegisterActionPerformed(evt);
            }
        });
        frmRegister.getContentPane().add(btnCancelRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, -1, -1));
        frmRegister.getContentPane().add(pwfRegisterPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 300, -1));
        frmRegister.getContentPane().add(lblRegisterBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 240));

        frmHelp.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmHelp.setTitle("Highscores");
        frmHelp.setMinimumSize(new java.awt.Dimension(963, 568));
        frmHelp.setResizable(false);
        frmHelp.setSize(new java.awt.Dimension(963, 568));
        frmHelp.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHelpTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HelpTitle.png"))); // NOI18N
        frmHelp.getContentPane().add(lblHelpTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 170, 50));

        btnBackHelp.setText("BACK");
        btnBackHelp.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnBackHelpActionPerformed(evt);
            }
        });
        frmHelp.getContentPane().add(btnBackHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 500, -1, -1));

        txaHowToPlay.setEditable(false);
        txaHowToPlay.setColumns(20);
        txaHowToPlay.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txaHowToPlay.setRows(5);
        txaHowToPlay.setText("> To start a new game, click on play from the home menu. Then log in with your two accounts that will be playing against one another. (You cannot play against \nyourself).\n\n>Once the game has started, the general must place his 30 starting reinforcements by clicking on any territory and specifying the amount to place. \nWhen he is finished, the alien must place his. Then the game begins.\n\n>A turn consists of 3 actions, in which the alien or general can attack, take unoccupied territories, move troops around or call reinforcements. All of these actions\ntake 1 turn, and are initiated by clicking on the button you wish to manipulate. The button being manipulated will have a black outline, and to cancel the move, click\non the button with the black outline.\n\n>To attack, click on one of your territories, then on an opponents territory, then specify the amount of troops you want to send into battle. Your chance of success is \nrandomly generated by a die. You can only attack if the opponent's territory is adjacent to yours.\n\n>To move troops, click on one of your territories and the on any one of your other territories, or on an adjacent unoccupied territory.\n\n>To call reinforcements, click on the territory you want to call the troops to, then click on the \"REINFORCEMENTS\" button. The amount of available reinforcements is \nshown above the button. You can only do this once per round.\nTAKE NOTE:\nFor the general, getting more territories means more reinforcements per round. Asian territories give 3 each per round, Australian territories give 1 each per round, and \nthe rest give 2 each per round.\nFor the Alien, reinforcements start as a random number between 2 and 10, and increase exponentially each round that reinforcements are not called, up to 10 rounds.\nThe new rounds reinforcements are added to the previous number of reinforcements each time, so it is not required to call reinforcements each round.\n\n>To win the game, destroy every troop that your opponent has on the board.");
        scrHowToPlay.setViewportView(txaHowToPlay);

        javax.swing.GroupLayout pnlHowToPlayLayout = new javax.swing.GroupLayout(pnlHowToPlay);
        pnlHowToPlay.setLayout(pnlHowToPlayLayout);
        pnlHowToPlayLayout.setHorizontalGroup(
            pnlHowToPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
            .addGroup(pnlHowToPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrHowToPlay, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
        );
        pnlHowToPlayLayout.setVerticalGroup(
            pnlHowToPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
            .addGroup(pnlHowToPlayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrHowToPlay, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        tabHelp.addTab("How to play", pnlHowToPlay);

        txaProfileManagement.setEditable(false);
        txaProfileManagement.setColumns(20);
        txaProfileManagement.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txaProfileManagement.setRows(5);
        txaProfileManagement.setText(">To manage profiles, click on \"ACCOUNT EDITOR\" from the home screen. A list of profiles and their data will be listed.\n\n>To manage a profile, select it from the drop-down box located at the botton left. Then click on delete or change password to do so. Note that you will need to know the\npassword of the account to manage it.\n\n>To create a new profile, click on the \"NEW USER\" button, located at the bottom right. You will be shown a register screen where you can cancel, or enter a username\nand password for the new account. The username and password cannot exceed 15 characters. Click \"REGISTER\" once you are satisfied. The account will be added\nto the list and the dropdown box where you can manage it.");
        scrProfileManagement.setViewportView(txaProfileManagement);

        javax.swing.GroupLayout pnlProfileManagementLayout = new javax.swing.GroupLayout(pnlProfileManagement);
        pnlProfileManagement.setLayout(pnlProfileManagementLayout);
        pnlProfileManagementLayout.setHorizontalGroup(
            pnlProfileManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
            .addGroup(pnlProfileManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrProfileManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
        );
        pnlProfileManagementLayout.setVerticalGroup(
            pnlProfileManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
            .addGroup(pnlProfileManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrProfileManagement, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        tabHelp.addTab("Profile Management", pnlProfileManagement);

        txaHighscores.setEditable(false);
        txaHighscores.setColumns(20);
        txaHighscores.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txaHighscores.setRows(5);
        txaHighscores.setText(">To view and manage highscores, click on the \"HIGH SCORES\" button from the home page. Two seperate lists for Alien and General highscores will be shown.\n\n>To clear all the highscores, press \"CLEAR\" on the bottom right of the screen, and confirm\n\n>At the end of each game, the winner's score will be checked, and if it is greater than their current highscore, it will be updated with the new one.\n\n>Highscores are calculated with this formula: maximum troops of opponent on the board at a point in time + (100 - roundsTaken) where roundsTaken cannot be more\nthan 100.");
        scrHighscores.setViewportView(txaHighscores);

        javax.swing.GroupLayout pnlHighscoresLayout = new javax.swing.GroupLayout(pnlHighscores);
        pnlHighscores.setLayout(pnlHighscoresLayout);
        pnlHighscoresLayout.setHorizontalGroup(
            pnlHighscoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 915, Short.MAX_VALUE)
            .addGroup(pnlHighscoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrHighscores, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE))
        );
        pnlHighscoresLayout.setVerticalGroup(
            pnlHighscoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
            .addGroup(pnlHighscoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrHighscores, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        tabHelp.addTab("High scores", pnlHighscores);

        frmHelp.getContentPane().add(tabHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 920, 410));

        lblHelpBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MainMenu.jpg"))); // NOI18N
        frmHelp.getContentPane().add(lblHelpBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 540));

        frmGeneralLogin.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmGeneralLogin.setTitle("General Login");
        frmGeneralLogin.setMinimumSize(new java.awt.Dimension(600, 365));
        frmGeneralLogin.setResizable(false);
        frmGeneralLogin.setSize(new java.awt.Dimension(600, 365));
        frmGeneralLogin.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGeneralLoginTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GeneralLoginTitle.png"))); // NOI18N
        frmGeneralLogin.getContentPane().add(lblGeneralLoginTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 260, 60));

        txfUsernameGeneral.setText("Username...");
        frmGeneralLogin.getContentPane().add(txfUsernameGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 230, -1));

        pwfPasswordGeneral.setText("Password...");
        frmGeneralLogin.getContentPane().add(pwfPasswordGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 230, -1));

        btnLoginGeneral.setText("LOGIN");
        btnLoginGeneral.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLoginGeneralActionPerformed(evt);
            }
        });
        frmGeneralLogin.getContentPane().add(btnLoginGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        lblGeneralLoginBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/GeneralLogin.jpg"))); // NOI18N
        frmGeneralLogin.getContentPane().add(lblGeneralLoginBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 340));

        frmAlienLogin.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmAlienLogin.setTitle("Alien Login");
        frmAlienLogin.setMinimumSize(new java.awt.Dimension(600, 365));
        frmAlienLogin.setResizable(false);
        frmAlienLogin.setSize(new java.awt.Dimension(600, 365));
        frmAlienLogin.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txfUsernameAlien.setText("Username...");
        frmAlienLogin.getContentPane().add(txfUsernameAlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 230, -1));

        pwfPasswordAlien.setText("Password...");
        frmAlienLogin.getContentPane().add(pwfPasswordAlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 230, -1));

        btnLoginAlien.setText("LOGIN");
        btnLoginAlien.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnLoginAlienActionPerformed(evt);
            }
        });
        frmAlienLogin.getContentPane().add(btnLoginAlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, -1, -1));

        lblAlienLoginBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AlienLogin.jpg"))); // NOI18N
        frmAlienLogin.getContentPane().add(lblAlienLoginBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 340));

        frmPlay.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmPlay.setTitle("Alien Invaders");
        frmPlay.setMinimumSize(new java.awt.Dimension(965, 660));
        frmPlay.setResizable(false);
        frmPlay.setSize(new java.awt.Dimension(965, 660));
        frmPlay.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTroops.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTroops.setForeground(new java.awt.Color(255, 255, 255));
        lblTroops.setText("Reinforcements:");
        frmPlay.getContentPane().add(lblTroops, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 200, 30));

        lblTurn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblTurn.setForeground(new java.awt.Color(255, 255, 255));
        lblTurn.setText("...'s Turn");
        frmPlay.getContentPane().add(lblTurn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 200, 30));

        lblActions.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lblActions.setForeground(new java.awt.Color(255, 255, 255));
        lblActions.setText("Actions left: -");
        frmPlay.getContentPane().add(lblActions, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 500, 200, 30));

        btnReinforcements.setText("REINFORCEMENTS");
        btnReinforcements.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReinforcementsActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnReinforcements, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, -1, -1));

        btnExitPlay.setText("Exit");
        btnExitPlay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExitPlayActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnExitPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, -1, -1));

        btnSA1.setText("0");
        btnSA1.setName("btnSA1"); // NOI18N
        btnSA1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSA1ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnSA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, -1, -1));

        btnSA2.setText("0");
        btnSA2.setName("btnSA2"); // NOI18N
        btnSA2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSA2ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnSA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, -1, -1));

        btnSA3.setText("0");
        btnSA3.setName("btnSA3"); // NOI18N
        btnSA3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSA3ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnSA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 430, -1, -1));

        btnSA4.setText("0");
        btnSA4.setName("btnSA4"); // NOI18N
        btnSA4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSA4ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnSA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 510, -1, -1));

        btnUSA1.setText("0");
        btnUSA1.setName("btnUSA1"); // NOI18N
        btnUSA1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA1ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        btnUSA2.setText("0");
        btnUSA2.setName("btnUSA2"); // NOI18N
        btnUSA2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA2ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        btnUSA3.setText("0");
        btnUSA3.setName("btnUSA3"); // NOI18N
        btnUSA3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA3ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 160, -1, -1));

        btnUSA4.setText("0");
        btnUSA4.setName("btnUSA4"); // NOI18N
        btnUSA4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA4ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        btnUSA5.setText("0");
        btnUSA5.setName("btnUSA5"); // NOI18N
        btnUSA5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA5ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        btnUSA6.setText("0");
        btnUSA6.setName("btnUSA6"); // NOI18N
        btnUSA6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA6ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, -1, -1));

        btnUSA7.setText("0");
        btnUSA7.setName("btnUSA7"); // NOI18N
        btnUSA7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA7ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 170, -1, -1));

        btnUSA8.setText("0");
        btnUSA8.setName("btnUSA8"); // NOI18N
        btnUSA8.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnUSA8ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnUSA8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        btnEU1.setText("0");
        btnEU1.setName("btnEU1"); // NOI18N
        btnEU1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEU1ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnEU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 130, -1, -1));

        btnEU2.setText("0");
        btnEU2.setName("btnEU2"); // NOI18N
        btnEU2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEU2ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnEU2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, -1, -1));

        btnEU3.setText("0");
        btnEU3.setName("btnEU3"); // NOI18N
        btnEU3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEU3ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnEU3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 220, -1, -1));

        btnAF1.setText("0");
        btnAF1.setName("btnAF1"); // NOI18N
        btnAF1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAF1ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 380, -1, -1));

        btnAF2.setText("0");
        btnAF2.setName("btnAF2"); // NOI18N
        btnAF2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAF2ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAF2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 330, -1, -1));

        btnAF3.setText("0");
        btnAF3.setName("btnAF3"); // NOI18N
        btnAF3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAF3ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAF3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, -1, -1));

        btnAF4.setText("0");
        btnAF4.setName("btnAF4"); // NOI18N
        btnAF4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAF4ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAF4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 460, -1, -1));

        btnAF5.setText("0");
        btnAF5.setName("btnAF5"); // NOI18N
        btnAF5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAF5ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAF5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 540, -1, -1));

        btnAU1.setText("0");
        btnAU1.setName("btnAU1"); // NOI18N
        btnAU1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAU1ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAU1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        btnAU2.setText("0");
        btnAU2.setName("btnAU2"); // NOI18N
        btnAU2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAU2ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAU2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 530, -1, -1));

        btnAU3.setText("0");
        btnAU3.setName("btnAU3"); // NOI18N
        btnAU3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAU3ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAU3, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 520, -1, -1));

        btnAI1.setText("0");
        btnAI1.setName("btnAI1"); // NOI18N
        btnAI1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI1ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, -1, -1));

        btnAI2.setText("0");
        btnAI2.setName("btnAI2"); // NOI18N
        btnAI2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI2ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, -1, -1));

        btnAI3.setText("0");
        btnAI3.setName("btnAI3"); // NOI18N
        btnAI3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI3ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, -1, -1));

        btnAI4.setText("0");
        btnAI4.setName("btnAI4"); // NOI18N
        btnAI4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI4ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        btnAI5.setText("0");
        btnAI5.setName("btnAI5"); // NOI18N
        btnAI5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI5ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, -1, -1));

        btnAI6.setText("0");
        btnAI6.setName("btnAI6"); // NOI18N
        btnAI6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI6ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, -1, -1));

        btnAI7.setText("0");
        btnAI7.setName("btnAI7"); // NOI18N
        btnAI7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI7ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 170, -1, -1));

        btnAI8.setText("0");
        btnAI8.setName("btnAI8"); // NOI18N
        btnAI8.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI8ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI8, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 280, -1, -1));

        btnAI9.setText("0");
        btnAI9.setName("btnAI9"); // NOI18N
        btnAI9.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI9ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI9, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 360, -1, -1));

        btnAI10.setText("0");
        btnAI10.setName("btnAI10"); // NOI18N
        btnAI10.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI10ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI10, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, -1, -1));

        btnAI11.setText("0");
        btnAI11.setName("btnAI11"); // NOI18N
        btnAI11.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAI11ActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnAI11, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 230, -1, -1));

        btnTest.setText("TEST");
        btnTest.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnTestActionPerformed(evt);
            }
        });
        frmPlay.getContentPane().add(btnTest, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        lblRiskBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RiskBoard.jpg"))); // NOI18N
        lblRiskBoard.setMaximumSize(new java.awt.Dimension(960, 640));
        lblRiskBoard.setMinimumSize(new java.awt.Dimension(960, 640));
        lblRiskBoard.setPreferredSize(new java.awt.Dimension(960, 640));
        frmPlay.getContentPane().add(lblRiskBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 640));

        frmEndGeneral.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmEndGeneral.setTitle("General Won");
        frmEndGeneral.setMinimumSize(new java.awt.Dimension(925, 547));
        frmEndGeneral.setResizable(false);
        frmEndGeneral.setSize(new java.awt.Dimension(925, 547));
        frmEndGeneral.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblEarthDefended.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EarthDefended.png"))); // NOI18N
        frmEndGeneral.getContentPane().add(lblEarthDefended, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 870, 60));

        btnDoneGeneral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DoneButton.png"))); // NOI18N
        btnDoneGeneral.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoneGeneralActionPerformed(evt);
            }
        });
        frmEndGeneral.getContentPane().add(btnDoneGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 130, 50));

        lblEndAlienTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEndAlienTitle.setForeground(new java.awt.Color(255, 0, 0));
        lblEndAlienTitle.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblEndAlienTitle.setText("Alien:");
        frmEndGeneral.getContentPane().add(lblEndAlienTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 280, 30));

        lblMaxTroopsGeneral.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaxTroopsGeneral.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxTroopsGeneral.setText("Max Troops:");
        frmEndGeneral.getContentPane().add(lblMaxTroopsGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 280, 30));

        lblTurnsTaken.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTurnsTaken.setForeground(new java.awt.Color(255, 255, 255));
        lblTurnsTaken.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurnsTaken.setText("Turns taken:");
        frmEndGeneral.getContentPane().add(lblTurnsTaken, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 270, 30));

        lblScoreGeneral.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblScoreGeneral.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreGeneral.setText("Score:");
        frmEndGeneral.getContentPane().add(lblScoreGeneral, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 280, 30));

        lblEndGeneralTitle.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEndGeneralTitle.setForeground(new java.awt.Color(0, 255, 0));
        lblEndGeneralTitle.setText("General:");
        frmEndGeneral.getContentPane().add(lblEndGeneralTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 280, 30));

        lblScoreAlien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblScoreAlien.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreAlien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblScoreAlien.setText("Score:");
        frmEndGeneral.getContentPane().add(lblScoreAlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 280, 30));

        lblMaxTroopsAlien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaxTroopsAlien.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxTroopsAlien.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblMaxTroopsAlien.setText("Max Troops:");
        frmEndGeneral.getContentPane().add(lblMaxTroopsAlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 280, 30));

        lblEndGeneralBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/EarthProtected.jpg"))); // NOI18N
        frmEndGeneral.getContentPane().add(lblEndGeneralBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 520));

        frmEndAlien.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frmEndAlien.setTitle("Alien Won");
        frmEndAlien.setMinimumSize(new java.awt.Dimension(925, 547));
        frmEndAlien.setResizable(false);
        frmEndAlien.setSize(new java.awt.Dimension(925, 547));
        frmEndAlien.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAliensInvaded.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AliensInvaded.png"))); // NOI18N
        frmEndAlien.getContentPane().add(lblAliensInvaded, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 850, 60));

        btnDoneAlien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/DoneButton.png"))); // NOI18N
        btnDoneAlien.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnDoneAlienActionPerformed(evt);
            }
        });
        frmEndAlien.getContentPane().add(btnDoneAlien, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 450, 130, 50));

        lblEndAlienTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEndAlienTitle1.setForeground(new java.awt.Color(255, 0, 0));
        lblEndAlienTitle1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblEndAlienTitle1.setText("Alien:");
        frmEndAlien.getContentPane().add(lblEndAlienTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 280, 30));

        lblMaxTroopsGeneral1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaxTroopsGeneral1.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxTroopsGeneral1.setText("Max Troops:");
        frmEndAlien.getContentPane().add(lblMaxTroopsGeneral1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 280, 30));

        lblTurnsTaken1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTurnsTaken1.setForeground(new java.awt.Color(255, 255, 255));
        lblTurnsTaken1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTurnsTaken1.setText("Turns taken:");
        frmEndAlien.getContentPane().add(lblTurnsTaken1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 270, 30));

        lblScoreGeneral1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblScoreGeneral1.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreGeneral1.setText("Score:");
        frmEndAlien.getContentPane().add(lblScoreGeneral1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 280, 30));

        lblEndGeneralTitle1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEndGeneralTitle1.setForeground(new java.awt.Color(0, 255, 0));
        lblEndGeneralTitle1.setText("General:");
        frmEndAlien.getContentPane().add(lblEndGeneralTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 280, 30));

        lblScoreAlien1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblScoreAlien1.setForeground(new java.awt.Color(255, 255, 255));
        lblScoreAlien1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblScoreAlien1.setText("Score:");
        frmEndAlien.getContentPane().add(lblScoreAlien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 280, 30));

        lblMaxTroopsAlien1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblMaxTroopsAlien1.setForeground(new java.awt.Color(255, 255, 255));
        lblMaxTroopsAlien1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblMaxTroopsAlien1.setText("Max Troops:");
        frmEndAlien.getContentPane().add(lblMaxTroopsAlien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 280, 30));

        lblEndAlienBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/alien-invasion.jpg"))); // NOI18N
        frmEndAlien.getContentPane().add(lblEndAlienBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 520));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Alien Invaders");
        setMinimumSize(new java.awt.Dimension(960, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(960, 540));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/TitleText.png"))); // NOI18N
        lblTitle.setText("jLabel1");
        getContentPane().add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 520, 70));

        btnHighScores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HighscoresButton.png"))); // NOI18N
        btnHighScores.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHighScoresActionPerformed(evt);
            }
        });
        getContentPane().add(btnHighScores, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 280, 40));

        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/HelpButton.png"))); // NOI18N
        btnHelp.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnHelpActionPerformed(evt);
            }
        });
        getContentPane().add(btnHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 110, 40));

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ExitButton.png"))); // NOI18N
        btnExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 90, 50));

        btnAccountEditor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AccountEditorButton.png"))); // NOI18N
        btnAccountEditor.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAccountEditorActionPerformed(evt);
            }
        });
        getContentPane().add(btnAccountEditor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 350, 50));

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/PlayButton.png"))); // NOI18N
        btnPlay.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnPlayActionPerformed(evt);
            }
        });
        getContentPane().add(btnPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 110, 40));

        lblMainMenuBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MainMenu.jpg"))); // NOI18N
        getContentPane().add(lblMainMenuBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        setSize(new java.awt.Dimension(965, 568));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHighScoresActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHighScoresActionPerformed
    {//GEN-HEADEREND:event_btnHighScoresActionPerformed
        this.setVisible(false);
        frmHighscores.setVisible(true);
        frmHighscores.setLocationRelativeTo(this);
        refreshHighscores();
    }//GEN-LAST:event_btnHighScoresActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnClearActionPerformed
    {//GEN-HEADEREND:event_btnClearActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete all highscores?");
        if (confirm == 0)
        {
            String sql = "UPDATE Profiles SET HSAlien = 0";
            int rs = connect.update(sql);
            sql = "UPDATE Profiles SET HSGeneral = 0";
            rs = connect.update(sql);
            JOptionPane.showMessageDialog(this, "All highscores successfully deleted.");
        }
        refreshHighscores();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnBackHighscoresActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBackHighscoresActionPerformed
    {//GEN-HEADEREND:event_btnBackHighscoresActionPerformed
        frmHighscores.setVisible(false);
        setLocationRelativeTo(this);
        setVisible(true);
    }//GEN-LAST:event_btnBackHighscoresActionPerformed

    private void btnDeleteUserActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDeleteUserActionPerformed
    {//GEN-HEADEREND:event_btnDeleteUserActionPerformed
        String user = "" + cmbManageUser.getSelectedItem();
        String sql = "SELECT Username FROM Profiles;";
        ResultSet rs = connect.query(sql);
        boolean found = false;
        try
        {
            while (rs.next())
            {
                String temp = rs.getString("Username");
                if (temp.equals(user))
                {
                    found = true;
                }
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        if (found == true)
        {
            String pass = JOptionPane.showInputDialog("Enter your password to delete account.");
            sql = "SELECT Password FROM Profiles WHERE Username = '" + user + "';";
            ResultSet password = connect.query(sql);
            try
            {
                while (password.next())
                {
                    String temp = password.getString("Password");
                    if (temp.equals(pass))
                    {
                        sql = "DELETE FROM Profiles WHERE Username = '" + user + "';";
                        int delete = connect.update(sql);
                        JOptionPane.showMessageDialog(this, "Successfully deleted!");
                    } else if (!temp.equals(pass))
                    {
                        JOptionPane.showMessageDialog(this, "Incorrect password.");
                    }

                }
            } catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid query.");
            }
            refreshAccountEditor();
        } else
        {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }

    }//GEN-LAST:event_btnDeleteUserActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnHelpActionPerformed
    {//GEN-HEADEREND:event_btnHelpActionPerformed
        setVisible(false);
        frmHelp.setLocationRelativeTo(this);
        frmHelp.setVisible(true);
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExitActionPerformed
    {//GEN-HEADEREND:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnLoginGeneralActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLoginGeneralActionPerformed
    {//GEN-HEADEREND:event_btnLoginGeneralActionPerformed
        userGeneral = txfUsernameGeneral.getText();
        String sql = "SELECT Username FROM Profiles;";
        ResultSet rs = connect.query(sql);
        boolean found = false;
        try
        {
            while (rs.next())
            {
                String temp = rs.getString("Username");
                if (temp.equals(userGeneral))
                {
                    found = true;
                }
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }

        if (found == true)
        {
            String pass = pwfPasswordGeneral.getText();
            boolean correct = false;
            sql = "SELECT Password FROM Profiles WHERE Username = '" + userGeneral + "';";
            ResultSet password = connect.query(sql);
            try
            {
                while (password.next())
                {
                    String temp = password.getString("Password");
                    if (temp.equals(pass))
                    {
                        correct = true;
                    } else if (!temp.equals(pass))
                    {
                        JOptionPane.showMessageDialog(this, "Incorrect password.");
                    }
                    if (correct == true)
                    {
                        sql = "SELECT HSGeneral FROM Profiles WHERE Username = '" + userGeneral + "';";
                        rs = connect.query(sql);
                        int high = 0;
                        while (rs.next())
                        {
                            high = rs.getInt("HSGeneral");
                        }
                        sql = "SELECT GamesPlayed FROM Profiles WHERE Username = '" + userGeneral + "';";
                        rs = connect.query(sql);
                        int played = 0;
                        while (rs.next())
                        {
                            played = rs.getInt("GamesPlayed");
                        }
                        g = new General(userGeneral, high, played, 0);
                        frmGeneralLogin.setVisible(false);
                        frmAlienLogin.setVisible(true);
                        frmAlienLogin.setLocationRelativeTo(this);
                    }
                }
            } catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid query.");
            }
        } else if (found == false)
        {
            JOptionPane.showMessageDialog(this, "Username not found.");
        }
    }//GEN-LAST:event_btnLoginGeneralActionPerformed

    private void btnLoginAlienActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnLoginAlienActionPerformed
    {//GEN-HEADEREND:event_btnLoginAlienActionPerformed
        userAlien = txfUsernameAlien.getText();
        String sql = "SELECT Username FROM Profiles;";
        ResultSet rs = connect.query(sql);
        boolean found = false;
        if (userAlien.equals(userGeneral))
        {
            JOptionPane.showMessageDialog(this, "You can not play against yourself!");
        } else if (!userAlien.equals(userGeneral))
        {
            try
            {
                while (rs.next())
                {
                    String temp = rs.getString("Username");
                    if (temp.equals(userAlien))
                    {
                        found = true;
                    }
                }
            } catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid query.");
            }

            if (found == true)
            {
                String pass = pwfPasswordAlien.getText();
                boolean correct = false;
                sql = "SELECT Password FROM Profiles WHERE Username = '" + userAlien + "';";
                ResultSet password = connect.query(sql);
                try
                {
                    while (password.next())
                    {
                        String temp = password.getString("Password");
                        if (temp.equals(pass))
                        {
                            correct = true;
                        } else if (!temp.equals(pass))
                        {
                            JOptionPane.showMessageDialog(this, "Incorrect password.");
                        }
                        if (correct == true)
                        {
                            sql = "SELECT HSAlien FROM Profiles WHERE Username = '" + userAlien + "';";
                            rs = connect.query(sql);
                            int high = 0;
                            while (rs.next())
                            {
                                high = rs.getInt("HSAlien");
                            }
                            sql = "SELECT GamesPlayed FROM Profiles WHERE Username = '" + userAlien + "';";
                            rs = connect.query(sql);
                            int played = 0;
                            while (rs.next())
                            {
                                played = rs.getInt("GamesPlayed");
                            }
                            a = new Alien(userAlien, high, played, 0);
                            frmAlienLogin.setVisible(false);
                            frmPlay.setVisible(true);
                            frmPlay.setLocationRelativeTo(this);
                            gameStartGeneral();
                        }
                    }
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(this, "Invalid query.");
                }
            } else if (found == false)
            {
                JOptionPane.showMessageDialog(this, "Username not found.");
            }
        }
    }//GEN-LAST:event_btnLoginAlienActionPerformed

    private void btnBackHelpActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBackHelpActionPerformed
    {//GEN-HEADEREND:event_btnBackHelpActionPerformed
        frmHelp.setVisible(false);
        setLocationRelativeTo(this);
        setVisible(true);
    }//GEN-LAST:event_btnBackHelpActionPerformed

    private void btnRegister2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnRegister2ActionPerformed
    {//GEN-HEADEREND:event_btnRegister2ActionPerformed
        //check if username is taken, if not, register
        if (!txfRegisterUser.getText().equals("") && !pwfRegisterPass.getText().equals(""))
        {
            String sql = "SELECT * FROM Profiles WHERE Username LIKE '" + txfRegisterUser.getText() + "';";
            ResultSet rs = connect.query(sql);
            int count = 0;
            try
            {
                while (rs.next())
                {
                    count++;
                }
                if (count == 1)
                {
                    JOptionPane.showMessageDialog(this, "Username taken.");
                } else if (count == 0)
                {
                    if (txfRegisterUser.getText().length() < 16)
                    {
                        sql = "INSERT INTO Profiles (Username, Password) VALUES ('"
                                + txfRegisterUser.getText() + "', '"
                                + pwfRegisterPass.getText() + "');";

                        int records = connect.update(sql);

                        JOptionPane.showMessageDialog(this, "Successfully registered!");
                        frmRegister.setVisible(false);
                        frmAccountEditor.setVisible(true);
                        frmAccountEditor.setLocationRelativeTo(this);
                    } else if (txfRegisterUser.getText().length() >= 16)
                    {
                        JOptionPane.showMessageDialog(this, "Username must not exceed 15 characters.");
                    } else if (pwfRegisterPass.getText().length() >= 16)
                    {
                        JOptionPane.showMessageDialog(this, "Password must not exceed 15 characters.");
                    }
                }
            } catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(this, "Invalid query.");
            }
        } else if (pwfRegisterPass.getText().equals("") && txfRegisterUser.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter a username and password.");
        } else if (txfRegisterUser.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter a username.");
        } else if (pwfRegisterPass.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter a password.");
        }
        refreshAccountEditor();
    }//GEN-LAST:event_btnRegister2ActionPerformed

    private void btnCancelRegisterActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCancelRegisterActionPerformed
    {//GEN-HEADEREND:event_btnCancelRegisterActionPerformed
        frmRegister.setVisible(false);
        frmAccountEditor.setLocationRelativeTo(this);
        frmAccountEditor.setVisible(true);
    }//GEN-LAST:event_btnCancelRegisterActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnPlayActionPerformed
    {//GEN-HEADEREND:event_btnPlayActionPerformed
        txfUsernameGeneral.setText("Username...");
        pwfPasswordGeneral.setText("Password...");
        txfUsernameAlien.setText("Username...");
        pwfPasswordAlien.setText("Password...");
        this.setVisible(false);
        frmGeneralLogin.setLocationRelativeTo(this);
        frmGeneralLogin.setVisible(true);
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnAccountEditorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAccountEditorActionPerformed
    {//GEN-HEADEREND:event_btnAccountEditorActionPerformed
        this.setVisible(false);
        frmAccountEditor.setLocationRelativeTo(this);
        frmAccountEditor.setVisible(true);
        refreshAccountEditor();
    }//GEN-LAST:event_btnAccountEditorActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnChangePasswordActionPerformed
    {//GEN-HEADEREND:event_btnChangePasswordActionPerformed
        String user = "" + cmbManageUser.getSelectedItem();
        String sql = "SELECT Username FROM Profiles;";
        ResultSet rs = connect.query(sql);
        boolean found = false;

        String old = JOptionPane.showInputDialog("Enter your previous password.");
        boolean correct = false;
        sql = "SELECT Password FROM Profiles WHERE Username = '" + user + "';";
        ResultSet password = connect.query(sql);
        try
        {
            while (password.next())
            {
                String temp = password.getString("Password");
                if (temp.equals(old))
                {
                    correct = true;
                } else if (!temp.equals(old))
                {
                    JOptionPane.showMessageDialog(this, "Incorrect password.");
                }
                if (correct == true)
                {
                    String newP = JOptionPane.showInputDialog("Enter your new password.");
                    if (!"".equals(newP))
                    {
                        sql = "UPDATE Profiles SET Password = '" + newP + "' WHERE Username = '" + user + "';";
                        int records = connect.update(sql);
                        JOptionPane.showMessageDialog(this, "Password successfully updated.");
                    } else
                    {
                        JOptionPane.showMessageDialog(this, "Enter a password.");
                    }
                }
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        refreshAccountEditor();
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnBackAccountEditorActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBackAccountEditorActionPerformed
    {//GEN-HEADEREND:event_btnBackAccountEditorActionPerformed
        frmAccountEditor.setVisible(false);
        setLocationRelativeTo(this);
        setVisible(true);
    }//GEN-LAST:event_btnBackAccountEditorActionPerformed

    private void btnNewUserActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnNewUserActionPerformed
    {//GEN-HEADEREND:event_btnNewUserActionPerformed
        txfRegisterUser.setText("");
        pwfRegisterPass.setText("");
        frmAccountEditor.setVisible(false);
        frmRegister.setLocationRelativeTo(this);
        frmRegister.setVisible(true);
    }//GEN-LAST:event_btnNewUserActionPerformed

    private void btnExitPlayActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExitPlayActionPerformed
    {//GEN-HEADEREND:event_btnExitPlayActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit the game?");
        if (confirm == 0)
        {
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitPlayActionPerformed

    private void btnUSA3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA3ActionPerformed
    {//GEN-HEADEREND:event_btnUSA3ActionPerformed
        //checks for conditions, and calls the appropriate gameplay method
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA3);
                movingButton = btnUSA3;
                btnUSA3.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA3);
                movingButton = btnUSA3;
                btnUSA3.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA3);
        }
        startup(btnUSA3);
    }//GEN-LAST:event_btnUSA3ActionPerformed

    private void btnSA3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSA3ActionPerformed
    {//GEN-HEADEREND:event_btnSA3ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnSA3);
                movingButton = btnSA3;
                btnSA3.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnSA3);
                movingButton = btnSA3;
                btnSA3.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnSA3);
        }
        startup(btnSA3);
    }//GEN-LAST:event_btnSA3ActionPerformed

    private void btnSA1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSA1ActionPerformed
    {//GEN-HEADEREND:event_btnSA1ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnSA1);
                movingButton = btnSA1;
                btnSA1.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnSA1);
                movingButton = btnSA1;
                btnSA1.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnSA1);
        }
        startup(btnSA1);
    }//GEN-LAST:event_btnSA1ActionPerformed

    private void btnAF3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAF3ActionPerformed
    {//GEN-HEADEREND:event_btnAF3ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAF3);
                movingButton = btnAF3;
                btnAF3.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAF3);
                movingButton = btnAF3;
                btnAF3.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAF3);
        }
        startup(btnAF3);
    }//GEN-LAST:event_btnAF3ActionPerformed

    private void btnAI4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI4ActionPerformed
    {//GEN-HEADEREND:event_btnAI4ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI4);
                movingButton = btnAI4;
                btnAI4.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI4);
                movingButton = btnAI4;
                btnAI4.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI4);
        }
        startup(btnAI4);
    }//GEN-LAST:event_btnAI4ActionPerformed

    private void btnUSA1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA1ActionPerformed
    {//GEN-HEADEREND:event_btnUSA1ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA1);
                movingButton = btnUSA1;
                btnUSA1.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA1);
                movingButton = btnUSA1;
                btnUSA1.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA1);
        }
        startup(btnUSA1);
    }//GEN-LAST:event_btnUSA1ActionPerformed

    private void btnUSA2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA2ActionPerformed
    {//GEN-HEADEREND:event_btnUSA2ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA2);
                movingButton = btnUSA2;
                btnUSA2.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA2);
                movingButton = btnUSA2;
                btnUSA2.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA2);
        }
        startup(btnUSA2);
    }//GEN-LAST:event_btnUSA2ActionPerformed

    private void btnUSA4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA4ActionPerformed
    {//GEN-HEADEREND:event_btnUSA4ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA4);
                movingButton = btnUSA4;
                btnUSA4.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA4);
                movingButton = btnUSA4;
                btnUSA4.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA4);
        }
        startup(btnUSA4);
    }//GEN-LAST:event_btnUSA4ActionPerformed

    private void btnUSA5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA5ActionPerformed
    {//GEN-HEADEREND:event_btnUSA5ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA5);
                movingButton = btnUSA5;
                btnUSA5.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA5);
                movingButton = btnUSA5;
                btnUSA5.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA5);
        }
        startup(btnUSA5);
    }//GEN-LAST:event_btnUSA5ActionPerformed

    private void btnUSA6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA6ActionPerformed
    {//GEN-HEADEREND:event_btnUSA6ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA6);
                movingButton = btnUSA6;
                btnUSA6.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA6);
                movingButton = btnUSA6;
                btnUSA6.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA6);
        }
        startup(btnUSA6);
    }//GEN-LAST:event_btnUSA6ActionPerformed

    private void btnUSA7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA7ActionPerformed
    {//GEN-HEADEREND:event_btnUSA7ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA7);
                movingButton = btnUSA7;
                btnUSA7.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA7);
                movingButton = btnUSA7;
                btnUSA7.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA7);
        }
        startup(btnUSA7);
    }//GEN-LAST:event_btnUSA7ActionPerformed

    private void btnUSA8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnUSA8ActionPerformed
    {//GEN-HEADEREND:event_btnUSA8ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnUSA8);
                movingButton = btnUSA8;
                btnUSA8.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnUSA8);
                movingButton = btnUSA8;
                btnUSA8.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnUSA8);
        }
        startup(btnUSA8);
    }//GEN-LAST:event_btnUSA8ActionPerformed

    private void btnSA2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSA2ActionPerformed
    {//GEN-HEADEREND:event_btnSA2ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnSA2);
                movingButton = btnSA2;
                btnSA2.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnSA2);
                movingButton = btnSA2;
                btnSA2.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnSA2);
        }
        startup(btnSA2);
    }//GEN-LAST:event_btnSA2ActionPerformed

    private void btnSA4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSA4ActionPerformed
    {//GEN-HEADEREND:event_btnSA4ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnSA4);
                movingButton = btnSA4;
                btnSA4.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnSA4);
                movingButton = btnSA4;
                btnSA4.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnSA4);
        }
        startup(btnSA4);
    }//GEN-LAST:event_btnSA4ActionPerformed

    private void btnEU1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEU1ActionPerformed
    {//GEN-HEADEREND:event_btnEU1ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnEU1);
                movingButton = btnEU1;
                btnEU1.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnEU1);
                movingButton = btnEU1;
                btnEU1.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnEU1);
        }
        startup(btnEU1);
    }//GEN-LAST:event_btnEU1ActionPerformed

    private void btnEU3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEU3ActionPerformed
    {//GEN-HEADEREND:event_btnEU3ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnEU3);
                movingButton = btnEU3;
                btnEU3.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnEU3);
                movingButton = btnEU3;
                btnEU3.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnEU3);
        }
        startup(btnEU3);
    }//GEN-LAST:event_btnEU3ActionPerformed

    private void btnEU2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnEU2ActionPerformed
    {//GEN-HEADEREND:event_btnEU2ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnEU2);
                movingButton = btnEU2;
                btnEU2.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnEU2);
                movingButton = btnEU2;
                btnEU2.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnEU2);
        }
        startup(btnEU2);
    }//GEN-LAST:event_btnEU2ActionPerformed

    private void btnAF1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAF1ActionPerformed
    {//GEN-HEADEREND:event_btnAF1ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAF1);
                movingButton = btnAF1;
                btnAF1.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAF1);
                movingButton = btnAF1;
                btnAF1.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAF1);
        }
        startup(btnAF1);
    }//GEN-LAST:event_btnAF1ActionPerformed

    private void btnAF2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAF2ActionPerformed
    {//GEN-HEADEREND:event_btnAF2ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAF2);
                movingButton = btnAF2;
                btnAF2.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAF2);
                movingButton = btnAF2;
                btnAF2.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAF2);
        }
        startup(btnAF2);
    }//GEN-LAST:event_btnAF2ActionPerformed

    private void btnAF4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAF4ActionPerformed
    {//GEN-HEADEREND:event_btnAF4ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAF4);
                movingButton = btnAF4;
                btnAF4.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAF4);
                movingButton = btnAF4;
                btnAF4.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAF4);
        }
        startup(btnAF4);
    }//GEN-LAST:event_btnAF4ActionPerformed

    private void btnAF5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAF5ActionPerformed
    {//GEN-HEADEREND:event_btnAF5ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAF5);
                movingButton = btnAF5;
                btnAF5.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAF5);
                movingButton = btnAF5;
                btnAF5.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAF5);
        }
        startup(btnAF5);
    }//GEN-LAST:event_btnAF5ActionPerformed

    private void btnAU1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAU1ActionPerformed
    {//GEN-HEADEREND:event_btnAU1ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAU1);
                movingButton = btnAU1;
                btnAU1.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAU1);
                movingButton = btnAU1;
                btnAU1.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAU1);
        }
        startup(btnAU1);
    }//GEN-LAST:event_btnAU1ActionPerformed

    private void btnAU2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAU2ActionPerformed
    {//GEN-HEADEREND:event_btnAU2ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAU2);
                movingButton = btnAU2;
                btnAU2.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAU2);
                movingButton = btnAU2;
                btnAU2.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAU2);
        }
        startup(btnAU2);
    }//GEN-LAST:event_btnAU2ActionPerformed

    private void btnAU3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAU3ActionPerformed
    {//GEN-HEADEREND:event_btnAU3ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAU3);
                movingButton = btnAU3;
                btnAU3.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAU3);
                movingButton = btnAU3;
                btnAU3.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAU3);
        }
        startup(btnAU3);
    }//GEN-LAST:event_btnAU3ActionPerformed

    private void btnAI1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI1ActionPerformed
    {//GEN-HEADEREND:event_btnAI1ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI1);
                movingButton = btnAI1;
                btnAI1.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI1);
                movingButton = btnAI1;
                btnAI1.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI1);
        }
        startup(btnAI1);
    }//GEN-LAST:event_btnAI1ActionPerformed

    private void btnAI2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI2ActionPerformed
    {//GEN-HEADEREND:event_btnAI2ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI2);
                movingButton = btnAI2;
                btnAI2.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI2);
                movingButton = btnAI2;
                btnAI2.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI2);
        }
        startup(btnAI2);
    }//GEN-LAST:event_btnAI2ActionPerformed

    private void btnAI3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI3ActionPerformed
    {//GEN-HEADEREND:event_btnAI3ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI3);
                movingButton = btnAI3;
                btnAI3.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI3);
                movingButton = btnAI3;
                btnAI3.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI3);
        }
        startup(btnAI3);
    }//GEN-LAST:event_btnAI3ActionPerformed

    private void btnAI5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI5ActionPerformed
    {//GEN-HEADEREND:event_btnAI5ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI5);
                movingButton = btnAI5;
                btnAI5.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI5);
                movingButton = btnAI5;
                btnAI5.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI5);
        }
        startup(btnAI5);
    }//GEN-LAST:event_btnAI5ActionPerformed

    private void btnAI6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI6ActionPerformed
    {//GEN-HEADEREND:event_btnAI6ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI6);
                movingButton = btnAI6;
                btnAI6.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI6);
                movingButton = btnAI6;
                btnAI6.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI6);
        }
        startup(btnAI6);
    }//GEN-LAST:event_btnAI6ActionPerformed

    private void btnAI7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI7ActionPerformed
    {//GEN-HEADEREND:event_btnAI7ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI7);
                movingButton = btnAI7;
                btnAI7.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI7);
                movingButton = btnAI7;
                btnAI7.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI7);
        }
        startup(btnAI7);
    }//GEN-LAST:event_btnAI7ActionPerformed

    private void btnAI8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI8ActionPerformed
    {//GEN-HEADEREND:event_btnAI8ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI8);
                movingButton = btnAI8;
                btnAI8.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI8);
                movingButton = btnAI8;
                btnAI8.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI8);
        }
        startup(btnAI8);
    }//GEN-LAST:event_btnAI8ActionPerformed

    private void btnAI9ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI9ActionPerformed
    {//GEN-HEADEREND:event_btnAI9ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI9);
                movingButton = btnAI9;
                btnAI9.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI9);
                movingButton = btnAI9;
                btnAI9.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI9);
        }
        startup(btnAI9);
    }//GEN-LAST:event_btnAI9ActionPerformed

    private void btnAI10ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI10ActionPerformed
    {//GEN-HEADEREND:event_btnAI10ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI10);
                movingButton = btnAI10;
                btnAI10.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI10);
                movingButton = btnAI10;
                btnAI10.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI10);
        }
        startup(btnAI10);
    }//GEN-LAST:event_btnAI10ActionPerformed

    private void btnAI11ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAI11ActionPerformed
    {//GEN-HEADEREND:event_btnAI11ActionPerformed
        if (start == false && moving == false)
        {
            if (turn.equals(g.getName()))
            {
                generalMove(btnAI11);
                movingButton = btnAI11;
                btnAI11.setBackground(Color.black);
            } else if (turn.equals(a.getName()))
            {
                alienMove(btnAI11);
                movingButton = btnAI11;
                btnAI11.setBackground(Color.black);
            }
        } else if (start == false && moving == true)
        {
            moving(btnAI11);
        }
        startup(btnAI11);
    }//GEN-LAST:event_btnAI11ActionPerformed

    private void btnReinforcementsActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReinforcementsActionPerformed
    {//GEN-HEADEREND:event_btnReinforcementsActionPerformed
        //only available to be clicked once per round. calls available reinforcements to territory being manipulated.
        JOptionPane.showMessageDialog(this, "Reinforcements have arrived!");
        int prev = Integer.parseInt(movingButton.getText());
        if (turn.equals(g.getName()))
        {
            prev += g.getAvailableTroops();
            totalGeneral += g.getAvailableTroops();
            g.setAvailableTroops(0);
            lblTroops.setText("Reinforcements: " + g.getAvailableTroops());
            for (int i = 0; i < generalTerritories.length; i++)
            {
                if (generalTerritories[i] != null)
                {
                    generalTerritories[i].setEnabled(true);
                    if (generalTerritories[i].getText().equals("0"))
                    {
                        generalTerritories[i].setEnabled(false);
                    }
                }
            }
            for (int i = 0; i < unoccupied.length; i++)
            {
                if (unoccupied[i] != null)
                {
                    unoccupied[i].setEnabled(false);
                }
            }
            for (int i = 0; i < alienTerritories.length; i++)
            {
                if (alienTerritories[i] != null)
                {
                    alienTerritories[i].setEnabled(false);
                }
            }
            actions--;
            lblActions.setText("Actions left: " + actions);
            if (actions == 0)
            {
                alienTurn(a);
            }
        } else if (turn.equals(a.getName()))
        {
            roundsWithout = 0;
            prev += a.getAvailableTroops();
            totalAlien += a.getAvailableTroops();
            a.setAvailableTroops(0);
            lblTroops.setText("Reinforcements: " + a.getAvailableTroops());
            for (int i = 0; i < generalTerritories.length; i++)
            {
                if (generalTerritories[i] != null)
                {
                    generalTerritories[i].setEnabled(false);
                }
            }
            for (int i = 0; i < unoccupied.length; i++)
            {
                if (unoccupied[i] != null)
                {
                    unoccupied[i].setEnabled(false);
                }
            }
            for (int i = 0; i < alienTerritories.length; i++)
            {
                if (alienTerritories[i] != null)
                {
                    alienTerritories[i].setEnabled(true);
                    if (alienTerritories[i].getText().equals("0"))
                    {
                        alienTerritories[i].setEnabled(false);
                    }
                }
            }
            actions--;
            lblActions.setText("Actions left: " + actions);
            if (actions == 0)
            {
                generalTurn(g);
            }
        }
        movingButton.setText("" + prev);
        moving = false;
        movingButton.setBackground(null);
        btnReinforcements.setEnabled(false);
        if (totalAlien > maxAlien)
        {
            maxAlien = totalAlien;
        }
        if (totalGeneral > maxGeneral)
        {
            maxGeneral = totalGeneral;
        }
    }//GEN-LAST:event_btnReinforcementsActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnTestActionPerformed
    {//GEN-HEADEREND:event_btnTestActionPerformed
        //for testing purposes
        System.out.println("TotalAlien: " + totalAlien);
        System.out.println("TotalGeneral: " + totalGeneral);
        System.out.println("Rounds without alien backup: " + roundsWithout);
        System.out.println("MaxAlien: " + maxAlien);
        System.out.println("MaxGeneral: " + maxGeneral);
        printAlien();
        printGeneral();
    }//GEN-LAST:event_btnTestActionPerformed

    private void btnDoneGeneralActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoneGeneralActionPerformed
    {//GEN-HEADEREND:event_btnDoneGeneralActionPerformed
        //appears if general has won. checks to see if general's score is a highscore.
        String sql = "SELECT HSGeneral FROM Profiles WHERE Username LIKE '" + g.getName() + "';";
        ResultSet rs = connect.query(sql);
        int score = 0;
        try
        {
            while (rs.next())
            {
                score = rs.getInt("HSGeneral");
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        if (g.getHighScore() > score)
        {
            sql = "UPDATE Profiles SET HSGeneral = '" + g.getHighScore() + "' WHERE Username LIKE '" + g.getName() + "';";
            int update = connect.update(sql);
            JOptionPane.showMessageDialog(this, "New highscore!\nScore: " + g.getHighScore());
        }
        System.exit(0);
    }//GEN-LAST:event_btnDoneGeneralActionPerformed

    private void btnDoneAlienActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnDoneAlienActionPerformed
    {//GEN-HEADEREND:event_btnDoneAlienActionPerformed
        //appears if alien has won. checks to see if alien's score is a highscore.
        String sql = "SELECT HSAlien FROM Profiles WHERE Username LIKE '" + a.getName() + "';";
        ResultSet rs = connect.query(sql);
        int score = 0;
        try
        {
            while (rs.next())
            {
                score = rs.getInt("HSAlien");
            }
        } catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Invalid query.");
        }
        if (a.getHighScore() > score)
        {
            sql = "UPDATE Profiles SET HSAlien = '" + a.getHighScore() + "' WHERE Username LIKE '" + a.getName() + "';";
            int update = connect.update(sql);
            JOptionPane.showMessageDialog(this, "New highscore!\nScore: " + a.getHighScore());
        }
        System.exit(0);
    }//GEN-LAST:event_btnDoneAlienActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Windows".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(AlienInvadersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(AlienInvadersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(AlienInvadersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(AlienInvadersGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new AlienInvadersGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAF1;
    private javax.swing.JButton btnAF2;
    private javax.swing.JButton btnAF3;
    private javax.swing.JButton btnAF4;
    private javax.swing.JButton btnAF5;
    private javax.swing.JButton btnAI1;
    private javax.swing.JButton btnAI10;
    private javax.swing.JButton btnAI11;
    private javax.swing.JButton btnAI2;
    private javax.swing.JButton btnAI3;
    private javax.swing.JButton btnAI4;
    private javax.swing.JButton btnAI5;
    private javax.swing.JButton btnAI6;
    private javax.swing.JButton btnAI7;
    private javax.swing.JButton btnAI8;
    private javax.swing.JButton btnAI9;
    private javax.swing.JButton btnAU1;
    private javax.swing.JButton btnAU2;
    private javax.swing.JButton btnAU3;
    private javax.swing.JButton btnAccountEditor;
    private javax.swing.JButton btnBackAccountEditor;
    private javax.swing.JButton btnBackHelp;
    private javax.swing.JButton btnBackHighscores;
    private javax.swing.JButton btnCancelRegister;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDeleteUser;
    private javax.swing.JButton btnDoneAlien;
    private javax.swing.JButton btnDoneGeneral;
    private javax.swing.JButton btnEU1;
    private javax.swing.JButton btnEU2;
    private javax.swing.JButton btnEU3;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExitPlay;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHighScores;
    private javax.swing.JButton btnLoginAlien;
    private javax.swing.JButton btnLoginGeneral;
    private javax.swing.JButton btnNewUser;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnRegister2;
    private javax.swing.JButton btnReinforcements;
    private javax.swing.JButton btnSA1;
    private javax.swing.JButton btnSA2;
    private javax.swing.JButton btnSA3;
    private javax.swing.JButton btnSA4;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton btnUSA1;
    private javax.swing.JButton btnUSA2;
    private javax.swing.JButton btnUSA3;
    private javax.swing.JButton btnUSA4;
    private javax.swing.JButton btnUSA5;
    private javax.swing.JButton btnUSA6;
    private javax.swing.JButton btnUSA7;
    private javax.swing.JButton btnUSA8;
    private javax.swing.JComboBox<String> cmbManageUser;
    private javax.swing.JFrame frmAccountEditor;
    private javax.swing.JFrame frmAlienLogin;
    private javax.swing.JFrame frmEndAlien;
    private javax.swing.JFrame frmEndGeneral;
    private javax.swing.JFrame frmGeneralLogin;
    private javax.swing.JFrame frmHelp;
    private javax.swing.JFrame frmHighscores;
    private javax.swing.JFrame frmPlay;
    private javax.swing.JFrame frmRegister;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAccountEditorBackground;
    private javax.swing.JLabel lblAccountEditorTitle;
    private javax.swing.JLabel lblActions;
    private javax.swing.JLabel lblAlensHighscores;
    private javax.swing.JLabel lblAlienLoginBackground;
    private javax.swing.JLabel lblAliensInvaded;
    private javax.swing.JLabel lblEarthDefended;
    private javax.swing.JLabel lblEndAlienBackground;
    private javax.swing.JLabel lblEndAlienTitle;
    private javax.swing.JLabel lblEndAlienTitle1;
    private javax.swing.JLabel lblEndGeneralBackground;
    private javax.swing.JLabel lblEndGeneralTitle;
    private javax.swing.JLabel lblEndGeneralTitle1;
    private javax.swing.JLabel lblGeneralLoginBackground;
    private javax.swing.JLabel lblGeneralLoginTitle;
    private javax.swing.JLabel lblGeneralsHighscores;
    private javax.swing.JLabel lblHelpBackground;
    private javax.swing.JLabel lblHelpTitle;
    private javax.swing.JLabel lblHighscoresBackground;
    private javax.swing.JLabel lblHighscoresTitle;
    private javax.swing.JLabel lblMainMenuBackground;
    private javax.swing.JLabel lblMaxTroopsAlien;
    private javax.swing.JLabel lblMaxTroopsAlien1;
    private javax.swing.JLabel lblMaxTroopsGeneral;
    private javax.swing.JLabel lblMaxTroopsGeneral1;
    private javax.swing.JLabel lblRegisterBackground;
    private javax.swing.JLabel lblRegisterPass;
    private javax.swing.JLabel lblRegisterUser;
    private javax.swing.JLabel lblRiskBoard;
    private javax.swing.JLabel lblScoreAlien;
    private javax.swing.JLabel lblScoreAlien1;
    private javax.swing.JLabel lblScoreGeneral;
    private javax.swing.JLabel lblScoreGeneral1;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTroops;
    private javax.swing.JLabel lblTurn;
    private javax.swing.JLabel lblTurnsTaken;
    private javax.swing.JLabel lblTurnsTaken1;
    private javax.swing.JPanel pnlHighscores;
    private javax.swing.JPanel pnlHowToPlay;
    private javax.swing.JPanel pnlProfileManagement;
    private javax.swing.JPasswordField pwfPasswordAlien;
    private javax.swing.JPasswordField pwfPasswordGeneral;
    private javax.swing.JPasswordField pwfRegisterPass;
    private javax.swing.JScrollPane scrHighscores;
    private javax.swing.JScrollPane scrHowToPlay;
    private javax.swing.JScrollPane scrProfileManagement;
    private javax.swing.JTabbedPane tabHelp;
    private javax.swing.JTextArea txaAccounts;
    private javax.swing.JTextArea txaHighscores;
    private javax.swing.JTextArea txaHighscoresAlien;
    private javax.swing.JTextArea txaHighscoresGeneral;
    private javax.swing.JTextArea txaHowToPlay;
    private javax.swing.JTextArea txaProfileManagement;
    private javax.swing.JTextField txfRegisterUser;
    private javax.swing.JTextField txfUsernameAlien;
    private javax.swing.JTextField txfUsernameGeneral;
    // End of variables declaration//GEN-END:variables
}
