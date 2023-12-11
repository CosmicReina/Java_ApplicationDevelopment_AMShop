package extended_JComponent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class JTable_DarkMode extends JTable {
    
    private class TableDarkHeader extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setBackground(new Color(30, 30, 30));
            component.setForeground(new Color(255, 255, 255));
            component.setFont(component.getFont().deriveFont(Font.BOLD, 12));
            return component;
        }
        
    }
    
    private class TableDarkCell extends DefaultTableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if(isCellSelected(row, column)){
                if(row % 2 == 0){
                    component.setBackground(new Color(30, 100, 150));
                }
                else{
                    component.setBackground(new Color(30, 85, 125));
                }
            }
            else{
                if(row % 2 == 0){
                    component.setBackground(new Color(50, 50, 50));
                }
                else{
                    component.setBackground(new Color(30, 30, 30));
                }
            }
            setBorder(new EmptyBorder(0, 0, 0, 0));
            component.setForeground(new Color(255, 255, 255));
            return component;
        }
        
    }

    public JTable_DarkMode() {
        this.getTableHeader().setDefaultRenderer(new TableDarkHeader());
        this.getTableHeader().setPreferredSize(new Dimension(0, 35));
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);
        this.setDefaultRenderer(Object.class, new TableDarkCell());
        this.setRowHeight(30);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)this.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
    }
    
    public void fixTable(JScrollPane scr){
        scr.setVerticalScrollBar(new JScrollBar_Custom());
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
