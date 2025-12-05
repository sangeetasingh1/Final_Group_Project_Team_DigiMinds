package ui.SystemAdminWorkArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JPanel;

/**
 * Custom panel for drawing bar charts
 */
public class BarChartPanel extends JPanel {
    
    private Map<String, Integer> data;
    private String title;
    private String xAxisLabel;
    private String yAxisLabel;
    private Color[] colors;
    private int maxValue;
    
    public BarChartPanel(String title, String xAxisLabel, String yAxisLabel) {
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.data = new LinkedHashMap<>();
        this.colors = new Color[]{
            new Color(70, 130, 180),   // Steel Blue
            new Color(60, 179, 113),   // Medium Sea Green
            new Color(255, 140, 0),    // Dark Orange
            new Color(220, 20, 60),    // Crimson
            new Color(138, 43, 226),   // Blue Violet
            new Color(255, 20, 147),   // Deep Pink
            new Color(30, 144, 255),   // Dodger Blue
            new Color(50, 205, 50)     // Lime Green
        };
        this.setBackground(Color.WHITE);
    }
    
    public void setData(Map<String, Integer> data) {
        this.data = data != null ? new LinkedHashMap<>(data) : new LinkedHashMap<>();
        if (!this.data.isEmpty()) {
            this.maxValue = this.data.values().stream().mapToInt(Integer::intValue).max().orElse(1);
        } else {
            this.maxValue = 1;
        }
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
        
        if (data.isEmpty()) {
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 12));
            g2d.setColor(Color.GRAY);
            g2d.drawString("No data available", width / 2 - 50, height / 2);
            return;
        }
        
        // Chart area
        int chartX = 60;
        int chartY = 50;
        int chartWidth = width - chartX - 40;
        int chartHeight = height - chartY - 60;
        
        // Draw axes
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new java.awt.BasicStroke(2));
        g2d.drawLine(chartX, chartY, chartX, chartY + chartHeight); // Y-axis
        g2d.drawLine(chartX, chartY + chartHeight, chartX + chartWidth, chartY + chartHeight); // X-axis
        
        // Draw Y-axis label
        if (yAxisLabel != null && !yAxisLabel.isEmpty()) {
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 11));
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(yAxisLabel);
            g2d.rotate(-Math.PI / 2);
            g2d.drawString(yAxisLabel, -(chartY + chartHeight / 2 + labelWidth / 2), 15);
            g2d.rotate(Math.PI / 2);
        }
        
        // Draw X-axis label
        if (xAxisLabel != null && !xAxisLabel.isEmpty()) {
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 11));
            FontMetrics fm = g2d.getFontMetrics();
            int labelWidth = fm.stringWidth(xAxisLabel);
            g2d.drawString(xAxisLabel, chartX + (chartWidth - labelWidth) / 2, height - 10);
        }
        
        // Draw Y-axis scale
        g2d.setFont(new Font("Tahoma", Font.PLAIN, 10));
        int numTicks = 5;
        for (int i = 0; i <= numTicks; i++) {
            int value = (int) (maxValue * i / numTicks);
            int y = chartY + chartHeight - (chartHeight * i / numTicks);
            String label = String.valueOf(value);
            int labelWidth = g2d.getFontMetrics().stringWidth(label);
            g2d.drawString(label, chartX - labelWidth - 5, y + 5);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.setStroke(new java.awt.BasicStroke(1));
            g2d.drawLine(chartX - 5, y, chartX + chartWidth, y);
            g2d.setColor(Color.BLACK);
        }
        
        // Draw bars
        int barCount = data.size();
        int barWidth = Math.max(20, (chartWidth - (barCount - 1) * 10) / barCount);
        int spacing = 10;
        int index = 0;
        
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            String label = entry.getKey();
            Integer valueObj = entry.getValue();
            
            // Handle null values
            if (label == null) label = "Unknown";
            if (valueObj == null) valueObj = 0;
            int value = valueObj;
            
            // Calculate bar height
            int barHeight = maxValue > 0 ? (int) ((double) value / maxValue * chartHeight) : 0;
            
            // Draw bar
            Color barColor = colors[index % colors.length];
            g2d.setColor(barColor);
            int x = chartX + index * (barWidth + spacing);
            int y = chartY + chartHeight - barHeight;
            g2d.fillRect(x, y, barWidth, barHeight);
            
            // Draw bar border
            g2d.setColor(Color.BLACK);
            g2d.setStroke(new java.awt.BasicStroke(1));
            g2d.drawRect(x, y, barWidth, barHeight);
            
            // Draw value on top of bar
            if (barHeight > 15) {
                g2d.setColor(Color.BLACK);
                g2d.setFont(new Font("Tahoma", Font.PLAIN, 9));
                String valueStr = String.valueOf(value);
                int valueWidth = g2d.getFontMetrics().stringWidth(valueStr);
                g2d.drawString(valueStr, x + (barWidth - valueWidth) / 2, y - 5);
            }
            
            // Draw label below bar
            g2d.setFont(new Font("Tahoma", Font.PLAIN, 9));
            g2d.setColor(Color.BLACK);
            // Rotate label if too long
            String displayLabel = label.length() > 12 ? label.substring(0, 10) + "..." : label;
            int labelWidth = g2d.getFontMetrics().stringWidth(displayLabel);
            g2d.drawString(displayLabel, x + (barWidth - labelWidth) / 2, chartY + chartHeight + 15);
            
            index++;
        }
    }
}

