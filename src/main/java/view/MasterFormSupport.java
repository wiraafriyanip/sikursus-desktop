package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Rectangle;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.plaf.basic.BasicButtonUI;

/** Komponen visual bersama untuk form master SIKURSUS. */
final class MasterFormSupport {
    static final Color NAVY = new Color(22, 54, 69);
    static final Color TEAL = new Color(12, 153, 119);
    static final Color BG = new Color(243, 247, 246);
    static final Color INK = new Color(25, 56, 70);
    static final Color MUTED = new Color(112, 132, 140);
    static final Color LINE = new Color(214, 226, 226);

    private MasterFormSupport() { }

    static JPanel panel(Color background) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(background);
        return panel;
    }

    static JLabel label(String text, boolean bold) {
        JLabel label = new JLabel(text);
        label.setForeground(INK);
        label.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, bold ? 12 : 11));
        return label;
    }

    static JTextField field() {
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));
        field.setForeground(INK);
        field.setBackground(Color.WHITE);
        field.setCaretColor(TEAL);
        field.setMargin(new Insets(5, 9, 5, 9));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(4, 7, 4, 7)));
        field.setPreferredSize(new java.awt.Dimension(170, 35));
        return field;
    }

    static void styleCombo(JComboBox<?> combo) {
        combo.setFont(new Font("SansSerif", Font.PLAIN, 12));
        combo.setBackground(Color.WHITE);
        combo.setForeground(INK);
        combo.setPreferredSize(new java.awt.Dimension(170, 35));
    }

    static JButton button(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setFont(new Font("SansSerif", Font.BOLD, 11));
        button.setForeground(foreground);
        button.setBackground(background);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        button.setPreferredSize(new java.awt.Dimension(120, 37));
        button.setUI(new ColorButtonUI(background, foreground));
        return button;
    }

    static void styleTable(JTable table) {
        table.setFont(new Font("SansSerif", Font.PLAIN, 12));
        table.setForeground(INK);
        table.setRowHeight(37);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.setShowVerticalLines(false);
        table.setShowHorizontalLines(true);
        table.setGridColor(new Color(230, 238, 236));
        table.setIntercellSpacing(new java.awt.Dimension(0, 1));
        table.setSelectionBackground(new Color(204, 235, 225));
        table.setSelectionForeground(INK);
        table.setAutoCreateRowSorter(true);
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setPreferredSize(new java.awt.Dimension(1, 39));
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean selected, boolean focus, int row, int column) {
                super.getTableCellRendererComponent(t, value, false, false, row, column);
                setOpaque(true);
                setBackground(NAVY);
                setForeground(Color.WHITE);
                setFont(new Font("SansSerif", Font.BOLD, 12));
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 2, 0, TEAL),
                        BorderFactory.createEmptyBorder(0, 8, 0, 4)));
                return this;
            }
        });
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override public Component getTableCellRendererComponent(JTable t, Object value,
                    boolean selected, boolean focus, int row, int column) {
                super.getTableCellRendererComponent(t, value, selected, focus, row, column);
                setOpaque(true);
                setFont(new Font("SansSerif", Font.PLAIN, 12));
                if (selected) {
                    setBackground(new Color(204, 235, 225));
                    setForeground(INK);
                } else {
                    setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 250, 249));
                    setForeground(INK);
                }
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 4));
                return this;
            }
        });
    }

    static GridBagConstraints gbc(int x, int y, int width, int height,
            double weightX, double weightY, int fill, Insets insets) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = x;
        c.gridy = y;
        c.gridwidth = width;
        c.gridheight = height;
        c.weightx = weightX;
        c.weighty = weightY;
        c.fill = fill;
        c.anchor = GridBagConstraints.NORTHWEST;
        c.insets = insets == null ? new Insets(0, 0, 0, 0) : insets;
        return c;
    }

    static void add(JPanel parent, Component child, int x, int y,
            double weightX, double weightY, int fill, Insets insets) {
        parent.add(child, gbc(x, y, 1, 1, weightX, weightY, fill, insets));
    }

    static void styleCard(JPanel panel) {
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE),
                BorderFactory.createEmptyBorder(18, 18, 16, 18)));
    }

    static void styleHeader(JPanel panel) {
        panel.setBorder(BorderFactory.createEmptyBorder(18, 25, 18, 25));
    }

    static void styleButtonRow(JPanel panel) {
        panel.setOpaque(false);
    }

    static void styleScroll(javax.swing.JScrollPane scroll) {
        scroll.setBorder(BorderFactory.createLineBorder(LINE));
        scroll.getViewport().setBackground(Color.WHITE);
    }

    private static final class ColorButtonUI extends BasicButtonUI {
        private final Color base;
        private final Color text;
        ColorButtonUI(Color base, Color text) { this.base = base; this.text = text; }
        @Override public void update(Graphics g, javax.swing.JComponent c) { paint(g, c); }
        @Override public void paint(Graphics g, javax.swing.JComponent c) {
            javax.swing.AbstractButton b = (javax.swing.AbstractButton) c;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color bg = b.isEnabled() ? base : mix(base, new Color(242, 247, 248), 0.77);
            if (b.isEnabled() && b.getModel().isPressed()) bg = mix(bg, Color.BLACK, 0.18);
            else if (b.isEnabled() && b.getModel().isRollover()) bg = mix(bg, Color.WHITE, 0.12);
            int w = b.getWidth() - 1, h = b.getHeight() - 1;
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w, h, 10, 10);
            g2.setColor(mix(bg, Color.BLACK, 0.12));
            g2.drawRoundRect(0, 0, w, h, 10, 10);
            if (b.hasFocus() && b.isEnabled()) {
                g2.setColor(TEAL);
                g2.drawRoundRect(2, 2, w - 4, h - 4, 8, 8);
            }
            g2.dispose();
            super.paint(g, c);
        }
        @Override protected void paintText(Graphics g, javax.swing.AbstractButton b,
                Rectangle r, String label) {
            g.setFont(b.getFont());
            g.setColor(b.isEnabled() ? text : new Color(105, 126, 136));
            javax.swing.plaf.basic.BasicGraphicsUtils.drawStringUnderlineCharAt(
                    g, label, b.getDisplayedMnemonicIndex(), r.x,
                    r.y + g.getFontMetrics().getAscent());
        }
        private static Color mix(Color a, Color b, double t) {
            return new Color((int)(a.getRed() * (1 - t) + b.getRed() * t),
                    (int)(a.getGreen() * (1 - t) + b.getGreen() * t),
                    (int)(a.getBlue() * (1 - t) + b.getBlue() * t));
        }
    }
}
