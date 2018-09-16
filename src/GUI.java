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
		frame.setBounds(100, 100, 450, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnPush = new JButton("Push");
		btnPush.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Validate that the textbox is not empty, and has valid input
				try
				{
					double CurrentWeight = Double.parseDouble(txtWeighIn.getText());

					
					System.out.println(CurrentWeight);
					
					//Awesome, you've today's weigh-in value result. Now to put it into the .csv file
					//But wait!
					//Does the .csv file exist yet?
					
					File CSV = new File(System.getProperty("user.dir") + "/WeighIns.csv");
					
					if(CSV.exists() == false)
					{
						//Creates the .csv file in the current directory
						FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/WeighIns.csv");
						
			            //Add the current weight to the csv file
			            fileWriter.append(Double.toString(CurrentWeight) + "\n");
			            fileWriter.flush();
			            fileWriter.close();
					}
					else
					{
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
						System.out.println(WeighIns.size());
						
						if(WeighIns.size() == 366)
						{
							WeighIns.remove(0);
						}
						
						//Once we are down to 365 values or less, we can recreate the .csv file from the ArrayList
						FileWriter fileWriter = new FileWriter(System.getProperty("user.dir") + "/WeighIns.csv");
						
			            for(int i = 0; i < WeighIns.size(); i++)
			            {
			            	fileWriter.append(WeighIns.get(i) + "\n");
			            }
						
			            fileWriter.flush();
			            fileWriter.close();
						
						
						//Now the file has been created/updated, it's time to let Python create the graphs!
						
						//Perhaps we could have 3 python scripts, all doing the same thing but only running the 7 one if we don't
						//have 28 values in the WeighIns ArrayList..?
						//Alternatively the script could just take in an argument to symbolise which graphs to make
						
						//Python.run... 
			          //Debugging - this is the correct path to the python file, just dunno how to run it
						String pypath = System.getProperty("user.dir");// + "/Plot.py";
						
						System.out.println(System.getProperty("user.dir") + "/Plot.py");
						
						
						
						
						// set up the command and parameter
						//String pythonScriptPath = "/home/norbert/python/helloPython.py";
						String[] cmd = new String[2];
						cmd[0] = "python"; // check version of installed python: python -V
						cmd[1] = "Plot.py";
						 
						// create runtime to execute external command
						Runtime rt = Runtime.getRuntime();
						try {
							Process pr = rt.exec(cmd);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						//Put Java to sleep while Python works its magic
						Thread.sleep(5000);
						
						
						//Python Pseudocode...
						/*
						if (7, 28 and 365.png exist)
						{
							delete them
						}
						
						count data to see if there is enough for each of the graph lengths
						
						proceed accordingly
						*/
						
						//After your snooze, check if the graph .png files exist yet, and if not, keep trying!
				GetGraphs:
						try
						{
							//lbl7 image = 7.png;
							//lbl28 image = 28.png;
							//lbl 365 image = 365.png;
							
							//Temporarily just exiting here as the graph stuff does not yet exist
							System.exit(0);
						}
						catch(Exception PythonNotFinishedOrImageDoesNotExist)
						{
							break GetGraphs;
						}
					}
				}
				catch(Exception Ex)
				{
					txtWeighIn.grabFocus();
					JOptionPane.showMessageDialog(null, "Please enter a numerical value for your current weight.");
				}
			}
		});
		btnPush.setBounds(265, 68, 117, 29);
		frame.getContentPane().add(btnPush);
		
		txtWeighIn = new JTextField();
		txtWeighIn.setBounds(60, 68, 130, 26);
		frame.getContentPane().add(txtWeighIn);
		txtWeighIn.setColumns(10);
		txtWeighIn.setHorizontalAlignment(JTextField.RIGHT);

		
		JLabel lblWhatDoYou = new JLabel("What do you weigh today?");
		lblWhatDoYou.setBounds(60, 39, 322, 16);
		frame.getContentPane().add(lblWhatDoYou);
		lblWhatDoYou.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblKg = new JLabel("kg");
		lblKg.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblKg.setBounds(192, 73, 61, 16);
		frame.getContentPane().add(lblKg);
		
		JLabel lbl7 = new JLabel("Future Graph 7");
		lbl7.setBackground(Color.WHITE);
		lbl7.setBounds(60, 109, 322, 135);
		frame.getContentPane().add(lbl7);
		
		//Adding graph
		BufferedImage Seven = null;
		try {
			Seven = ImageIO.read(new File("Graph7.png"));
			
			Image ScaledSeven = Seven.getScaledInstance(lbl7.getWidth(), lbl7.getHeight(), Image.SCALE_SMOOTH);
			
			ImageIcon Graph7 = new ImageIcon(ScaledSeven);
			
			lbl7.setIcon(Graph7);
		}
		catch(IOException e)
		{
			//e.printStackTrace();
			System.out.println("Graph7.png does not exist");
		}
		
		
		
		
		JLabel lbl28 = new JLabel("Future Graph 28");
		lbl28.setBackground(Color.LIGHT_GRAY);
		lbl28.setBounds(60, 249, 322, 135);
		frame.getContentPane().add(lbl28);
		
		//Adding graph
		BufferedImage TwentyEight = null;
		try {
			TwentyEight = ImageIO.read(new File("Graph28.png"));
			
			Image ScaledTwentyEight = TwentyEight.getScaledInstance(lbl28.getWidth(), lbl28.getHeight(), Image.SCALE_SMOOTH);
			
			ImageIcon Graph28 = new ImageIcon(ScaledTwentyEight);
			
			lbl28.setIcon(Graph28);
		}
		catch(IOException e)
		{
			//e.printStackTrace();
			System.out.println("Graph28.png does not exist");
		}

		JLabel lbl365 = new JLabel("Future Graph 365");
		lbl365.setBackground(Color.WHITE);
		lbl365.setBounds(60, 396, 322, 135);
		frame.getContentPane().add(lbl365);
		
		BufferedImage ThreeSixFive = null;
		try {
			ThreeSixFive = ImageIO.read(new File("Graph365.png"));
			
			Image ScaledThreeSixFive = ThreeSixFive.getScaledInstance(lbl365.getWidth(), lbl365.getHeight(), Image.SCALE_SMOOTH);
			
			ImageIcon Graph365 = new ImageIcon(ScaledThreeSixFive);
			
			lbl365.setIcon(Graph365);
		}
		catch(IOException e)
		{
			//e.printStackTrace();
			System.out.println("Graph365.png does not exist");
		}

	}
}
