import javax.swing.*;

public class UserView extends JFrame{
    private JTextField txtUserName  = new JTextField(30);
    private JTextField txtUserJob  = new JTextField(30);
    private JTextField txtUserPass  = new JTextField(30);


    private JButton btnLoad = new JButton("Load User");
    private JButton btnSave = new JButton("Save User");

    public UserView() {
        this.setTitle("User View");
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        this.setSize(500, 200);

        JPanel panelButton = new JPanel();
        panelButton.add(btnLoad);
        panelButton.add(btnSave);
        this.getContentPane().add(panelButton);

        JPanel panelUserName = new JPanel();
        panelUserName.add(new JLabel("Name: "));
        panelUserName.add(txtUserName);
        this.getContentPane().add(panelUserName);

        JPanel panelUserPass = new JPanel();
        panelUserPass.add(new JLabel("Password: "));
        panelUserPass.add(txtUserPass);
        this.getContentPane().add(panelUserPass);

        JPanel panelUserJob = new JPanel();
        panelUserJob.add(new JLabel("Job: "));
        panelUserJob.add(txtUserJob);
        this.getContentPane().add(panelUserJob);

    }

    public JButton getBtnLoad() {
        return btnLoad;
    }

    public JButton getBtnSave() {
        return btnSave;
    }
    
    public JTextField getTxtUserName() {
        return txtUserName;
    }

    public JTextField getTxtUserPass() {
        return txtUserPass;
    }

    public JTextField getTxtUserJob() {
        return txtUserJob;
    }
}
