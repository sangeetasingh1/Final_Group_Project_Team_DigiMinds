package ui.SystemAdminWorkArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * 
 * @author sange
 */
public class PieChartPanel extends JPanel {
    
    private Map<String, Integer> data;
    private String title;
    private Color[] colors;
    private int total;
    
    public PieChartPanel(String title) {
        this.title = title;
        this.data = new LinkedHashMap<>();
        this.colors = new Color[]{
            new Color(70, 130, 180),   // Steel Blue
            new Color(60, 179, 113),   // Medium Sea Green
            new Color(255, 140, 0),    // Dark Orange
            new Color(220, 20, 60),    // Crimson
            new Color(138, 43, 226),   // Blue Violet
            new Color(255, 20, 147),   // Deep Pink
            new Color(30, 144, 255),   // Dodger Blue
            new Color(50, 205, 50),    // Lime Green
            new Color(255, 215, 0),    // Gold
            new Color(255, 69, 0)      // Red Orange
        };
        this.setBackground(Color.WHITE);
    }
    
    public void setData(Map<String, Integer> data) {
        this.data = data != null ? new LinkedHashMap<>(data) : new LinkedHashMap<>();
        this.total = this.data.values().stream().mapToInt(Integer::intValue).sum();
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (g == null) {
            return;
        }
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int width = getWidth();
        int height = getHeight();
        
        // Prevent division by zero or negative dimensions
        if (width <= 0 || height <= 0) {
            return;
        }
        
        // Draw title
        if (title != null && !title.isEmpty()) {
            g2d.setFont(new Font("Tahoma", Font.BOLD, 14));
            g2d.setColor(Color.BLACK);
            int titleWidth = g2d.getFontMetrics().stringWidth(title);
            g2d.drawString(title, (width - titleWidth) / 2, 20);
        }
        
        if (data.isEmpty() || total == 0) {
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 12));
            g2d.setColor(Color.GRAY);
            g2d.drawString("No data available", width / 2 - 50, height / 2);
            return;
        }
        
        // Calculate pie chart dimensions
        int centerX = width / 2;
        int centerY = height / 2 + 10;
        int radius = Math.min(width, height) / 3;
        int legendX = width - 180;
        int legendY = 50;
        
        // Draw pie slices
        double startAngle = 0;
        int index = 0;
        
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String label = entry.getKey();
            Integer valueObj = entry.getValue();
            
            // Handle null values
            if (label == null) label = "Unknown";
            if (valueObj == null) valueObj = 0;
            int value = valueObj;
            
            if (value > 0) {
                double angle = 360.0 * value / total;
                
                // Draw slice
                Color sliceColor = colors[index % colors.length];
                g2d.setColor(sliceColor);
                g2d.fillArc(centerX - radius, centerY - radius, radius * 2, radius * 2,
                    (int) startAngle, (int) Math.ceil(angle));
                
                // Draw slice border
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new java.awt.BasicStroke(2));
                g2d.drawArc(centerX - radius, centerY - radius, radius * 2, radius * 2,
                    (int) startAngle, (int) Math.ceil(angle));
                
                // Draw percentage label on slice if large enough
                if (angle > 10) {
                    double labelAngle = Math.toRadians(startAngle + angle / 2);
                    int labelX = centerX + (int) (radius * 0.6 * Math.cos(labelAngle));
                    int labelY = centerY + (int) (radius * 0.6 * Math.sin(labelAngle));
                    
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Tahoma", Font.BOLD, 11));
                    double percentage = (value * 100.0) / total;
                    String percentStr = String.format("%.1f%%", percentage);
                    int labelWidth = g2d.getFontMetrics().stringWidth(percentStr);
                    g2d.drawString(percentStr, labelX - labelWidth / 2, labelY);
                }
                
                // Draw legend
                g2d.setColor(sliceColor);
                g2d.fillRect(legendX, legendY + index * 20, 15, 15);
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
                String displayLabel = label.length() > 20 ? label.substring(0, 18) + "..." : label;
                g2d.drawString(displayLabel + " (" + value + ")", legendX + 20, legendY + index * 20 + 12);
                
                startAngle += angle;
                index++;
            }
        }
        
        // Draw total in center
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Tahoma", Font.BOLD, 12));
        String totalStr = "Total: " + total;
        int totalWidth = g2d.getFontMetrics().stringWidth(totalStr);
        g2d.drawString(totalStr, centerX - totalWidth / 2, centerY + 5);
    }
}

