package projekat_dom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;

public class DomGUI {

	private JFrame frame;
	private JTextField textField_user;
	private JTextField textField_pass;
	JPanel login_o=null;
	JPanel panelup=null;
	JPanel panelss=null;
	JPanel panelup2=null;
	private JTable tabelaSt;
	private ArrayList<Student> lista_st_ss=null;
	private ArrayList<Student> lista_st_up=null;
	private StudentiTableModel1 model_tabela_ss=null;
	private StudentiTableModel2 model_tabela_up1=null;
	private DAO daoss=new DAO();
	private boolean viewmarkerss=false;
	private JTable tableSta1;
	private ArrayList<Soba> lista_soba_m=null;
	private ArrayList<Soba> lista_soba_z=null;
	private int kapacitet=0;
	private int useljeno=0;
	private JTable tableEvid;
	private ArrayList<StudentSoba> lista_evidencija=null;
	private StudentiTableModel3 model_evidencija=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DomGUI window = new DomGUI();
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
	public DomGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		try {
			lista_st_ss=daoss.vratiStudente();
			lista_st_up=daoss.vratiStudentePoSkoru();
			kapacitet=daoss.vratiKapacitet();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "PUKLA BAZA!");
			e1.printStackTrace();
		}
		model_tabela_ss=new StudentiTableModel1(lista_st_ss);
		model_tabela_up1=new StudentiTableModel2(lista_st_up);

		frame = new JFrame();
		frame.setBounds(100, 100, 896, 341);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		login_o = new JPanel();
		frame.getContentPane().add(login_o, "name_42158474012000");
		login_o.setLayout(null);

		JLabel l_naslovna = new JLabel("STUDENTSKI CENTAR");
		l_naslovna.setHorizontalAlignment(SwingConstants.CENTER);
		l_naslovna.setBounds(139, 10, 133, 28);
		login_o.add(l_naslovna);

		textField_user = new JTextField();
		textField_user.setBounds(165, 69, 96, 19);
		login_o.add(textField_user);
		textField_user.setColumns(10);

		textField_pass = new JTextField();
		textField_pass.setBounds(165, 118, 96, 19);
		login_o.add(textField_pass);
		textField_pass.setColumns(10);

		JLabel l_user = new JLabel("Username:");
		l_user.setBounds(165, 47, 68, 13);
		login_o.add(l_user);

		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(165, 98, 68, 13);
		login_o.add(lblNewLabel);

		JButton btnlogin = new JButton("Uloguj se");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_user.getText().length()>0 && textField_pass.getText().length()>0) {
					String user=textField_user.getText();
					String pass=textField_pass.getText();
					if(user.equals("upravnik") && pass.equals("upravnik123")) {
						JOptionPane.showMessageDialog(null, "Ulogovani ste kao upravnik!");
						clearTxtField(textField_user);
						clearTxtField(textField_pass);
						visibility(login_o, panelup);
					}else {
						if(user.equals("saradnik") && pass.equals("saradnik123")) {
							JOptionPane.showMessageDialog(null, "Ulogovani ste kao saradnik!");
							clearTxtField(textField_user);
							clearTxtField(textField_pass);
							visibility(login_o, panelss);
						}else {
							JOptionPane.showMessageDialog(null, "Uneli ste pogresne podatke!");
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Greska, morate popuniti sva polja!");
				}
			}
		});
		btnlogin.setBounds(165, 147, 96, 21);
		login_o.add(btnlogin);

		panelss = new JPanel();
		frame.getContentPane().add(panelss, "name_45891050505400");
		panelss.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 28, 534, 670);
		panelss.add(scrollPane);

		tabelaSt = new JTable();
		tabelaSt.setModel(model_tabela_ss);
		scrollPane.setViewportView(tabelaSt);

		JButton btnSetSkor = new JButton("Izracunaj skor");
		btnSetSkor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Student pom:lista_st_ss) {
					try {
						daoss.obracunajSkor(pom);
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "Greska pri racunanju skora!");
						e1.printStackTrace();
					}
				}
				try {
					viewRefresh_ss();
					JOptionPane.showMessageDialog(null, "Izracunavanje uspesno!");
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Greska klase pri osvezavanju podataka!");
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Greska baze pri osvezavanju podataka!");
					e1.printStackTrace();
				}
			}
		});
		btnSetSkor.setBounds(574, 28, 116, 21);
		panelss.add(btnSetSkor);

		JButton btnRstSkor = new JButton("Resetuj skor");
		btnRstSkor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Student pom:lista_st_ss) {
					try {
						daoss.resetujSkor(pom);
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "Greska pri racunanju skora!");
						e1.printStackTrace();
					}
				}
				try {
					viewRefresh_ss();
					JOptionPane.showMessageDialog(null, "Resetovanje uspesno!");
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Greska klase pri osvezavanju podataka!");
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Greska baze pri osvezavanju podataka!");
					e1.printStackTrace();
				}
			}
		});
		btnRstSkor.setBounds(574, 59, 116, 21);
		panelss.add(btnRstSkor);

		JButton btnRang = new JButton("Rang lista");
		btnRang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewmarkerss=true;
				try {
					lista_st_ss=daoss.vratiStudentePoSkoru();
					model_tabela_ss=new StudentiTableModel1(lista_st_ss);
					viewRefresh_up1();
					tabelaSt.setModel(model_tabela_ss);
					JOptionPane.showMessageDialog(null, "Rangiranje uspesno!");
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "Greska pri kreiranju rang liste!");
					e1.printStackTrace();
				}
			}
		});
		btnRang.setBounds(574, 90, 116, 21);
		panelss.add(btnRang);

		JButton btnRefresh = new JButton("Osvezi listu");
		btnRefresh.setMnemonic(245);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(viewmarkerss) {
					try {
						lista_st_ss=daoss.vratiStudentePoSkoru();
						model_tabela_ss=new StudentiTableModel1(lista_st_ss);
						tabelaSt.setModel(model_tabela_ss);
						JOptionPane.showMessageDialog(null, "Uspesno!");
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, "Greska pri osvezavanju podataka!");
						e1.printStackTrace();
					}
				}else {
					try {
						viewRefresh_ss();
						JOptionPane.showMessageDialog(null, "Uspesno!");
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Greska klase pri osvezavanju podataka!");
						e1.printStackTrace();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Greska baze pri osvezavanju podataka!");
						e1.printStackTrace();
					}
				}
			}
		});
		btnRefresh.setBounds(574, 273, 116, 21);
		panelss.add(btnRefresh);

		JLabel lblNewLabel_1 = new JLabel("Ulogovani ste kao:");
		lblNewLabel_1.setBounds(768, 224, 114, 15);
		panelss.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Saradnik za smestaj");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setBounds(768, 249, 114, 13);
		panelss.add(lblNewLabel_3);

		JButton btnLogOut1 = new JButton("Izloguj se");
		btnLogOut1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelss, login_o);
			}
		});
		btnLogOut1.setBounds(768, 273, 104, 21);
		panelss.add(btnLogOut1);
		
		JLabel lblKonkurisali = new JLabel("Rang lista:");
		lblKonkurisali.setBounds(30, 10, 67, 21);
		panelss.add(lblKonkurisali);

		panelup = new JPanel();
		frame.getContentPane().add(panelup, "name_45966426503600");
		panelup.setLayout(null);

		JLabel lblNewLabel_2 = new JLabel("Ulogovani ste kao:");
		lblNewLabel_2.setBounds(764, 230, 108, 21);
		panelup.add(lblNewLabel_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 43, 340, 251);
		panelup.add(scrollPane_1);
		
		tableSta1 = new JTable();
		tableSta1.setFillsViewportHeight(true);
		tableSta1.setModel(model_tabela_up1);
		scrollPane_1.setViewportView(tableSta1);
		
		JButton btnUseli = new JButton("Useli studente");
		btnUseli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				useljeno=0;
				ArrayList<Soba> dostupne=null;
				try {
					lista_st_up=daoss.vratiStudentePoSkoru();
					lista_soba_m=daoss.vratiSobeM();
					lista_soba_z=daoss.vratiSobeZ();
					for(Student stu:lista_st_up) {
						if(stu.getSkor()<84.5) continue;
						if(stu.getPol().equals(gender.M)) dostupne=lista_soba_m;
						else dostupne=lista_soba_z;
						for(int i=0;i<dostupne.size();i++) {
							if(dostupne.get(i).isEmpty() && stu.getBr_sobe()==0) {
								daoss.dodeliSobu(stu, dostupne.get(i));
								dostupne.get(i).populate();
								useljeno++;
								break;
							}
						}
					}
					JOptionPane.showMessageDialog(null, "Useljenje zavrseno, useljenih studenata: "+useljeno);
					viewRefresh_up1();
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null,"Greska pri preuzimanju podataka iz baze!");
					e1.printStackTrace();
				}
			}
		});
		btnUseli.setBounds(360, 129, 121, 21);
		panelup.add(btnUseli);
		
		JButton btnEvidencija = new JButton("Evidencija");
		btnEvidencija.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					lista_evidencija=daoss.vratiEvidenciju();
					model_evidencija=new StudentiTableModel3(lista_evidencija);
					tableEvid.setModel(model_evidencija);
					visibility(panelup, panelup2);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null,"Greska pri preuzimanju podataka iz baze!");
					e1.printStackTrace();
				}
			}
		});
		btnEvidencija.setBounds(360, 160, 121, 21);
		panelup.add(btnEvidencija);
		
		JLabel lblNewLabel_4 = new JLabel("Upravnik");
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setBounds(764, 251, 92, 21);
		panelup.add(lblNewLabel_4);
		
		JButton btnLogout2 = new JButton("Izloguj se");
		btnLogout2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelup, login_o);
			}
		});
		btnLogout2.setBounds(764, 273, 92, 21);
		panelup.add(btnLogout2);
		
		panelup2 = new JPanel();
		frame.getContentPane().add(panelup2, "name_65233536500700");
		panelup2.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 31, 543, 263);
		panelup2.add(scrollPane_2);
		
		tableEvid = new JTable();
		tableEvid.setFillsViewportHeight(true);
		scrollPane_2.setViewportView(tableEvid);
		
		JLabel lblNewLabel_5 = new JLabel("Evidencija o stanarima:");
		lblNewLabel_5.setBounds(10, 8, 112, 20);
		panelup2.add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Nazad");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				visibility(panelup2, panelup);
			}
		});
		btnNewButton.setBounds(787, 273, 85, 21);
		panelup2.add(btnNewButton);
	}

	public void visibility(JPanel a,JPanel b) {
		a.setVisible(!a.isVisible());
		b.setVisible(!b.isVisible());
	}

	public void viewRefresh_ss() throws ClassNotFoundException, SQLException {
		lista_st_ss=daoss.vratiStudente();
		model_tabela_ss=new StudentiTableModel1(lista_st_ss);
		tabelaSt.setModel(model_tabela_ss);
	}
	public void viewRefresh_up1() throws ClassNotFoundException, SQLException {
		lista_st_up=daoss.vratiStudentePoSkoru();
		model_tabela_up1=new StudentiTableModel2(lista_st_up);
		tableSta1.setModel(model_tabela_up1);
	}

	public void clearTxtField(JTextField tf) {
		tf.setText("");
	}
}
