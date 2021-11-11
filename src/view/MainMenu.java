package view;

import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.Image;

public class MainMenu implements MouseInputListener{
    JFrame fMainMenu;
    JPanel panelMenu, panelMainMenu, panelProfile, panelPesawat, panelKeretaApi, panelBus, panelHotel, panelWisata;
    JButton btnProfile, btnPesawat, btnKeretaApi, btnBus, btnHotel, btnWisata, btnLogOut;

    public MainMenu() {
        fMainMenu = new JFrame("MAIN MENU");
        fMainMenu.setSize(1500, 700);
        fMainMenu.setLayout(null);

        fMainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelMenu = new JPanel();
        panelMenu.setBackground(new Color(23,42,62));
        panelMenu.setBounds(0, 0, 250, 700);
        panelMenu.setLayout(null);

        panelProfile = new JPanel();
        panelProfile.setBackground(Color.WHITE);
        panelProfile.setBounds(250, 0, 1500, 700);
        panelProfile.setLayout(null);

        panelPesawat = new JPanel();
        panelPesawat.setBackground(Color.black);
        panelPesawat.setBounds(250, 0, 1500, 700);
        panelPesawat.setLayout(null);

        panelKeretaApi = new JPanel();
        panelKeretaApi.setBackground(Color.magenta);
        panelKeretaApi.setBounds(250, 0, 1500, 700);
        panelKeretaApi.setLayout(null);

        panelBus = new JPanel();
        panelBus.setBackground(Color.red);
        panelBus.setBounds(250, 0, 1500, 700);
        panelBus.setLayout(null);

        panelHotel = new JPanel();
        panelHotel.setBackground(Color.ORANGE);
        panelHotel.setBounds(250, 0, 1500, 700);
        panelHotel.setLayout(null);

        panelWisata = new JPanel();
        panelWisata.setBackground(Color.pink);
        panelWisata.setBounds(250, 0, 1500, 700);
        panelWisata.setLayout(null);

        panelMainMenu = new JPanel();
        panelMainMenu.setBackground(new Color(32,59,87));
        panelMainMenu.setBounds(250, 0, 1500, 700);
        panelMainMenu.setLayout(null);

        ImageIcon logo = new ImageIcon("src\\source\\Logo_Splashscreen.png");
        Image scaleImage = logo.getImage().getScaledInstance(150, 150,Image.SCALE_SMOOTH);
        
        btnProfile = new JButton();
        btnProfile.setText("NAMA");
        btnProfile.setHorizontalTextPosition(JButton.CENTER);
        btnProfile.setVerticalTextPosition(JButton.BOTTOM);
        btnProfile.setIcon(new ImageIcon(scaleImage));
        btnProfile.setBounds(10, 10, 230, 230);
        btnProfile.setBackground(new Color(23,42,62));
        btnProfile.setForeground(Color.white);
        btnProfile.addMouseListener(this);

        btnPesawat = new JButton("Pesawat");
        btnPesawat.setBounds(10, 245, 230, 50);
        btnPesawat.setBackground(new Color(23,42,62));
        btnPesawat.setForeground(Color.white);
        btnPesawat.addMouseListener(this);

        btnKeretaApi = new JButton("Kereta Api");
        btnKeretaApi.setBounds(10, 300, 230, 50);
        btnKeretaApi.setBackground(new Color(23,42,62));
        btnKeretaApi.setForeground(Color.white);
        btnKeretaApi.addMouseListener(this);

        btnBus = new JButton("Bus");
        btnBus.setBounds(10, 355, 230, 50);
        btnBus.setBackground(new Color(23,42,62));
        btnBus.setForeground(Color.white);
        btnBus.addMouseListener(this);

        btnHotel = new JButton("Hotel");
        btnHotel.setBounds(10, 410, 230, 50);
        btnHotel.setBackground(new Color(23,42,62));
        btnHotel.setForeground(Color.white);
        btnHotel.addMouseListener(this);

        btnWisata = new JButton("Wisata");
        btnWisata.setBounds(10, 465, 230, 50);
        btnWisata.setBackground(new Color(23,42,62));
        btnWisata.setForeground(Color.white);
        btnWisata.addMouseListener(this);

        btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(10, 600, 230, 50);
        btnLogOut.setForeground(Color.white);
        btnLogOut.setBackground(Color.red);

        panelMenu.add(btnLogOut);
        panelMenu.add(btnWisata);
        panelMenu.add(btnHotel);
        panelMenu.add(btnBus);
        panelMenu.add(btnKeretaApi);
        panelMenu.add(btnPesawat);
        panelMenu.add(btnProfile);
        fMainMenu.add(panelMainMenu);
        fMainMenu.add(panelMenu);
        fMainMenu.add(panelBus);
        fMainMenu.add(panelHotel);
        fMainMenu.add(panelKeretaApi);
        fMainMenu.add(panelPesawat);
        fMainMenu.add(panelProfile);
        fMainMenu.add(panelWisata);

        fMainMenu.setVisible(true);
        panelMainMenu.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = e.getComponent();
        component.setBackground(new Color (32,59,87));
        JButton button = (JButton) e.getSource();
        String name = button.getText();
        switch (name) {
        case "Profile":
            panelMainMenu.setVisible(false);
            panelProfile.setVisible(true);
            panelPesawat.setVisible(false);
            panelKeretaApi.setVisible(false);
            panelBus.setVisible(false);
            panelHotel.setVisible(false);
            panelWisata.setVisible(false);

            break;
        case "Pesawat":
            panelMainMenu.setVisible(false);
            panelProfile.setVisible(false);
            panelPesawat.setVisible(true);
            panelKeretaApi.setVisible(false);
            panelBus.setVisible(false);
            panelHotel.setVisible(false);
            panelWisata.setVisible(false);
            break;
        case "Kereta Api":
            panelMainMenu.setVisible(false);
            panelProfile.setVisible(false);
            panelPesawat.setVisible(false);
            panelKeretaApi.setVisible(true);
            panelBus.setVisible(false);
            panelHotel.setVisible(false);
            panelWisata.setVisible(false);
            break;
        case "Bus":
            panelMainMenu.setVisible(false);
            panelProfile.setVisible(false);
            panelPesawat.setVisible(false);
            panelKeretaApi.setVisible(false);
            panelBus.setVisible(true);
            panelHotel.setVisible(false);
            panelWisata.setVisible(false);
            break;
        case "Hotel":
            panelMainMenu.setVisible(false);
            panelProfile.setVisible(false);
            panelPesawat.setVisible(false);
            panelKeretaApi.setVisible(false);
            panelBus.setVisible(false);
            panelHotel.setVisible(true);
            panelWisata.setVisible(false);
            break;
        case "Wisata":
            panelMainMenu.setVisible(false);
            panelProfile.setVisible(false);
            panelPesawat.setVisible(false);
            panelKeretaApi.setVisible(false);
            panelBus.setVisible(false);
            panelHotel.setVisible(false);
            panelWisata.setVisible(true);
            break;
        default:
            System.out.println("TEST BUTTON ELSE");
            break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Component component = e.getComponent();
        component.setBackground(new Color(57,189,118));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Component component = e.getComponent();
        component.setBackground(new Color(23,42,62));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
