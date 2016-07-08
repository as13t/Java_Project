/*http://kodingtutorials.weebly.com/tic-tac-toe-gui.html
* The above link is the location that the code was provided from.
* I added 3 icons the Gator, Nole, and blank. I have also added the title picture
* and also the footer with my name. I have also added a check to see if there was a tie 
* in the game. There is also now a check to see if the selected cell has already been occupied.
* If so then a dialogue box will tell the user to choose another cell.
*/
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JPanel
{
	Icon noleIcon = new ImageIcon("src/nole.jpg");
	Icon gatorIcon = new ImageIcon("src/gator.jpg");
	Icon blankIcon = new ImageIcon("src/blank.jpg");
	
    JButton buttons[] = new JButton[9]; 
    int alternate = 0;//if this number is a even, then put a X. If it's odd, then put an O
    
    public TicTacToe()
    {
    	setLayout(new GridLayout(3,3));
    	initializebuttons(); 
    }
    
    public void initializebuttons()
    {
    	for(int i = 0; i <= 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setIcon(blankIcon);
            buttons[i].addActionListener(new buttonListener());
            
            add(buttons[i]); //adds this button to JPanel (note: no need for JPanel.add(...)
                                //because this whole class is a JPanel already           
        }
    }
    
    public void resetButtons()
    {
        for(int i = 0; i <= 8; i++)
        {
            buttons[i].setIcon(blankIcon);
        }
    }
    
// when a button is clicked, it generates an ActionEvent. Thus, each button needs an ActionListener. When it is clicked, it goes to this listener class that I have created and goes to the actionPerformed method. There (and in this class), we decide what we want to do.
    private class buttonListener implements ActionListener
    {
       
        public void actionPerformed(ActionEvent e) 
        {        
            JButton buttonClicked = (JButton)e.getSource(); //get the particular button that was clicked
            if(!buttonClicked.getIcon().toString().equals("src/blank.jpg"))
            {
            	JOptionPane.showMessageDialog(null, "Please choose a different cell.");
            }
            else
            {
            	if(alternate%2 == 0)
            		buttonClicked.setIcon(noleIcon);
            	else
            		buttonClicked.setIcon(gatorIcon);
            
            	if(checkForWin() == true)
            	{
            		JOptionPane.showConfirmDialog(null, "Game Over.");
            		resetButtons();
            	}
            	else if(checkTie())
            	{
            		JOptionPane.showConfirmDialog(null, "Tie Game. Would you like to play again?");
            		resetButtons();
            	}
                
            	alternate++;
            }
        }
        
        public boolean checkForWin()
        {
            /**   Reference: the button array is arranged like this as the board
             *      0 | 1 | 2
             *      3 | 4 | 5
             *      6 | 7 | 8
             */
            //horizontal win check
            if( checkAdjacent(0,1) && checkAdjacent(1,2) ) //no need to put " == true" because the default check is for true
                return true;
            else if( checkAdjacent(3,4) && checkAdjacent(4,5) )
                return true;
            else if ( checkAdjacent(6,7) && checkAdjacent(7,8))
                return true;
            
            //vertical win check
            else if ( checkAdjacent(0,3) && checkAdjacent(3,6))
                return true;  
            else if ( checkAdjacent(1,4) && checkAdjacent(4,7))
                return true;
            else if ( checkAdjacent(2,5) && checkAdjacent(5,8))
                return true;
            
            //diagonal win check
            else if ( checkAdjacent(0,4) && checkAdjacent(4,8))
                return true;  
            else if ( checkAdjacent(2,4) && checkAdjacent(4,6))
                return true;
            else 
                return false;
        }
        
        public boolean checkAdjacent(int a, int b)
        {
            if (buttons[a].getIcon().toString().equals(buttons[b].getIcon().toString()) && !buttons[a].getIcon().toString().equals("src/blank.jpg"))
                return true;
            else
                return false;
        }
        
        public boolean checkTie()
        {
        	for(int i = 0; i <= 8; i++)
        	{
        		if(buttons[i].getIcon().toString().equals("src/blank.jpg"))
        		{
        			return false;
        		}
        	}	
        	return true;
        }
    }
    
    public static void main(String[] args) 
    {
        JFrame window = new JFrame("Tic-Tac-Toe");
        JLabel headerLabel = new JLabel(new ImageIcon("src/fsuLogo8.png"));
        JLabel footerLabel = new JLabel("By: Arron Solano");
        window.add(headerLabel, BorderLayout.NORTH);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().add(new TicTacToe());
        window.add(footerLabel, BorderLayout.SOUTH);
        window.setBounds(600,500,600,600);
        window.setVisible(true);
    }
}