package ui.stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.StockController;
import model.StockModel;

public class AllStockPage extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel,northPanel,centerPanel,southPanel;
	private JLabel titleLbl,subtitleLbl;
	private JTable table;
	private JButton updateBtn, deleteBtn, backBtn, addBtn;
	DefaultTableModel dtm;
	private int id = -1;
	public AllStockPage() {
		landing();
		init();
	}
	
	public void landing() {
		Font titleFont = new Font("arial", Font.PLAIN, 25);
		Font subTitleFont = new Font("arial", Font.PLAIN, 20);
		
		//main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		setContentPane(mainPanel);
		
		
		//north panel
		northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,1));
		titleLbl = new JLabel("Stock Organizer",SwingConstants.CENTER);
		titleLbl.setFont(titleFont);
		northPanel.add(titleLbl);
		
		subtitleLbl = new JLabel("All Stock",SwingConstants.CENTER);
		subtitleLbl.setFont(subTitleFont);
		northPanel.add(subtitleLbl);
		mainPanel.add(northPanel, BorderLayout.NORTH);
		
		//center panel
		centerPanel = new JPanel();
		centerPanel.setLayout(null);
		
		//table
		table = new JTable();
		loadTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(10, 10, 375, 200);
		centerPanel.add(scrollPane);
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row;
				row = table.getSelectedRow();
				id = (int) table.getValueAt(row, 0);
			}
		});
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);

		
		//south panel
		southPanel = new JPanel();
		updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(this);
		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		addBtn = new JButton("Add Stock");
		addBtn.addActionListener(this);
		southPanel.add(updateBtn);
		southPanel.add(deleteBtn);
		southPanel.add(addBtn);
		southPanel.add(backBtn);
		mainPanel.add(southPanel, BorderLayout.SOUTH);
	}
	
	private void loadTable() {
		String header[] = {"ID","Stock Name", "Stock Brand","Stock Qty"};
		dtm = new DefaultTableModel(header,0);
		
		StockController controller = new StockController();
		try {
			ArrayList<StockModel> stock = controller.getStock();
			for (int i = 0; i < stock.size(); i++) {
				int id = stock.get(i).getStokID();
				String name = stock.get(i).getStockName();
				int qty = stock.get(i).getQuantity();
				String brand = stock.get(i).getStockBrand();
				
				Object[] data = {id,name,brand,qty};
				dtm.addRow(data);
			}
			table.setModel(dtm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init() {
		setTitle("Stock Organizer");
		setVisible(true);
		setSize(new Dimension(400,350));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == updateBtn) {
			if (id != -1) {
				this.setVisible(false);
				new ui.stock.UpdatePage(id);				
			}else {
				JOptionPane.showMessageDialog(this, "Please Pick The Item First!!!");
			}
		}else if(e.getSource() == backBtn) {
			this.setVisible(false);
			new ui.home.HomePage();
		}else if(e.getSource() == addBtn) {
			if(id != -1) {
				this.setVisible(false);
				new ui.stock.addStockPage(id);				
			}else {
				JOptionPane.showMessageDialog(this, "Please Pick The Item First!!!");
			}
		}else if(e.getSource() == deleteBtn) {
			if (id != -1) {
				new StockController().deleteStock(id);
				loadTable();			
			}else {
				JOptionPane.showMessageDialog(this, "Please Pick The Item First!!!");
			}
		}
		
	}

}
