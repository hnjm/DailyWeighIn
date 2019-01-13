import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class GUI {

	private JFrame frame;
	private JTextField txtWeighIn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 812, 962);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtWeighIn = new JTextField();
		txtWeighIn.setBounds(228, 40, 130, 26);
		frame.getContentPane().add(txtWeighIn);
		txtWeighIn.setColumns(10);
		txtWeighIn.setHorizontalAlignment(JTextField.RIGHT);
		
		JLabel lblWhatDoYou = new JLabel("What do you weigh today?");
		lblWhatDoYou.setBounds(228, 11, 322, 16);
		frame.getContentPane().add(lblWhatDoYou);
		lblWhatDoYou.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblKg = new JLabel("kg");
		lblKg.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblKg.setBounds(360, 45, 61, 16);
		frame.getContentPane().add(lblKg);
		
		JLabel lbl7 = new JLabel("Future Graph 7");
		lbl7.setBackground(Color.WHITE);
		lbl7.setBounds(60, 72, 660, 276);
		frame.getContentPane().add(lbl7);
		
		//Adding graph
		BufferedImage Seven = null;
		try {
			Seven = ImageIO.read(new File("Graph7.png"));
			
			Image ScaledSeven = Seven.getScaledInstance(lbl7.getWidth(), lbl7.getHeight(), Image.SCALE_SMOOTH);
			
			ImageIcon Graph7 = new ImageIcon(ScaledSeven);
			
			lbl7.setIcon(Graph7);
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Graph7.png does not exist");
		}
		
		
		JLabel lbl28 = new JLabel("Future Graph 28");
		lbl28.setBackground(Color.LIGHT_GRAY);
		lbl28.setBounds(60, 359, 660, 276);
		frame.getContentPane().add(lbl28);
		
		//Adding graph
		BufferedImage TwentyEight = null;
		try {
			TwentyEight = ImageIO.read(new File("Graph28.png"));
			
			Image ScaledTwentyEight = TwentyEight.getScaledInstance(lbl28.getWidth(), lbl28.getHeight(), Image.SCALE_SMOOTH);
			
			ImageIcon Graph28 = new ImageIcon(ScaledTwentyEight);
			
			lbl28.setIcon(Graph28);
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Graph28.png does not exist");
		}

		JLabel lbl365 = new JLabel("Future Graph 365");
		lbl365.setBackground(Color.WHITE);
		lbl365.setBounds(60, 646, 660, 276);
		frame.getContentPane().add(lbl365);
		
		//Adding graph
		BufferedImage ThreeSixFive = null;
		try {
			ThreeSixFive = ImageIO.read(new File("Graph365.png"));
			
			Image ScaledThreeSixFive = ThreeSixFive.getScaledInstance(lbl365.getWidth(), lbl365.getHeight(), Image.SCALE_SMOOTH);
			
			ImageIcon Graph365 = new ImageIcon(ScaledThreeSixFive);
			
			lbl365.setIcon(Graph365);
		}
		catch(IOException e) {
			//e.printStackTrace();
			System.out.println("Graph365.png does not exist");
		}
		
		
		
		JButton btnPush = new JButton("Push");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Validate that the textbox is not empty, and has valid input
				try {
					double CurrentWeight = Double.parseDouble(txtWeighIn.getText());

					System.out.println("New weight: " + CurrentWeight);
					
					//Awesome, you've today's weigh-in value result. Now to put it into the .csv file
					//But wait!
					//Does the .csv file exist yet?
					
					File CSV = new File(System.getProperty("user.dir") + "/WeighIns.csv");
					
					if(CSV.exists() == false) {
						//Creates the .csv file in the current directory
						FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/WeighIns.csv");
						
			            //Add the current weight to the csv file
			            fileWriter.append(Double.toString(CurrentWeight) + "\n");
			            fileWriter.flush();
			            fileWriter.close();
					}
					else {
						//Create an ArrayList to hold the variables
						ArrayList<String> WeighIns=new ArrayList<String>();
						
						//String for each line
						String line = "";
						
						BufferedReader br = new BufferedReader(new FileReader(CSV));
						while ((line = br.readLine()) != null) {
							WeighIns.add(line);
						}
						
						//Add the CurrentWeight to the ArrayList too
						WeighIns.add(Double.toString(CurrentWeight));
						
						//Cool, now you have all of the data in the ArrayList.
						//We can now delete the .csv file
						CSV.delete();
						
						//We now need to check if there are less than 366 entries.
						//If there is a 366th entry, we must delete the first entry before recreating the file 
						System.out.println("WeighIns.size(): " + WeighIns.size());
						
						if(WeighIns.size() == 366) {
							WeighIns.remove(0);
						}
						
						//Once we are down to 365 values or less, we can recreate the .csv file from the ArrayList
						FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/WeighIns.csv");
						
			            for(int i = 0; i < WeighIns.size(); i++) {
			            	fileWriter.append(WeighIns.get(i) + "\n");
			            }
						
			            fileWriter.flush();
			            fileWriter.close();
						
						
						//Now the file has been created/updated, it's time to let Python create the graphs!
						//But first, let's delete the current graphs so that we can loop until new ones are
						//created, and update the display when the scripts complete
						File Graph7PNG = new File(System.getProperty("user.dir") + "/Graph7.png");
						File Graph28PNG = new File(System.getProperty("user.dir") + "/Graph28.png");
						File Graph365PNG = new File(System.getProperty("user.dir") + "/Graph365.png");
						
						if(Graph7PNG.delete()) {
							System.out.println("Graph7.png deleted");
						}
						else {
							System.out.println("Failed to delete Graph7.png");
						}
						
						if(Graph28PNG.delete()) {
							System.out.println("Graph28.png deleted");
						}
						else {
							System.out.println("Failed to delete Graph28.png");
						}
						
						if(Graph365PNG.delete()) {
							System.out.println("Graph365.png deleted");
						}
						else {
							System.out.println("Failed to delete Graph365.png");
						}
						
						
						// set up the command and parameter
						String[] cmd = new String[2];
						cmd[0] = "python"; // check version of installed python: python -V
						cmd[1] = "Plot7.py";
						 
						// create runtime to execute external command
						Runtime rt = Runtime.getRuntime();
						try {
							Process pr = rt.exec(cmd);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//Now that the first graphing script has been called, I want to wait until it is complete
						//before starting the next. 
						
						while(Graph7PNG.exists() == false)
						{
							//Do nothing!
						}
						// set up the command and parameter
						cmd[0] = "python"; // check version of installed python: python -V
						cmd[1] = "Plot28.py";
						 
						// create runtime to execute external command
						//rt = Runtime.getRuntime();
						try {
							Process pr = rt.exec(cmd);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						//Again, the graphing script has been called and I want to wait until it is complete
						//before starting the final graph
						
						while(Graph28PNG.exists() == false)
						{
							//Do nothing!
						}
						
						// set up the command and parameter
						cmd[0] = "python"; // check version of installed python: python -V
						cmd[1] = "Plot365.py";
						 
						// create runtime to execute external command
						//Runtime rt = Runtime.getRuntime();
						try {
							Process pr = rt.exec(cmd);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						//check if the graph .png files exist yet - and if not, keep trying!
						boolean GraphsUpdated = false;
						
						while(GraphsUpdated == false) {
							try {
								//Updating graph
								BufferedImage Seven = null;
								Seven = ImageIO.read(new File("Graph7.png"));
								Image ScaledSeven = Seven.getScaledInstance(lbl7.getWidth(), lbl7.getHeight(), Image.SCALE_SMOOTH);
								ImageIcon Graph7 = new ImageIcon(ScaledSeven);
								lbl7.setIcon(Graph7);
								
								//Updating graph
								BufferedImage TwentyEight = null;
								TwentyEight = ImageIO.read(new File("Graph28.png"));
								Image ScaledTwentyEight = TwentyEight.getScaledInstance(lbl28.getWidth(), lbl28.getHeight(), Image.SCALE_SMOOTH);
								ImageIcon Graph28 = new ImageIcon(ScaledTwentyEight);
								lbl28.setIcon(Graph28);
	
								//Updating graph
								BufferedImage ThreeSixFive = null;
								ThreeSixFive = ImageIO.read(new File("Graph365.png"));
								Image ScaledThreeSixFive = ThreeSixFive.getScaledInstance(lbl365.getWidth(), lbl365.getHeight(), Image.SCALE_SMOOTH);
								ImageIcon Graph365 = new ImageIcon(ScaledThreeSixFive);
								lbl365.setIcon(Graph365);
								
								//If you get this far in the try without being caught up in the catch clause, we're done!
								
								//Clear the inputbox 
								txtWeighIn.setText(null);
								
								System.out.println("Graphs updated!");
								GraphsUpdated = true;
								
								//Time to commit to Git and then update x4iiiis.com, all via Python!
								//To do so, we run the command 'python CommitToGit.py' followed by the current weight variable (converted to a string),
								//And the current directory that we are in so that the script can navigate to the working directory
								String[] cmd2 = new String[4];
								cmd2[0] = "python"; 
								cmd2[1] = "CommitToGit.py";
								cmd2[2] = Double.toString(CurrentWeight);
								cmd2[3] = System.getProperty("user.dir");
								
								try {
									Process pr = rt.exec(cmd2);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
							catch(Exception PythonNotFinishedOrImageDoesNotExist) {
								//System.out.println("GraphsUpdated = false...");
								GraphsUpdated = false;
							}
						}
					}
				}
				catch(Exception Ex) {
					txtWeighIn.grabFocus();
					JOptionPane.showMessageDialog(null, "Please enter a numerical value for your current weight.");
				}
			}
		});
		btnPush.setBounds(433, 40, 117, 29);
		frame.getContentPane().add(btnPush);
	}
}
