package org.tamedragon.visualization.ide;

import javax.swing.Icon;

class IconData {
	protected Icon m_icon = null;
	protected Icon m_expanded_icon = null;
	protected Object m_data = null;
	
	public IconData(Icon icon, Object data) {
		m_icon = icon;
		m_expanded_icon = null;
		m_data = data;
		
	}
	
	public IconData(Icon icon, Icon expandedIcon, Object data) {
		m_icon = icon;
		m_expanded_icon = expandedIcon;
		m_data = data;
	}
	
	public Icon getIcon() { 
		return m_icon; 
	}
	
	public Icon getExpandedIcon() {
		 return m_expanded_icon != null ? m_expanded_icon : m_icon;
	}

	public Object getObject() { 
		return m_data; 
	}

	public String toString() { 
		return m_data.toString(); 
	}
}
