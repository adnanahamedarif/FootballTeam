import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Container;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class MyFrame extends JFrame {

    private ArrayList<Team> teams;
    private JTextArea ta;
    private Container c;
    private JTextField searchField;
    private JButton btn1, btn2, btn3, searchButton;
    private Font f;
    private ImageIcon img;
    private JLabel l1;
    private JScrollPane scroll;


    MyFrame()
    {
        initComponents();
    }

    public void initComponents() {
        f = new Font("Arial", Font.BOLD, 15);

        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.cyan);
        img = new ImageIcon(getClass().getResource("img.jpg"));
        l1 = new JLabel(img);
        l1.setBounds(50, 15, img.getIconWidth(), img.getIconHeight());
        c.add(l1);

        teams = new ArrayList<>();

        ta = new JTextArea();
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        ta.setFont(f);

        scroll = new JScrollPane(ta);
        scroll.setBounds(50, 200, 500, 150);
        c.add(scroll);


        // Search text field
        searchField = new JTextField();
        searchField.setBounds(50, 160, 200, 30);
        c.add(searchField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text File Saver");
        setSize(400, 300);
        setLocationRelativeTo(null);


//button
        btn1 = new JButton("Add Team");
        btn1.setBounds(370, 20, 150, 50);
        c.add(btn1);

        btn2 = new JButton("Display Updates");
        btn2.setBounds(370, 75, 150, 50);
        c.add(btn2);

        btn3 = new JButton("Clear");
        btn3.setBounds(370, 130, 150, 50);
        c.add(btn3);

        searchButton = new JButton("Search");
        searchButton.setBounds(260, 160, 100, 30);
        c.add(searchButton);


        // ActionListener

        // Add Team button

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddTeamFrame();
            }
        });
        c.add(btn1);

        // Display Updates button
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayUpdates();
            }
        });
        c.add(btn2);
        //clear button
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ta.setText("");
            }
        });
        c.add(btn3);


        // searchButton
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText();
                searchTeam(searchTerm);
            }
        });
        c.add(searchButton);
    }


        // Method to open frame for adding a new team
        private void openAddTeamFrame () {

            JFrame addTeamFrame = new JFrame("Add Team");
            addTeamFrame.setFont(f);
            addTeamFrame.setBounds(800, 100, 300, 200);
            addTeamFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            addTeamFrame.setLayout(new GridLayout(0, 1));


            JTextField teamNameField = new JTextField();
            teamNameField.setFont(f);
            JButton addPlayerButton = new JButton("Add Player");
            addPlayerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openAddPlayerFrame(addTeamFrame, teamNameField.getText());
                }
            });
            addTeamFrame.add(new JLabel("Team Name:"));
            addTeamFrame.add(teamNameField);
            addTeamFrame.add(addPlayerButton);

            addTeamFrame.setVisible(true);

        }

        // Method to open frame for adding a new player to a team
        private void openAddPlayerFrame (JFrame parentFrame, String teamName){
            JFrame addPlayerFrame = new JFrame("Add Player");
            addPlayerFrame.setBounds(800,300,300,300);
            //addPlayerFrame.setSize(300, 300);

            addPlayerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addPlayerFrame.setLayout(new GridLayout(0, 2));


            JTextField playerNameField = new JTextField();
            playerNameField.setFont(f);
            JTextField goalsScoredField = new JTextField();
            goalsScoredField.setFont(f);
            JTextField goalsConcededField = new JTextField();
            goalsConcededField.setFont(f);

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    savePlayerInfo(teamName, playerNameField.getText(), Integer.parseInt(goalsScoredField.getText()), Integer.parseInt(goalsConcededField.getText()));
                    parentFrame.dispose();
                    addPlayerFrame.dispose();
                }
            });


            addPlayerFrame.add(new JLabel("Player Name:"));
            addPlayerFrame.add(playerNameField);
            addPlayerFrame.add(new JLabel("Goals Scored:"));
            addPlayerFrame.add(goalsScoredField);
            addPlayerFrame.add(new JLabel("Goals Conceded:"));
            addPlayerFrame.add(goalsConcededField);
            addPlayerFrame.add(saveButton);

            addPlayerFrame.setVisible(true);
        }

        // Method to save player information to a team
        private void savePlayerInfo (String teamName, String playerName,int goalsScored, int goalConceded){
            // Check if the team already exists
            boolean teamExists = false;
            for (Team team : teams) {
                if (team.getTeamName().equals(teamName)) {
                    Player player = new Player(playerName, goalsScored, goalConceded);
                    team.addPlayer(player);
                    // btn2.append("Added player " + playerName + " to team " + teamName + "\n");
                    teamExists = true;
                    break;
                }
            }
            // If the team doesn't exist, create a new team
            if (!teamExists) {
                Team newTeam = new Team(teamName);
                Player player = new Player(playerName, goalsScored, goalConceded);
                newTeam.addPlayer(player);
                teams.add(newTeam); // Add the new team to the list of teams
                ta.append("Created team " + teamName + " and added player " + playerName + "\n");
            }
        }
        private void displayUpdates () {
            //btn2.setText(""); // Clear the update area first
            for (Team team : teams) {
                ta.append("Team Name: " + team.getTeamName() + "\n");
                for (Player player : team.getPlayers()) {
                    ta.append("Player Name: " + player.getPlayerName() + ", Goals Scored: " + player.getGoalsScored() + ", Goals Conceded: " + player.getGoalConceded() + "\n");
                }
                ta.append("\n");
            }
        }

        private void searchTeam (String searchTerm){
            ta.setText(""); // Clear existing text
            boolean found = false;
            for (Team team : teams) {
                if (team.getTeamName().equalsIgnoreCase(searchTerm)) {
                    ta.append("Team Name: " + team.getTeamName() + "\n");
                    for (Player player : team.getPlayers()) {
                        ta.append("Player Name: " + player.getPlayerName() + ", Goals Scored: " + player.getGoalsScored() + ", Goals Conceded: " + player.getGoalConceded() + "\n");
                    }
                    ta.append("\n");
                    found = true;
                    break; // Stop searching if team found
                }
            }
            if (!found) {
                ta.append("Team not found!");
            }
        }



    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 100, 600, 400);

    }}


