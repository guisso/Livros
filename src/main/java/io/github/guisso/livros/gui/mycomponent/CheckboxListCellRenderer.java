/* 
 * Material didático destinado ao curso
 * de Programação Orientada a Objetos do 
 * Bacharelado em Ciência da Computação 
 * do IFNMG - Câmpus Montes Claros
 */
package io.github.guisso.livros.gui.mycomponent;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * A JList component within JCheckBox items.
 *
 * Source:
 * http://link-intersystems.com/blog/2014/10/19/custom-swing-component-renderers/
 * Source:
 * https://github.com/link-intersystems/blog/tree/master/custom-ListCellRenderer
 *
 * <pre>JList<Autor> list = new JList<Autor>();
 * list.setCellRenderer(new CheckboxListCellRenderer<Autor>());</pre>
 *
 * @author Luis Guisso <luis dot guisso at ifnmg dot edu dot br>
 * @version 0.0.1, 22/08/2021
 */
public class CheckboxListCellRenderer<E>
        extends JCheckBox
        implements ListCellRenderer<E> {

    private static final long serialVersionUID = 3734536442230283966L;

    @Override
    public Component getListCellRendererComponent(
            JList<? extends E> list,
            E value, int index, boolean isSelected, boolean cellHasFocus) {
        setComponentOrientation(list.getComponentOrientation());

        setFont(list.getFont());
        setText(String.valueOf(value));

        setBackground(list.getBackground());
        setForeground(list.getForeground());

        setSelected(isSelected);
        setEnabled(list.isEnabled());

        return this;
    }

}
