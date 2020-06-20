package Genesis.UI;

import Genesis.Math.Vector2;
import Genesis.Toolkit;

import java.awt.*;
import java.util.Vector;

public class UIMenu extends UIElement{
    private Vector<UIMenuItem> MenuItems;
    private int TabHeight = 24;
    private int HeaderWidth = 100;
    private int HeaderHeight = 100;
    private int HeaderSpacing = 10;
    private int HeaderPadding = 10;
    private int ItemSpacing = 10;
    private int ItemPadding = 10;
    private int ScrollbarWidth = 15;
    private int ScrollValue = 0;
    private int MaxScrollValue = 120;
    private int SelectedItemIndex = 0;
    private Color SelectedItemBorderColor;
    private Color SelectedItemBackgroundColor;
    private Color ScrollbarColor;
    private Color ScrollbarBackgroundColor;
    private Color SelectedForegroundColor;
    private UIMenuActionListener ActionListener;

    public UIMenu(String Name, String Text, Vector2 Location, Vector2 Size) {
        this.MenuItems = new Vector<UIMenuItem>();
        this.setName(Name);
        this.setLocation(Location);
        this.setSize(Size);
        this.setText(Text);
        this.setBackgroundColor(Toolkit.getColorAlpha(Color.BLACK, 200));
        this.setBorderColor(Color.BLACK);
        this.setForegroundColor(Color.lightGray);
        this.setSelectedItemBorderColor(new Color(255, 76, 0));
        this.setSelectedItemBackgroundColor(new Color(255, 119, 0));
        this.setSelectedForegroundColor(Color.BLACK);
        this.setScrollbarColor(Color.DARK_GRAY);
        this.setScrollbarBackgroundColor(Color.BLACK);
    }

    public Vector<UIMenuItem> getMenuItems() {
        return MenuItems;
    }

    public void setMenuItems(Vector<UIMenuItem> menuItems) {
        MenuItems = menuItems;
    }

    public int getTabHeight() {
        return TabHeight;
    }

    public void setTabHeight(int tabHeight) {
        TabHeight = tabHeight;
    }

    public int getHeaderWidth() {
        return HeaderWidth;
    }

    public void setHeaderWidth(int headerWidth) {
        HeaderWidth = headerWidth;
    }

    public int getHeaderHeight() {
        return HeaderHeight;
    }

    public void setHeaderHeight(int headerHeight) {
        HeaderHeight = headerHeight;
    }

    public int getHeaderSpacing() {
        return HeaderSpacing;
    }

    public void setHeaderSpacing(int headerSpacing) {
        HeaderSpacing = headerSpacing;
    }

    public int getHeaderPadding() {
        return HeaderPadding;
    }

    public void setHeaderPadding(int headerPadding) {
        HeaderPadding = headerPadding;
    }

    public int getItemSpacing() {
        return ItemSpacing;
    }

    public void setItemSpacing(int itemSpacing) {
        ItemSpacing = itemSpacing;
    }

    public int getItemPadding() {
        return ItemPadding;
    }

    public void setItemPadding(int itemPadding) {
        ItemPadding = itemPadding;
    }

    public void addMenuItem(UIMenuItem item) {
        this.MenuItems.add(item);
        item.setParentMenu(this);
    }

    public int getSelectedItemIndex() {
        return SelectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        SelectedItemIndex = selectedItemIndex;
    }

    public Color getSelectedItemBorderColor() {
        return SelectedItemBorderColor;
    }

    public void setSelectedItemBorderColor(Color selectedItemBorderColor) {
        SelectedItemBorderColor = selectedItemBorderColor;
    }

    public Color getSelectedItemBackgroundColor() {
        return SelectedItemBackgroundColor;
    }

    public void setSelectedItemBackgroundColor(Color selectedItemBackgroundColor) {
        SelectedItemBackgroundColor = selectedItemBackgroundColor;
    }

    public int getScrollbarWidth() {
        return ScrollbarWidth;
    }

    public void setScrollbarWidth(int scrollbarWidth) {
        ScrollbarWidth = scrollbarWidth;
    }

    public int getScrollValue() {
        return ScrollValue;
    }

    public void setScrollValue(int scrollValue) {
        ScrollValue = scrollValue;
    }

    public int getMaxScrollValue() {
        return MaxScrollValue;
    }

    public void setMaxScrollValue(int maxScrollValue) {
        MaxScrollValue = maxScrollValue;
    }

    public Color getScrollbarColor() {
        return ScrollbarColor;
    }

    public void setScrollbarColor(Color scrollbarColor) {
        ScrollbarColor = scrollbarColor;
    }

    public Color getScrollbarBackgroundColor() {
        return ScrollbarBackgroundColor;
    }

    public void setScrollbarBackgroundColor(Color scrollbarBackgroundColor) {
        ScrollbarBackgroundColor = scrollbarBackgroundColor;
    }

    public UIMenuItem getSelectedItem() {
        return this.MenuItems.get(this.SelectedItemIndex);
    }

    public Color getSelectedForegroundColor() {
        return SelectedForegroundColor;
    }

    public void setSelectedForegroundColor(Color selectedForegroundColor) {
        SelectedForegroundColor = selectedForegroundColor;
    }

    public UIMenuActionListener getActionListener() {
        return ActionListener;
    }

    public void setActionListener(UIMenuActionListener actionListener) {
        ActionListener = actionListener;
    }
}
